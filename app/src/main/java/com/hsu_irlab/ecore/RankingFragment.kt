package com.hsu_irlab.ecore
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.hsu_irlab.ecore.databinding.FragmentRankingBinding
import com.hsu_irlab.ecore.ranking.FollowingRankingFragment
import com.hsu_irlab.ecore.ranking.WholeRankingFragment

class RankingFragment : Fragment() {

    private val binding by lazy { FragmentRankingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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