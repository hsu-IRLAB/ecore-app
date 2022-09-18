package com.hsu_irlab.ecore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.ecore.databinding.FragmentOtherImgBinding
import com.hsu_irlab.ecore.presentation.viewmodel.OtherImgViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtherImgFragment : DialogFragment() {
    lateinit var binding: FragmentOtherImgBinding
    private val viewmodel: OtherImgViewModel by viewModels()
    private val args by navArgs<OtherImgFragmentArgs>()
    private var isLiked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtherImgBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.getOneImg(args.type,args.userId,args.imgId)
        viewmodel.getUserInfo(args.userId)
        binding.btnOtherImgBack.setOnClickListener { findNavController().popBackStack() }
        binding.btnOtherImgReport.setOnClickListener {
            viewmodel.addReport(args.type,args.imgId)
            Toast.makeText(context, "신고 되었습니다.", Toast.LENGTH_SHORT).show()
        }
        binding.btnOtherImgLike.setOnClickListener {
            if(isLiked)
            {
                binding.btnOtherImgLike.isSelected=false
                isLiked=false
//                viewmodel.delLike(args.type,args.imgId)
//                viewmodel.getOneImg(args.type,args.userId,args.imgId)
            }
            else if (!isLiked)
            {
                binding.btnOtherImgLike.isSelected=true
                isLiked=true
                viewmodel.addLike(args.type,args.imgId)
                viewmodel.getOneImg(args.type,args.userId,args.imgId)
            }
            viewmodel.getOneImg(args.type,args.userId,args.imgId)
        }
        viewmodel.image.observe(viewLifecycleOwner){
            binding.tvOtherImgTitle.text=it?.title
            binding.tvOtherImgDate.text=it?.date
            binding.tvOtherImgLike.text=it?.good.toString()
            Glide.with(binding.imageView)
                .load("${BuildConfig.BASE_URL}/upload/${it?.img}")
                .into(binding.imageView)
        }
        viewmodel.userInfo.observe(viewLifecycleOwner){
            binding.tvOtherImgName.text=it.name
        }

    }

}