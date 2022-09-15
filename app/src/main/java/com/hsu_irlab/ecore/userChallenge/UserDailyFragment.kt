package com.hsu_irlab.ecore.userChallenge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.hsu_irlab.ecore.R
import com.hsu_irlab.ecore.databinding.FragmentUserCampaignBinding
import com.hsu_irlab.ecore.databinding.FragmentUserDailyBinding
import com.hsu_irlab.ecore.presentation.adapter.UserChallengeAdapter
import com.hsu_irlab.ecore.presentation.viewmodel.userChallenge.UserCampaignViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.userChallenge.UserDailyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDailyFragment : Fragment() {
    private lateinit var adapter: UserChallengeAdapter
    private val model : UserDailyViewModel by viewModels()
    lateinit var binding: FragmentUserDailyBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDailyBinding.inflate(inflater,container,false)
        setObserver()
        setView()
        return binding.root
    }
    private fun setView() {
        adapter = UserChallengeAdapter().apply {
            setHasStableIds(true)
        }
        binding.rvUserDaily.adapter = adapter

    }

    fun setObserver(){
        model.userDaily.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

}