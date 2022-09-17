package com.hsu_irlab.ecore.ranking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.ecore.databinding.FragmentRankingBinding
import com.hsu_irlab.ecore.presentation.viewmodel.ranking.RankingViewModel
import com.hsu_irlab.ecore.ranking.FollowingRankingFragment
import com.hsu_irlab.ecore.ranking.WholeRankingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankingFragment : Fragment() {
    lateinit var binding: FragmentRankingBinding
    private val model: RankingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRankingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vp2Ranking.adapter = RankingPagerAdapter(requireActivity())
        binding.vp2Ranking.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                when (position) {
                    0 -> model.getMyRanking("all")
                    1 -> model.getMyRanking("following")

                }
            }
        })
        TabLayoutMediator(binding.tlRanking, binding.vp2Ranking) { tab, position ->
            when (position) {
                0 -> tab.text = "전체"
                1 -> tab.text = "팔로잉"

            }
        }.attach()
        model.myRanking.observe(viewLifecycleOwner) {
            binding.tvRankingGrade.text = it.row_num.toString()+"."
            binding.tvRankingName.text = it.name
            Glide.with(binding.ivProfile)
                .load("${BuildConfig.BASE_URL}/upload/${it.profile_img}")
                .circleCrop()
                .into(binding.ivProfile)
            binding.tvRankingScore.text = it.total_score.toString()

        }

        binding.rankingToolbar.btnBack.visibility=View.GONE
        binding.rankingToolbar.tvPagename.text = "랭킹"
    }

    private inner class RankingPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> WholeRankingFragment()
                else -> FollowingRankingFragment()
            }
        }

    }
}