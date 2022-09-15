package com.hsu_irlab.ecore.userChallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.hsu_irlab.ecore.R
import com.hsu_irlab.ecore.databinding.FragmentRatingBinding
import com.hsu_irlab.ecore.databinding.FragmentUserChallengeBinding

class UserChallengeFragment : Fragment() {
    lateinit var binding : FragmentUserChallengeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserChallengeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userChallengeToolbar.btnBack.setOnClickListener { findNavController().navigate(R.id.action_userChallengeFragment_to_userFragment) }
        binding.userChallengeToolbar.tvPagename.text="완료한 도전"

        binding.vp2UserChallenge.adapter = UserChallengeAdapter(requireActivity())
        binding.vp2UserChallenge.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                when(position){

                }
            }
        })
        TabLayoutMediator(binding.tlChallenge,binding.vp2UserChallenge) { tab, position ->
            when(position) {
                0->tab.text ="일일도전"
                1->tab.text ="도전"
                2->tab.text ="캠페인"
            }
        }.attach()
    }

    private inner class UserChallengeAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return when (position){
                0-> UserDailyFragment()
                1-> UserWholeChallengeFragment()
                else-> UserCampaignFragment()
            }
        }
    }

}