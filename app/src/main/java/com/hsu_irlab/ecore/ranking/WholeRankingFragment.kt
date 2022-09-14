package com.hsu_irlab.ecore.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hsu_irlab.ecore.databinding.FragmentWholeRankingBinding
import com.hsu_irlab.ecore.presentation.adapter.RankingAdapter
import com.hsu_irlab.ecore.presentation.viewmodel.ranking.RankingViewModel

class WholeRankingFragment : Fragment(){

    lateinit var binding : FragmentWholeRankingBinding
    private val model: RankingViewModel by activityViewModels()
    private lateinit var retrofitAdapter: RankingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWholeRankingBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView() // 리사이클러 뷰 연결
        setObserver()
    }
    private fun setView(){
        retrofitAdapter =  RankingAdapter().apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
        }
        binding.rvWholeRanking.adapter = retrofitAdapter // 리사이클러 뷰 연결
    }

    private fun setObserver() {
        // 뷰모델 관찰
        model.ranking.observe(viewLifecycleOwner) {
            model.ranking.value?.let { it1 -> retrofitAdapter.setData(it1) }
        }

    }

}