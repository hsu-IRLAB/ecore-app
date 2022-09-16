package com.hsu_irlab.ecore

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.hsu_irlab.ecore.databinding.FragmentChangeNickNameDialogBinding
import com.hsu_irlab.ecore.databinding.FragmentReviewDialogBinding
import com.hsu_irlab.ecore.presentation.viewmodel.ReviewDialogViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.userChallenge.UserWholeChallengeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewDialogFragment : DialogFragment() {

    private val args: ReviewDialogFragmentArgs by navArgs()
    private val reviewModel : ReviewDialogViewModel by viewModels()
    lateinit var binding : FragmentReviewDialogBinding
    private val model: UserWholeChallengeViewModel by activityViewModels()


    override fun onStart() {
        super.onStart()
        if (dialog != null && activity != null && isAdded) {
            val fullWidth = Utils.getScreenWidth(requireActivity()) * .85
            dialog?.window?.setLayout(fullWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(javaClass.simpleName, "onCreate: ${args.title}", )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewDialogBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun dismiss() {
        super.dismiss()
        model.getWholeChallenge()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvReviewTitle.text = args.title

        binding.tvReviewCancle.setOnClickListener {
            Log.e(javaClass.simpleName, "onViewCreated: I'm Clicked!", )
            dismiss()
        }

        binding.tvReviewOk.setOnClickListener {
            val text = binding.etReview.text.toString()
            reviewModel.postReview(args.uId,text)
        }

        setObserver()
    }

    fun setObserver(){
        reviewModel.review.observe(viewLifecycleOwner){
            reviewModel.review.value?.let {
                if(it.Message=="성공"){
                    Toast.makeText(requireContext(),"후기가 등록되었습니다.",1000).show()
                    dismiss()
                }
                else{
                    Toast.makeText(requireContext(),"후기가 등록되지 않았습니다.",1000).show()
                }
            }
        }
    }

}