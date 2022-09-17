package com.hsu_irlab.ecore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hsu_irlab.ecore.R
import com.hsu_irlab.ecore.databinding.FragmentCantChallengeBinding
import com.hsu_irlab.ecore.databinding.FragmentChallengeDetailBinding
import com.hsu_irlab.ecore.presentation.viewmodel.ChallengeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChallengeDetailFragment : Fragment() {
    lateinit var binding : FragmentChallengeDetailBinding
    private val args: ChallengeDetailFragmentArgs by navArgs()
    private val model: ChallengeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeDetailBinding.inflate(inflater,container,false)
        getChallengeDetail(args.data.challenge_id)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        with(binding){
            challengeDetailToolbar.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            challengeDetailToolbar.tvPagename.text = "도전"
            Log.e("TAG", "onViewCreated: ${args.data}", )

            tvCdTitle.text = args.data.title
            tvCdEcore.text = args.data.challenge_reward.toString()
            tvCdTerm.text ="${args.data.term}일"
            tvParticipate.text="현재 ${args.data.participating_person}명이 같이 환경을 지키고 있어요!"

        }
    }

    fun getChallengeDetail(id:Int){
        model.getChallengeDetail(id)
    }

    fun setObserver(){
        model.challengeDetail.observe(viewLifecycleOwner) {
            binding.tvCdDetail.text =it.detail
        }
    }
}