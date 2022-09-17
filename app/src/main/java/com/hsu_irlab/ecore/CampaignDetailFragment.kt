package com.hsu_irlab.ecore

import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.ecore.databinding.FragmentCampaginDetailBinding
import com.hsu_irlab.ecore.presentation.viewmodel.CampaignViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class CampaignDetailFragment : Fragment() {
    lateinit var binding: FragmentCampaginDetailBinding
    private val model: CampaignViewModel by viewModels()
    private val mainModel : MainViewModel by activityViewModels()

    val REQUEST_IMAGE_CAPTURE = 1

//    lateinit var mainBinding : ActivityMainBinding

    private val args: CampaignDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        mainModel.pictureClear()
//        mainBinding = ActivityMainBinding.inflate(layoutInflater)
//        mainBinding.activityMainNavi.visibility = View.GONE

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCampaginDetailBinding.inflate(inflater, container, false)
        setView()
        setObserver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolBar()
        binding.btnCampaginStart.setOnClickListener {
            checkPermission()
        }

    }
    private fun setToolBar(){
        binding.campaignToolBar.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.campaignToolBar.tvPagename.text = "캠페인"
    }

    private fun setView(){
        binding.tvCampaginDetailTitle.text = args.data.title
        binding.tvCampaginDetailEcoreVal.text = args.data.campaign_reward.toString()
        binding.tvCdDetail.text = args.data.detail
        Glide.with(this)
            .load("${BuildConfig.BASE_URL}/upload/${args.data.poster_img}")
            .into(binding.ivCdMain)
    }
    private fun setObserver() {
        mainModel.img.observe(viewLifecycleOwner){
            val file = getUploadFile(it)
            model.postImg(file, args.data.campaign_id)
            findNavController().popBackStack()
            Toast.makeText(requireContext(),"사진 업로드 완료",1000).show()

        }
    }
    private fun getUploadFile(bitmap: Bitmap): File {
        val fileName = System.currentTimeMillis().toString() + ".png"
        val cachePath = File(requireActivity().cacheDir, "images")
        cachePath.mkdirs()
        val stream = FileOutputStream("$cachePath/$fileName")
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        stream.close()
        return File(cachePath, fileName)
    }


    private fun cameraShot(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                    requireActivity().startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
    }

    private fun checkPermission(){
        val permissionChecker: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                cameraShot()
            }
            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(context, "카메라 권한을 설정해 주세요", Toast.LENGTH_SHORT).show()
            }
        }
        TedPermission.create()
            .setPermissionListener(permissionChecker)
            .setDeniedMessage("권한 설정 해주세요")
            .setPermissions(android.Manifest.permission.CAMERA)
            .check()
    }
}