package com.hsu_irlab.ecore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
    }

    override fun onBackPressed() {
        //TODO 현재 위치 home이면 back -> 종료
        Log.e("TAG", "onBackPressed: ${navController.currentDestination}", )
        Log.e("TAG", "onBackPressed: sdf", )
        super.onBackPressed()
    }

}