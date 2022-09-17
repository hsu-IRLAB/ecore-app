package com.hsu_irlab.ecore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Dimension
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.domain.model.ReviewDetail
import com.hsu_irlab.ecore.R
import com.hsu_irlab.ecore.challenge.ChallengeFragmentDirections
import com.hsu_irlab.ecore.databinding.FragmentCantChallengeBinding
import com.hsu_irlab.ecore.databinding.FragmentChallengeDetailBinding
import com.hsu_irlab.ecore.presentation.adapter.BadgeAdapter
import com.hsu_irlab.ecore.presentation.adapter.ReviewAdapter
import com.hsu_irlab.ecore.presentation.viewmodel.ChallengeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class ChallengeDetailFragment : Fragment() {
    lateinit var binding : FragmentChallengeDetailBinding
    private val args: ChallengeDetailFragmentArgs by navArgs()
    private val model: ChallengeDetailViewModel by viewModels()
    private lateinit var adapter: ReviewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChallengeDetailBinding.inflate(inflater,container,false)
        setView()

        getChallengeDetail(args.data.challenge_id)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        with(binding){
            challengeDetailToolbar.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnChallenge.setOnClickListener {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                val today = Calendar.getInstance()
                val startDate = args.data.start_date?.let { it1 -> dateFormat.parse(it1) }
                val cal = Calendar.getInstance()
                val day = (today.time.time - (startDate?.time ?: (today.time.time))) / (60 * 60 * 24 * 1000)
                val title = args.data.title
                val action = ChallengeDetailFragmentDirections.actionChallengeDetailFragmentToChallengeUploadFragment(day.toInt()+1,title,)
                findNavController().navigate(action)
            }


            challengeDetailToolbar.tvPagename.text = "도전"
            Log.e("TAG", "onViewCreated: ${args.data}", )

            tvCdTitle.text = args.data.title
            tvCdEcore.text = args.data.challenge_reward.toString()
            tvCdTerm.text ="${args.data.term}일"
            tvParticipate.text="현재 ${args.data.participating_person}명이 같이 환경을 지키고 있어요!"

        }
    }

    private fun getChallengeDetail(id:Int){
        model.getChallengeDetail(id)
    }

    private fun setView(){
        adapter =  ReviewAdapter().apply {
            setHasStableIds(true) // 리사이클러 뷰 업데이트 시 깜빡임 방지
        }
        binding.pgReview.adapter = adapter


        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.pgReview.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }
        binding.pgReview.offscreenPageLimit = 1

    }

    fun setObserver(){
        model.challengeDetail.observe(viewLifecycleOwner) {
            binding.tvCdDetail.text =it.detail
            if(it.example.size>=3){
                Glide.with(this)
                    .load("${BuildConfig.BASE_URL}/upload/${it.example[0]}")
                    .into(binding.ivCdExample1)
                Glide.with(this)
                    .load("${BuildConfig.BASE_URL}/upload/${it.example[1]}")
                    .into(binding.ivCdExample2)
                Glide.with(this)
                    .load("${BuildConfig.BASE_URL}/upload/${it.example[2]}")
                    .into(binding.ivCdExample3)
            }

//            val data = arrayListOf<ReviewDetail>(
//                ReviewDetail("dd","profile.png","dd","profile.png"),
//                ReviewDetail("wwww","profile.png","wwww","profile.png"),
//                ReviewDetail("ee","profile.png","ff","profile.png")
//            )
//            it.review?.let { it1 -> adapter.setData(data) }
            it.review?.let { it1 -> adapter.setData(it1) }
        }
    }
}