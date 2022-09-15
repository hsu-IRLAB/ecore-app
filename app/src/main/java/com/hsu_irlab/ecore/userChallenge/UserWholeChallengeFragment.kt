package com.hsu_irlab.ecore.userChallenge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.hsu_irlab.ecore.R
import com.hsu_irlab.ecore.databinding.FragmentUserWholeChallengeBinding
import com.hsu_irlab.ecore.presentation.adapter.UserChallengeAdapter
import com.hsu_irlab.ecore.presentation.viewmodel.userChallenge.UserCampaignViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.userChallenge.UserWholeChallengeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserWholeChallengeFragment : Fragment() {

    private val model: UserWholeChallengeViewModel by viewModels()
    private lateinit var adapter: UserChallengeAdapter
    lateinit var binding: FragmentUserWholeChallengeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserWholeChallengeBinding.inflate(inflater, container, false)
        setObserver()
        setView()
        return binding.root
    }

    private fun setView() {
        adapter = UserChallengeAdapter().apply {
            setHasStableIds(true)
        }
        binding.rvUserWholechallenge.adapter = adapter

    }

    fun setObserver() {
        model.userChallenge.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }


}