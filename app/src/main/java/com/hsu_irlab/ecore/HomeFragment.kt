package com.hsu_irlab.ecore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hsu_irlab.ecore.databinding.FragmentBadgeBinding
import com.hsu_irlab.ecore.databinding.FragmentHomeBinding
import com.hsu_irlab.ecore.presentation.viewmodel.BadgeViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.HomeViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat


@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    private val model: HomeViewModel by viewModels()
    private val mainModel : MainViewModel by activityViewModels()

//    private val viewModel by lazy { ViewModelProvider(this,
//        HomeViewModel.Factory(99))[HomeViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()

    }

//    private fun setView(){
////        retrofitAdapter =  RankingAdapter().apply {
////            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
////        }
////        binding.rvFollowingRanking.adapter = retrofitAdapter // 리사이클러 뷰 연결
//    }
    private fun setObserver() {
        // 뷰모델 관찰
        mainModel.userInfo.observe(viewLifecycleOwner) {
            binding.tvHomeName.text = it.name
            val dec = DecimalFormat("#,###")
            binding.tvEcoretotValue.text = dec.format(it.total_score)

        }

        model.dailyInfo.observe(viewLifecycleOwner) {
            binding.tvDailyHome.text = it.title
            binding.tvEcoreValue.text = it.daily_reward.toString()
        }
    }

}