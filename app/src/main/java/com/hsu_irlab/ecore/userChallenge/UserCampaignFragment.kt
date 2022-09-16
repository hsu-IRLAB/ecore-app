package com.hsu_irlab.ecore.userChallenge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.hsu_irlab.ecore.R
import com.hsu_irlab.ecore.databinding.FragmentFollowingRankingBinding
import com.hsu_irlab.ecore.databinding.FragmentUserCampaignBinding
import com.hsu_irlab.ecore.databinding.FragmentUserChallengeBinding
import com.hsu_irlab.ecore.presentation.adapter.UserChallengeAdapter
import com.hsu_irlab.ecore.presentation.adapter.UserDailyCampaignAdapter
import com.hsu_irlab.ecore.presentation.viewmodel.userChallenge.UserCampaignViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserCampaignFragment : Fragment() {

    private val model : UserCampaignViewModel by viewModels()
    private lateinit var adapter: UserDailyCampaignAdapter
    lateinit var binding: FragmentUserCampaignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserCampaignBinding.inflate(inflater,container,false)
        setObserver()
        setView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setView() {
        adapter = UserDailyCampaignAdapter().apply {
            setHasStableIds(true)
        }
        binding.rvUserCampaign.adapter = adapter

    }

    fun setObserver(){
        model.userCampaign.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

}