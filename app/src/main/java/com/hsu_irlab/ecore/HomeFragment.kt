package com.hsu_irlab. ecore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.ecore.databinding.FragmentBadgeBinding
import com.hsu_irlab.ecore.databinding.FragmentHomeBinding
import com.hsu_irlab.ecore.presentation.adapter.ProfileAdapter
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
    private lateinit var adpater: ProfileAdapter


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

        binding.btnToDaily.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDailyFragment(
                model.dailyData.title,model.dailyData.daily_reward)

            findNavController().navigate(action)

        }
        adpater=ProfileAdapter().apply { setHasStableIds(true) }
        binding.rvDailyDone.adapter=adpater
        binding.rvDailyDone.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        model.img.observe(viewLifecycleOwner){
            adpater.setData(it)
        }

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
            binding.tvHomeLike.text = "0"
            if((it.info?.daily_good ?: -1) != -1){
                Glide.with(this)
                    .load("${BuildConfig.BASE_URL}/upload/${it.info?.daily_img}")
                    .into(binding.btnToDaily)
                    .apply {
                        binding.btnToDaily.visibility=View.VISIBLE
                    }
                binding.btnToDaily.setOnClickListener {
                    Toast.makeText(requireContext(),"일일도전 완료",1000).show()
                }
            }else{
                binding.btnToDaily.background = resources.getDrawable(R.drawable.pc_upload,null)
                binding.btnToDaily.visibility=View.VISIBLE

            }

        }
    }

    override fun onResume() {
        model.getDaily()
        super.onResume()
    }

}