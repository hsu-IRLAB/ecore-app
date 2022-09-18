package com.hsu_irlab.ecore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.ecore.databinding.FragmentOtherImgCampaignBinding
import com.hsu_irlab.ecore.presentation.viewmodel.OtherImgViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtherImgCampaignFragment : Fragment() {
    lateinit var binding: FragmentOtherImgCampaignBinding
    private val viewmodel: OtherImgViewModel by viewModels()
    private val args by navArgs<OtherImgCampaignFragmentArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtherImgCampaignBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.getOneCampaignImg(args.userId,args.imgId)
        viewmodel.getUserInfo(args.userId)
        binding.btnOtherImgCampaignBack.setOnClickListener { findNavController().popBackStack() }
        viewmodel.image.observe(viewLifecycleOwner){
            binding.tvOtherImgCampaignTitle.text=it?.title
            binding.tvOtherImgCampaignDate.text=it?.date
            Glide.with(binding.imageViewCampaign)
                .load("${BuildConfig.BASE_URL}/upload/${it?.campaign_img}")
                .into(binding.imageViewCampaign)
        }
        viewmodel.userInfo.observe(viewLifecycleOwner){
            binding.tvOtherImgCampaignName.text=it.name
        }
    }

}