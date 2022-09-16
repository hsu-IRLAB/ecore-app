package com.hsu_irlab.ecore.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig.BASE_URL
import com.hsu_irlab.domain.model.DomainImages
import com.hsu_irlab.ecore.databinding.ItemRecyclerUserChallengeBinding

class UserDailyCampaignAdapter(
    // val onReviewClick: (Int) -> Unit
) : RecyclerView.Adapter<UserDailyCampaignAdapter.ViewHolder>(
) {
    private var items: List<DomainImages> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserDailyCampaignAdapter.ViewHolder {
        val binding = ItemRecyclerUserChallengeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserDailyCampaignAdapter.ViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding: ItemRecyclerUserChallengeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: DomainImages) {
            with(binding) {
                var imgUrl: String
                item.campaign_img?.let { imgUrl = BASE_URL + "/upload/" + item.campaign_img
                    Log.e(javaClass.simpleName, "setItem: ${imgUrl}")
                    Glide.with(binding.root).load(imgUrl).into(ivUserChallenge) }

                item.img?.let {
                    imgUrl = BASE_URL + "/upload/" + item.img
                    Log.e(javaClass.simpleName, "setItem: ${imgUrl}")
                    Glide.with(binding.root).load(imgUrl).into(ivUserChallenge)
                }
                item.title?.let {
                    tvUserchallengeTitle.text = item.title
                }
                if (item.good != null) {
                    tvUserChallengeGood.visibility = View.VISIBLE
                    tvUserChallengeGood.text = item.good.toString()
                } else {
                    tvUserChallengeGood.visibility = View.INVISIBLE
                }
                item.date?.let {
                    tvUserChallengeDate.text = item.date
                }
                item.is_review?.let { it ->
                    when (it) {
                        0 -> btnReview.visibility = View.VISIBLE
                        1 -> btnReview.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    internal fun setData(newItems: List<DomainImages>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}