package com.hsu_irlab.ecore.challenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.hsu_irlab.ecore.databinding.FragmentChallengeBinding
import com.hsu_irlab.ecore.presentation.adapter.CampaignAdapter
import com.hsu_irlab.ecore.presentation.viewmodel.ChallengeViewModel
import com.hsu_irlab.ecore.ranking.FollowingRankingFragment
import com.hsu_irlab.ecore.ranking.WholeRankingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengeFragment : Fragment() {
    lateinit var binding: FragmentChallengeBinding

    private val model: ChallengeViewModel by viewModels()
    private lateinit var campaignAdapter: CampaignAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChallengeBinding.inflate(inflater, container, false)
        setView()
        setObserver()
        campaignAdapter.onClick={
            val action = ChallengeFragmentDirections.actionChallengeFragmentToCampaginDetailFragment(it)
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    private fun setView() {
        campaignAdapter = CampaignAdapter().apply {
            setHasStableIds(true)
        }
        binding.rvCampaign.adapter = campaignAdapter
        binding.rvCampaign.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.vpChallenge.adapter = ChallengePagerAdapter(requireActivity())

        TabLayoutMediator(binding.tabLayout, binding.vpChallenge) { tab, position ->
            when (position) {
                0 -> tab.text = "참여중"
                1 -> tab.text = "참여가능"
            }
        }.attach()
    }

    private fun setObserver() {
        model.campaignList.observe(viewLifecycleOwner) { it ->
            campaignAdapter.setData(it)
        }
    }
    private inner class ChallengePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int {
            return 2
        }
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> CanChallengeFragment()
                else -> CantChallengeFragment()
            }
        }
    }


}