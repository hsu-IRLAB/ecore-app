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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.ecore.databinding.ActivityMainBinding
import com.hsu_irlab.ecore.databinding.FragmentCampaginDetailBinding
import com.hsu_irlab.ecore.databinding.FragmentChallengeBinding
import com.hsu_irlab.ecore.presentation.viewmodel.BadgeViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.CampaignViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CampaignDetailFragment : Fragment() {
    lateinit var binding: FragmentCampaginDetailBinding
    private val model: CampaignViewModel by viewModels()
    val REQUEST_IMAGE_CAPTURE = 1

//    lateinit var mainBinding : ActivityMainBinding

    private val args: CampaignDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
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
        model.campaignImg.observe(viewLifecycleOwner) { it ->
            val data =it.filter { it.title== args.data.title}
        }
    }

}