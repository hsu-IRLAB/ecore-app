package com.hsu_irlab.ecore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.hsu_irlab.ecore.databinding.FragmentCampaginDetailBinding
import com.hsu_irlab.ecore.databinding.FragmentChallengeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CampaignDetailFragment : Fragment() {
    lateinit var binding: FragmentCampaginDetailBinding
    private val args: CampaignDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCampaginDetailBinding.inflate(inflater, container, false)
        binding.textView9.text = args.data.title
        return binding.root
    }

}