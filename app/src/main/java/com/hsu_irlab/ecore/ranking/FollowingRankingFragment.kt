package com.hsu_irlab.ecore.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hsu_irlab.ecore.databinding.FragmentFollowingRankingBinding
import com.hsu_irlab.ecore.presentation.adapter.RankingAdapter
import com.hsu_irlab.ecore.presentation.viewmodel.ranking.FollowingRankingViewModel

class FollowingRankingFragment:Fragment() {

    private val binding by lazy { FragmentFollowingRankingBinding.inflate(layoutInflater) }

    private val viewModel by lazy { ViewModelProvider(this,
        FollowingRankingViewModel.Factory())[FollowingRankingViewModel::class.java] }

    private lateinit var retrofitAdapter: RankingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setView() // 리사이클러 뷰 연결
        setObserver() //
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
    private fun setView(){
        retrofitAdapter =  RankingAdapter().apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
        }
        binding.rvFollowingRanking.adapter = retrofitAdapter // 리사이클러 뷰 연결
    }
    private fun setObserver() {
        // 뷰모델 관찰
        viewModel.retrofitRanking.observe(this) {
            viewModel.retrofitRanking.value?.let { it1 -> retrofitAdapter.setData(it1) }
        }

    }

}