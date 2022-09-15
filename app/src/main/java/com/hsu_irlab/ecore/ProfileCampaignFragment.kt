package com.hsu_irlab.ecore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hsu_irlab.ecore.databinding.FragmentProfileCampaignBinding


class ProfileCampaignFragment(user_id: Int) : Fragment() {
    lateinit var binding: FragmentProfileCampaignBinding
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
}