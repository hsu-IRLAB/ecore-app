package com.hsu_irlab.ecore.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hsu_irlab.ecore.databinding.FragmentFollowingRankingBinding
import com.hsu_irlab.ecore.presentation.adapter.RankingAdapter
import com.hsu_irlab.ecore.presentation.viewmodel.ranking.FollowingRankingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowingRankingFragment : Fragment() {
    lateinit var binding: FragmentFollowingRankingBinding
    private val model: FollowingRankingViewModel by activityViewModels()
    private lateinit var retrofitAdapter: RankingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowingRankingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrofitAdapter = RankingAdapter {
            findNavController().navigate(
                RankingFragmentDirections.actionRankingFragmentToProfileFragment(
                    it
                )
            )
        }.apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
        }
        binding.rvFollowingRanking.adapter = retrofitAdapter // 리사이클러 뷰 연결
        model.ranking.observe(viewLifecycleOwner) {
            retrofitAdapter.setData(it)
        }
    }
}
