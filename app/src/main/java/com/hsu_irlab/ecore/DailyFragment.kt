package com.hsu_irlab.ecore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.ecore.databinding.FragmentDailyBinding
import com.hsu_irlab.ecore.presentation.viewmodel.BadgeViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.DailyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyFragment : Fragment() {
    lateinit var binding : FragmentDailyBinding

    private val model: DailyViewModel by viewModels()

    private val args: DailyFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyBinding.inflate(inflater,container,false)
        binding.tvDailyTitle.text = args.title
        binding.tvDailyReward.text = args.reward.toString()
        setObserver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setObserver() {
        model.dailyDetail.observe(viewLifecycleOwner) {
            binding.tvDailyDetail.text = it.detail
            Glide.with(this)
                .load("${BuildConfig.BASE_URL}/upload/${it.good_ex}")
                .circleCrop()
                .into(binding.ivDailyGood)
            Glide.with(this)
                .load("${BuildConfig.BASE_URL}/upload/${it.bad_ex}")
                .circleCrop()
                .into(binding.ivDailyBad)
        }
    }

}