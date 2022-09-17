package com.hsu_irlab.ecore

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.hsu_irlab.ecore.databinding.ActivityMainBinding
import com.hsu_irlab.ecore.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController
    private val mainModel : MainViewModel by viewModels()

    val REQUEST_IMAGE_CAPTURE = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHomeFragment = supportFragmentManager.findFragmentById(R.id.activity_main_container) as NavHostFragment
        navController = navHomeFragment.navController
        mainModel.isLogin.observe(this) {
            if (it){
                binding.activityMainNavi.visibility = View.VISIBLE
                setupWithNavController(binding.activityMainNavi,navController)
            }
        }
        navController.addOnDestinationChangedListener{_,destination, _ ->
            if (destination.id==R.id.otherImgFragment || destination.id==R.id.otherImgCampaignFragment)
            {
                binding.activityMainNavi.visibility= View.GONE
            }
            else
            {
                binding.activityMainNavi.visibility = View.VISIBLE
            }
        }
    }

    override fun onBackPressed() {
        //TODO 현재 위치 home이면 back -> 종료
        Log.e("TAG", "onBackPressed: ${navController.currentDestination}", )
        Log.e("TAG", "onBackPressed: sdf", )
        super.onBackPressed()
    }

    /*override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_IMAGE_CAPTURE) {
            var count = grantResults.count { it == PackageManager.PERMISSION_DENIED }

            if(count != 0) {
                Toast.makeText(applicationContext, "권한을 동의해주세요.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            Log.e("dddd", "onActivityResult: dddddd", )
            mainModel.picture(imageBitmap)
//            main_img_photo.setImageBitmap(imageBitmap)
        }
    }

}