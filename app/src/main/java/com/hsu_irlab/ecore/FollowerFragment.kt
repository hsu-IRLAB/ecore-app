package com.hsu_irlab.ecore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hsu_irlab.ecore.R
import com.hsu_irlab.ecore.databinding.FragmentFollowerBinding
import com.hsu_irlab.ecore.databinding.FragmentRankingBinding
import com.hsu_irlab.ecore.databinding.FragmentWholeRankingBinding
import com.hsu_irlab.ecore.presentation.adapter.FollowAdapter
import com.hsu_irlab.ecore.presentation.adapter.RankingAdapter
import com.hsu_irlab.ecore.presentation.viewmodel.follow.FollowerViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.ranking.WholeRankingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowerFragment : Fragment() {
    lateinit var binding: FragmentFollowerBinding
    private val viewModel: FollowerViewModel by viewModels()
    private lateinit var adpater: FollowAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adpater = FollowAdapter { it ->
            viewModel.getFollowSearch(it)
            viewModel.follow.observe(viewLifecycleOwner)
            {
                val id = it?.first()?.user_id
                if (id != null) {
                    findNavController().navigate(
                        FollowingFragmentDirections.actionFollowingFragmentToProfileFragment(
                            id
                        )
                    )
                }
            }
        }.apply { setHasStableIds(true) }
        binding.rvFollower.adapter = adpater
        viewModel.follower.observe(viewLifecycleOwner) {
            adpater.setData(it)
            binding.tvFollowerCount.text = it.size.toString()
        }
        binding.followerToolbar.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.followerToolbar.tvPagename.text = "팔로워"
    }

}