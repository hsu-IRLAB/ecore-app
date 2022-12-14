package com.hsu_irlab.ecore.ranking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import com.hsu_irlab.ecore.EcoreApp
import com.hsu_irlab.ecore.databinding.FragmentRankingBinding
import com.hsu_irlab.ecore.databinding.FragmentWholeRankingBinding
import com.hsu_irlab.ecore.presentaion.adapter.RankingAdapter
import com.hsu_irlab.ecore.presentaion.viewmodel.ranking.WholeRankingViewModel

class WholeRankingFragment : Fragment(){

    private val binding by lazy { FragmentWholeRankingBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this,
        WholeRankingViewModel.Factory())[WholeRankingViewModel::class.java] }

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
        binding.rvWholeRanking.adapter = retrofitAdapter // 리사이클러 뷰 연결
    }

    private fun setObserver() {
        // 뷰모델 관찰
        viewModel.retrofitRanking.observe(this) {
            viewModel.retrofitRanking.value?.let { it1 -> retrofitAdapter.setData(it1) }
        }

    }

}