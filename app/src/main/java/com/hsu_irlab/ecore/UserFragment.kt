package com.hsu_irlab.ecore

import android.R
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig.BASE_URL
import com.hsu_irlab.domain.model.DomainUserInfo
import com.hsu_irlab.ecore.databinding.FragmentUserBinding
import com.hsu_irlab.ecore.presentaion.viewmodel.UserViewModel


class UserFragment : Fragment() {

    private val binding by lazy { FragmentUserBinding.inflate(layoutInflater) }

    private val viewModel by lazy {
        ViewModelProvider(this,UserViewModel.Factory())[UserViewModel::class.java]}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setObserver()

        binding.tvBadgeAll.setOnClickListener {
            val intent : Intent = Intent(activity,BadgeActivity::class.java)
            startActivity(intent)
        }

        binding.ivChangeProfileimg.setOnClickListener {

        }

        binding.ivChangeNickname.setOnClickListener {
            val bundle = Bundle()
            val dialog: ChangeNickNameDialogFragment = ChangeNickNameDialogFragment().getInstance()
            dialog.arguments = bundle
            activity?.supportFragmentManager?.let {
                fragmentManager -> dialog.show(
                    fragmentManager,dialog.tag
                )
            }
        }

        binding.ivUserimage.setOnClickListener {

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