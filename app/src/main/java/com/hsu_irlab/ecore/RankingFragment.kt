package com.hsu_irlab.ecore
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.ecore.databinding.FragmentRankingBinding
import com.hsu_irlab.ecore.presentaion.viewmodel.ranking.FollowingRankingViewModel
import com.hsu_irlab.ecore.presentaion.viewmodel.ranking.RankingViewModel
import com.hsu_irlab.ecore.presentaion.viewmodel.ranking.WholeRankingViewModel
import com.hsu_irlab.ecore.ranking.FollowingRankingFragment
import com.hsu_irlab.ecore.ranking.WholeRankingFragment
import kotlin.math.log

class RankingFragment : Fragment() {

    private val binding by lazy { FragmentRankingBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this,
        RankingViewModel.Factory())[RankingViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner=this
        binding.viewmodel=viewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load("${BuildConfig.BASE_URL}/upload/${viewModel.myRanking.value?.profile_picture}")
            .circleCrop()
            .into(binding.ivProfile)
        binding.vp2Ranking.adapter=RankingPagerAdapter(requireActivity())
        binding.vp2Ranking.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                when(position)
                {
                    0-> viewModel.getMyRanking("all")
                    1->viewModel.getMyRanking("following")
                }
            }
        })
        TabLayoutMediator(binding.tlRanking,binding.vp2Ranking){ tab,position ->
            when(position)
            {
                0-> tab.text="전체"
                1-> tab.text="팔로잉"

            }
        }.attach()
    }



    private inner class RankingPagerAdapter(fa:FragmentActivity): FragmentStateAdapter(fa){
        override fun getItemCount(): Int {
            return 2
        }


        override fun createFragment(position: Int): Fragment {

            return when(position)
            {
                0->WholeRankingFragment()
                else->FollowingRankingFragment()
            }
        }

    }


}