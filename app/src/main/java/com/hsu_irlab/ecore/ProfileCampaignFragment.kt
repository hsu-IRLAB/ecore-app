package com.hsu_irlab.ecore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hsu_irlab.ecore.databinding.FragmentProfileCampaignBinding
import com.hsu_irlab.ecore.presentation.adapter.FollowAdapter
import com.hsu_irlab.ecore.presentation.adapter.ProfileAdapter
import com.hsu_irlab.ecore.presentation.adapter.ProfileCampaignAdapter
import com.hsu_irlab.ecore.presentation.viewmodel.ProfileImgViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileCampaignFragment(user_id: Int) : Fragment() {
    lateinit var binding: FragmentProfileCampaignBinding
    private val viewModel: ProfileImgViewModel by viewModels()
    private lateinit var adpater: ProfileCampaignAdapter
    val user_id=user_id
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileCampaignBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDailyImages("campaign", user_id = user_id)
        adpater=ProfileCampaignAdapter {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToOtherImgCampaignFragment(imgId = it, userId =user_id))}.apply { setHasStableIds(true) }
        binding.rvProfileCampaign.adapter=adpater
        binding.rvProfileCampaign.layoutManager=GridLayoutManager(context,3)
        viewModel.images.observe(viewLifecycleOwner){
            adpater.setData(it)
        }
    }
}