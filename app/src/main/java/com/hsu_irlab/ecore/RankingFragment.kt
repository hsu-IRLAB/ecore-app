package com.hsu_irlab.ecore
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.hsu_irlab.ecore.databinding.FragmentRankingBinding
import com.hsu_irlab.ecore.presentaion.adapter.RankingAdapter
import com.hsu_irlab.ecore.presentaion.viewmodel.RankingViewModel

class RankingFragment : Fragment() {

    private val binding by lazy { FragmentRankingBinding.inflate(layoutInflater) }

    private val viewModel by lazy { ViewModelProvider(this,
        RankingViewModel.Factory(requireActivity().application))[RankingViewModel::class.java] }

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
        binding.activityMainRanking.adapter = retrofitAdapter // 리사이클러 뷰 연결
    }
    private fun setObserver() {
        // 뷰모델 관찰
        viewModel.retrofitRanking.observe(this) {
            viewModel.retrofitRanking.value?.let { it1 -> retrofitAdapter.setData(it1) }
        }

    }

}