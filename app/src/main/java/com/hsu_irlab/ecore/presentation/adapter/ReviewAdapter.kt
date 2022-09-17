package com.hsu_irlab.ecore.presentation.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.data.BuildConfig.BASE_URL
import com.hsu_irlab.domain.model.DomainBadge
import com.hsu_irlab.domain.model.DomainChallengeDetail
import com.hsu_irlab.domain.model.DomainReview
import com.hsu_irlab.domain.model.ReviewDetail
import com.hsu_irlab.ecore.R
import com.hsu_irlab.ecore.databinding.ItemRecyclerBadgeBinding
import com.hsu_irlab.ecore.databinding.ItemRecyclerReviewBinding

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    private var items: List<ReviewDetail> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    inner class ViewHolder(private val binding: ItemRecyclerReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: ReviewDetail){
            binding.tvReview.text = item.review_content
            binding.tvReviewNick.text = item.name
            Glide.with(itemView)
                .load("${BuildConfig.BASE_URL}/upload/${item.profile_img}")
                .circleCrop()
                .into(binding.ivReviewProfile)
            Glide.with(itemView)
                .load("${BuildConfig.BASE_URL}/upload/${item.review_img}")
                .into(binding.ivReview)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun setData(newItems: List<ReviewDetail>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount():Int{
        return items.size
    }

}