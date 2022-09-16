package com.hsu_irlab.ecore

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.hsu_irlab.ecore.databinding.FragmentChangeNickNameDialogBinding
import com.hsu_irlab.ecore.databinding.FragmentLoginBinding
import com.hsu_irlab.ecore.databinding.FragmentUserBinding
import com.hsu_irlab.ecore.presentation.viewmodel.BadgeViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.ChangeNickNameDialogViewModel
import com.hsu_irlab.ecore.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeNickNameDialogFragment : DialogFragment() {

    lateinit var binding : FragmentChangeNickNameDialogBinding
    private val changeNickNameDialogViewModel : ChangeNickNameDialogViewModel by viewModels()
    private val mainModel : MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null && activity != null && isAdded) {
            val fullWidth = Utils.getScreenWidth(requireActivity()) * .8
            dialog?.window?.setLayout(fullWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    override fun dismiss() {
        super.dismiss()
        mainModel.getUserInfo(changeNickNameDialogViewModel.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangeNickNameDialogBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvCancle.setOnClickListener{
            dismiss() }

        binding.btnNamecheck.setOnClickListener{
            changeNickNameDialogViewModel.checkUserExist(binding.etUsername.text.toString())
        }

        binding.tvOk.setOnClickListener {
            changeNickNameDialogViewModel.changeName(binding.etUsername.text.toString())
//            Handler(Looper.getMainLooper()).postDelayed(Runnable {
//                dismiss()
//            },1500)
        }

        setObserver()
    }

    fun setObserver(){
        changeNickNameDialogViewModel.userExist.observe(viewLifecycleOwner){
            changeNickNameDialogViewModel.userExist.value?.let {
                if(it.data == "possible") {
                    binding.tvMassage.setTextColor(requireActivity().getColor(R.color.green_300))
                    binding.tvMassage.text = "사용 가능한 닉네임입니다"
                    binding.tvMassage.visibility = View.VISIBLE
                    binding.tvOk.visibility = View.VISIBLE
                }
                else{
                    binding.tvMassage.setTextColor(requireActivity().getColor(R.color.red_100))
                    binding.tvMassage.text="사용 불가능한 닉네임입니다."
                    binding.tvMassage.visibility = View.VISIBLE
                    binding.tvOk.visibility = View.GONE
                }
            }
        }

        changeNickNameDialogViewModel.changeName.observe(viewLifecycleOwner){
            changeNickNameDialogViewModel.changeName.value?.let {
                if(it.Message == "성공"){
                    Toast.makeText(requireContext(),"닉네임 변경에 성공하였습니다.",1000).show()
                    dismiss()
                }
                else{
                    Toast.makeText(requireContext(),"닉네임 변경에 실패하였습니다.",1000).show()
                }
            }
        }
    }

}