package com.hsu_irlab.ecore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hsu_irlab.ecore.R
import com.hsu_irlab.ecore.databinding.FragmentFollowerBinding
import com.hsu_irlab.ecore.databinding.FragmentFollowingBinding
import com.hsu_irlab.ecore.presentation.adapter.FollowAdapter
import com.hsu_irlab.ecore.presentation.viewmodel.follow.FollowerViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.follow.FollowingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowingFragment : Fragment() {
    lateinit var binding: FragmentFollowingBinding
    private val viewModel: FollowingViewModel by viewModels()
    private lateinit var adpater: FollowAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adpater=FollowAdapter().apply { setHasStableIds(true) }
        binding.rvFollowing.adapter=adpater
        viewModel.following.observe(viewLifecycleOwner){
            adpater.setData(it)
            binding.tvFollowingCount.text = it.size.toString()
        }
        binding.followingToolbar.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.followingToolbar.tvPagename.text="팔로잉"
    }


}