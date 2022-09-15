package com.hsu_irlab.ecore

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.ecore.databinding.FragmentFollowingBinding
import com.hsu_irlab.ecore.databinding.FragmentFollowingSearchDialogBinding
import com.hsu_irlab.ecore.presentation.viewmodel.follow.FollowingSearchDialogViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.follow.FollowingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowingSearchDialogFragment : DialogFragment() {
    lateinit var binding: FragmentFollowingSearchDialogBinding
    private val viewModel: FollowingSearchDialogViewModel by viewModels()
    override fun onStart() {
        super.onStart()
        if(dialog != null && activity !=null && isAdded){
            val fullWidth= Utils.getScreenWidth(requireActivity()) * .9
            dialog?.window?.setLayout(fullWidth.toInt(),ViewGroup.LayoutParams.WRAP_CONTENT)
            //dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFollowingSearchDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddFollowSearch.setOnClickListener {
            if(binding.etFollowingName.text == null)
            {
                binding.viewFollowSearchResult.visibility=View.GONE
                binding.tvAddFollowError.visibility=View.VISIBLE
                binding.tvAddFollowError.text="문자를 입력해주세요"
            }
            else{
                viewModel.getFollowSearch(binding.etFollowingName.text.toString())
            }
            viewModel.follow.observe(viewLifecycleOwner)
            {
                if(it == null)
                {
                    binding.viewFollowSearchResult.visibility=View.GONE
                    binding.tvAddFollowError.visibility=View.VISIBLE
                    binding.tvAddFollowError.text="유저를 찾을 수 없습니다."
                }
                else
                {
                    binding.tvAddFollowError.visibility=View.GONE
                    binding.viewFollowSearchResult.visibility=View.VISIBLE
                    binding.tvAddFollowName.text = binding.etFollowingName.text.toString()
                    binding.tvAddFollowScore.text = it.first().total_score.toString()
                    Glide.with(binding.ivAddFollow)
                        .load("${BuildConfig.BASE_URL}/upload/${it.first().profile_img}")
                        .circleCrop()
                        .into(binding.ivAddFollow)
                }

            }

        }
        binding.tvFollowCancel.setOnClickListener { dismiss() }
        viewModel.follow.observe(viewLifecycleOwner){ id ->
            binding.tvAddFollowAdd.setOnClickListener {
                id?.first()?.user_id?.let { it1 -> viewModel.addFollow(it1) }
                dismiss()
            }
        }
    }


}