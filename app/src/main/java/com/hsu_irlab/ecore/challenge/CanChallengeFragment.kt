package com.hsu_irlab.ecore.challenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hsu_irlab.ecore.R
import com.hsu_irlab.ecore.databinding.FragmentCanChallengeBinding
import com.hsu_irlab.ecore.databinding.FragmentChallengeBinding
import com.hsu_irlab.ecore.databinding.FragmentFollowingRankingBinding
import com.hsu_irlab.ecore.presentation.adapter.RankingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class CanChallengeFragment : Fragment() {
    lateinit var binding : FragmentCanChallengeBinding
//    private lateinit var retrofitAdapter: ChallengeA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCanChallengeBinding.inflate(inflater, container, false)

        return binding.root
    }

}