package com.hsu_irlab.ecore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.ecore.databinding.FragmentFollowerBinding
import com.hsu_irlab.ecore.databinding.FragmentProfileBinding
import com.hsu_irlab.ecore.databinding.FragmentRatingBinding
import com.hsu_irlab.ecore.presentation.viewmodel.ProfileViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.follow.FollowerViewModel


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private val args by navArgs<ProfileFragmentArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserInfo(args.userId)
        viewModel.userInfo.observe(viewLifecycleOwner) {
            binding.tvProfileName.text = it.name
            binding.tvProfileFollowingValue.text = it.following.toString()
            binding.tvFollowerValue.text = it.follower.toString()
            binding.tvProfileMyecoreValue.text = it.total_score.toString()
            binding.tvProfileDailyValue.text = it.daily_challenge_done.toString()
            binding.tvProfileChallengeValue.text = it.challenge_done.toString()
            Glide.with(binding.ivProfileImage)
                .load("${BuildConfig.BASE_URL}/upload/${it.profile_img}")
                .circleCrop()
                .into(binding.ivProfileImage)
        }
        TabLayoutMediator(binding.profileTablayout, binding.profileViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "일일도전"
                1 -> tab.text = "도전"
                2 -> tab.text = "캠페인"
            }
        }.attach()
        binding.profileViewPager.adapter = ProfilePagerAdapter(requireActivity())
    }

    private inner class ProfilePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> ProfileDailyFragment(args.userId)
                1 -> ProfileChallengeFragment(args.userId)
                else -> ProfileCampaignFragment(args.userId)

            }
        }

    }


}