package com.hsu_irlab.ecore.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.domain.model.DomainChallengeUploadDetail
import com.hsu_irlab.ecore.databinding.ItemRecyclerMyChallengeBinding

class MyChallengeAdapter : RecyclerView.Adapter<MyChallengeAdapter.ViewHolder>() {
    private var items: List<DomainChallengeUploadDetail> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerMyChallengeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    inner class ViewHolder(private val binding: ItemRecyclerMyChallengeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: DomainChallengeUploadDetail){
            Glide.with(itemView)
                .load("${BuildConfig.BASE_URL}/upload/${item.challenge_img}")
                .into(binding.ivMychall)
            binding.tvMychallDate.text = item.challenge_data.subSequence(5,10).toString().replace("-",".")
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun setData(newItems: List<DomainChallengeUploadDetail>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount():Int{
        return items.size
    }




}