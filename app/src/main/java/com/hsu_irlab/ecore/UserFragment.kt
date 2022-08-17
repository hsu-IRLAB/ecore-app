package com.hsu_irlab.ecore

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hsu_irlab.ecore.databinding.FragmentUserBinding
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig.BASE_URL
import com.hsu_irlab.domain.model.DomainRanking
import com.hsu_irlab.domain.model.DomainUserInfo
import com.hsu_irlab.ecore.presentaion.viewmodel.UserViewModel

class UserFragment : Fragment() {

    private val binding by lazy { FragmentUserBinding.inflate(layoutInflater) }

    private val viewModel by lazy {
        ViewModelProvider(this,UserViewModel.Factory())[UserViewModel::class.java]}

    private var userInfo : DomainUserInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /*userInfo=viewModel.retrofitUserInfo.value
        binding.tvName.text = userInfo!!.name
        */

        setObserver()



        binding.tvBadgeAll.setOnClickListener {
            val intent : Intent = Intent(activity,BadgeActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setObserver() {
        // 뷰모델 관찰
        viewModel.retrofitUserInfo.observe(viewLifecycleOwner) {
            viewModel.retrofitUserInfo.value?.let { it ->
                binding.tvName.text = it.name
                binding.tvMyecoreValue.text= it.total_score.toString()
                binding.tvFollowerValue.text=it.follwer.toString()
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

}