package com.hsu_irlab.ecore

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.hsu_irlab.ecore.databinding.FragmentUserBinding
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.hsu_irlab.data.BuildConfig.BASE_URL
import com.hsu_irlab.ecore.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {
    lateinit var binding : FragmentUserBinding
    private val mainModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater,container,false)
        /*userInfo=viewModel.retrofitUserInfo.value
        binding.tvName.text = userInfo!!.name
        */
        setObserver()
        binding.ivBadgeAll.setOnClickListener {
            findNavController().navigate(R.id.action_userFragment_to_badgeFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvFollowingValue.setOnClickListener { findNavController().navigate(R.id.action_userFragment_to_followingFragment)  }
        binding.tvFollowing.setOnClickListener { findNavController().navigate(R.id.action_userFragment_to_followingFragment) }

        binding.tvFollower.setOnClickListener { findNavController().navigate(R.id.action_userFragment_to_followerFragment) }
        binding.tvFollowerValue.setOnClickListener { findNavController().navigate(R.id.action_userFragment_to_followerFragment) }

        binding.btnChangeNickName.setOnClickListener { findNavController().navigate(R.id.action_userFragment_to_changeNickNameDialogFragment) }

        binding.ivUserChallenge.setOnClickListener { findNavController().navigate(R.id.action_userFragment_to_userChallengeFragment) }

        binding.btnChangePhoto.setOnClickListener{

        }
        binding.ivOss.setOnClickListener{
            Intent(requireActivity().applicationContext, OssLicensesMenuActivity::class.java).also { it2->
                OssLicensesMenuActivity.setActivityTitle("오픈소스 라이선스")
                startActivity(it2)
            }
        }
    }

    private fun setObserver() {
        mainModel.userInfo.observe(viewLifecycleOwner) {
            binding.tvName.text = it.name
            binding.tvMyecoreValue.text= it.total_score.toString()
            binding.tvFollowerValue.text=it.follower.toString()
            binding.tvFollowingValue.text=it.following.toString()
            binding.tvChallengeValue.text=it.challenge_done.toString()
            binding.tvDailyValue.text=it.daily_challenge_done.toString()
            Glide.with(this)
                .load("${BASE_URL}/upload/${it.profile_img}")
                .circleCrop()
                .into(binding.ivUserimage)

        }
    }
}