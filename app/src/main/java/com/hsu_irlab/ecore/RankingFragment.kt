package com.hsu_irlab.ecore
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.ecore.databinding.FragmentRankingBinding
import com.hsu_irlab.ecore.databinding.FragmentUserBinding
import com.hsu_irlab.ecore.presentation.viewmodel.HomeViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.ranking.RankingViewModel
import com.hsu_irlab.ecore.ranking.FollowingRankingFragment
import com.hsu_irlab.ecore.ranking.WholeRankingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankingFragment : Fragment() {
    lateinit var binding : FragmentRankingBinding
    private val model:RankingViewModel  by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding.lifecycleOwner=this
//        binding.viewmodel=viewModel

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRankingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                    0-> model.getMyRanking("all")
                    1->model.getMyRanking("following")

                }
                Glide.with(this@RankingFragment)
                    .load("${BuildConfig.BASE_URL}/upload/${model.myRanking.value?.profile_picture}")
                    .circleCrop()
                    .into(binding.ivProfile)
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