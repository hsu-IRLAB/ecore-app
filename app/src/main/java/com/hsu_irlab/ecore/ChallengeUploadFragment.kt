package com.hsu_irlab.ecore

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.ecore.databinding.FragmentChallengeDetailBinding
import com.hsu_irlab.ecore.databinding.FragmentChallengeUploadBinding
import com.hsu_irlab.ecore.presentation.adapter.MyChallengeAdapter
import com.hsu_irlab.ecore.presentation.adapter.ReviewAdapter
import com.hsu_irlab.ecore.presentation.viewmodel.ChallengeUploadViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.ChallengeViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengeUploadFragment : Fragment() {
    lateinit var binding : FragmentChallengeUploadBinding
    private val args: ChallengeUploadFragmentArgs by navArgs()
    private val model : ChallengeUploadViewModel by viewModels()
    private lateinit var adapter: MyChallengeAdapter
    val REQUEST_IMAGE_CAPTURE = 1

    private val challengeModel : ChallengeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeUploadBinding.inflate(inflater,container,false)
        val id = challengeModel.user_challenge_id
        model.getChallengeUploadDetail(id)

        adapter =  MyChallengeAdapter().apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
        }
        binding.rvMyChallenge.adapter = adapter
        binding.rvMyChallenge.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        with(binding){
            idChupToolbar.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            idChupToolbar.tvPagename.text = "도전"
            tvChupTerm.text = args.day.toString()+"일차0"
            tvChupTitle.text = args.titiel
            btnChupStart.setOnClickListener {
                checkPermission()
                val action = ChallengeUploadFragmentDirections.actionChallengeUploadFragmentToRatingFragment(args.titiel,challengeModel.user_challenge_id,"challenge")
                findNavController().navigate(action)
            }
        }
    }
    private fun setObserver() {
        model.challengeDetail.observe(viewLifecycleOwner) { it ->
            Glide.with(this)
                .load("${BuildConfig.BASE_URL}/upload/${it.good_example}")
                .into(binding.ivChupGood)
            Glide.with(this)
                .load("${BuildConfig.BASE_URL}/upload/${it.bad_example}")
                .into(binding.ivChupGood)
            val current =it.my_challenge.size.toString()
            val cnt = current+"/"+it.achievement_condition.toString()
            binding.tvChupCnt.text = cnt
            adapter.setData(it.my_challenge)

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

    override fun onResume() {
        model.getChallengeUploadDetail(challengeModel.user_challenge_id)
        super.onResume()

    }
}