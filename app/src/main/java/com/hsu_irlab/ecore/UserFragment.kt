package com.hsu_irlab.ecore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.hsu_irlab.ecore.databinding.FragmentUserBinding
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
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

        binding.btnChangeNickName.setOnClickListener{

        }

        binding.btnChangePhoto.setOnClickListener{

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