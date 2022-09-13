package com.hsu_irlab.ecore

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.hsu_irlab.ecore.databinding.FragmentChangeNickNameDialogBinding
import com.hsu_irlab.ecore.presentaion.viewmodel.ChangeNickNameDialogViewModel
import kotlinx.coroutines.launch


class ChangeNickNameDialogFragment : DialogFragment() {

    private val binding by lazy { FragmentChangeNickNameDialogBinding.inflate(layoutInflater) }

    private val viewModel by lazy {
        ViewModelProvider(this, ChangeNickNameDialogViewModel.Factory())[ChangeNickNameDialogViewModel::class.java]}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        binding.btnNamecheck.setOnClickListener {
            Log.e("Text",""+binding.etUsername.text.toString())
            viewModel.checkUserExist(binding.etUsername.text.toString())
        }

        binding.tvOk.setOnClickListener {
            changeNickName()
            Handler().postDelayed(Runnable {
                dismiss()
            }, 1500)
        }

        binding.tvCancle.setOnClickListener {
            dismiss()
        }

        setObserver()

        return binding.root
    }

    fun getInstance(): ChangeNickNameDialogFragment {
        return ChangeNickNameDialogFragment()
    }

    private fun setObserver() {
        viewModel.retrofitUserExist.observe(viewLifecycleOwner) {
            viewModel.retrofitUserExist.value?.let { it ->
                if(it.Data == "possible") {
                    //binding.tvMassage.setTextColor()
                    binding.tvMassage.text = "사용 가능한 닉네임입니다"
                    binding.tvMassage.visibility = View.VISIBLE
                    binding.tvOk.visibility = View.VISIBLE
                }
                else{
                    //binding.tvMassage.setTextColor(R.color.red_100)
                    binding.tvMassage.text="사용 불가능한 닉네임입니다."
                    binding.tvMassage.visibility = View.VISIBLE
                    binding.tvOk.visibility = View.GONE
                }
            }
        }
    }

    private fun changeNickName(){
        lifecycleScope.launch{
            viewModel.changeName(binding.etUsername.text.toString())

        }
        viewModel.retrofitChangeName.observe(viewLifecycleOwner){
            viewModel.retrofitChangeName.value?.let { it->
                if(it.Message == "성공"){
                    Log.e("헉!성공함 ㅠㅠ","감격이양")
                }
                else {
                    Log.e("닉변 실패ㅜ","슬프다 헉헉")
                }
            }
        }
    }
}