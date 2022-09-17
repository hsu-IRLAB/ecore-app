package com.hsu_irlab.ecore

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.content.PermissionChecker
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.ecore.databinding.FragmentDailyBinding
import com.hsu_irlab.ecore.presentation.viewmodel.DailyViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DailyFragment : Fragment() {
    lateinit var binding : FragmentDailyBinding

    private val mainModel : MainViewModel by activityViewModels()

    private val model: DailyViewModel by viewModels()

    private val args: DailyFragmentArgs by navArgs()
    val REQUEST_IMAGE_CAPTURE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainModel.pictureClear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyBinding.inflate(inflater,container,false)
        binding.tvDailyTitle.text = args.title
        binding.tvDailyReward.text = args.reward.toString()
        setObserver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDailyStart.setOnClickListener {
            checkPermission()
        }
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



    /*private fun checkPermission() {
        val permission = mutableMapOf<String, String>()
        permission["camera"] = Manifest.permission.CAMERA
        val denied = permission.count { ContextCompat.checkSelfPermission(requireContext(), it.value)  == PackageManager.PERMISSION_DENIED }
        if(denied > 0 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permission.values.toTypedArray(), REQUEST_IMAGE_CAPTURE)
        }
        cameraShot()
    }*/
    private fun setObserver() {
        model.dailyDetail.observe(viewLifecycleOwner) {
            binding.tvDailyDetail.text = it.detail
            Glide.with(this)
                .load("${BuildConfig.BASE_URL}/upload/${it.good_ex}")
                .circleCrop()
                .into(binding.ivDailyGood)
            Glide.with(this)
                .load("${BuildConfig.BASE_URL}/upload/${it.bad_ex}")
                .circleCrop()
                .into(binding.ivDailyBad)
        }
        mainModel.img.observe(viewLifecycleOwner){
//            val action = DailyFragmentDirections.actionDailyFragmentToRatingFragment(
//                args.title)
//            findNavController().navigate(action)
//            binding.ivDailyGood.setImageBitmap(it)
        }
    }
}