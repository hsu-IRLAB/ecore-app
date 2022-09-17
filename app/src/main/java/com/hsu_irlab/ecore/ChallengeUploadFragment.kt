package com.hsu_irlab.ecore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
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
            binding.tvChupTerm.text = args.day.toString()+"일차"
            binding.tvChupTitle.text = args.titiel

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
}