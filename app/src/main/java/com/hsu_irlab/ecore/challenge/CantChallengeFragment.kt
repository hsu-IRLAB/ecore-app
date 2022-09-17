package com.hsu_irlab.ecore.challenge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hsu_irlab.domain.model.DomainChallenge
import com.hsu_irlab.ecore.R
import com.hsu_irlab.ecore.databinding.FragmentCanChallengeBinding
import com.hsu_irlab.ecore.databinding.FragmentCantChallengeBinding
import com.hsu_irlab.ecore.presentation.adapter.ChallengeAdapter
import com.hsu_irlab.ecore.presentation.viewmodel.CantViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.ChallengeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CantChallengeFragment : Fragment() {
    lateinit var binding : FragmentCantChallengeBinding
    private lateinit var adapter: ChallengeAdapter
    private val model : CantViewModel by viewModels()
    private val challengeModel : ChallengeViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCantChallengeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ChallengeAdapter().apply {
            setHasStableIds(true)
        }
        binding.rvCant.adapter = adapter // 리사이클러 뷰 연결
        adapter.onClick = {
            if(it.user_challenge_id!=null){
                challengeModel.user_challenge_id= it.user_challenge_id!!
            }

//            val data = DomainChallenge(1,"dd",1,1,1,-1,"f")
            val action = ChallengeFragmentDirections.actionChallengeFragmentToChallengeDetailFragment(it)
//            val action = ChallengeFragmentDirections.actionChallengeFragmentToChallengeDetailFragment(data)
            findNavController().navigate(action)
        }
        model.challengeList.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

}