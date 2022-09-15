package com.hsu_irlab.ecore.challenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCantChallengeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ChallengeAdapter().apply {
            setHasStableIds(true)
        }
        binding.rvCant.adapter = adapter // 리사이클러 뷰 연결

        model.challengeList.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

}