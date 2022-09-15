package com.hsu_irlab.ecore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hsu_irlab.ecore.databinding.FragmentProfileDailyBinding


class ProfileDailyFragment(user_id: Int) : Fragment() {
    lateinit var binding: FragmentProfileDailyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileDailyBinding.inflate(inflater, container, false)
        return binding.root
    }
}