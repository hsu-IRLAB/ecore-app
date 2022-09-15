package com.hsu_irlab.ecore.presentation.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig.BASE_URL
import com.hsu_irlab.domain.model.DomainBadge
import com.hsu_irlab.ecore.R
import com.hsu_irlab.ecore.databinding.ItemRecyclerBadgeBinding

class BadgeAdapter : RecyclerView.Adapter<BadgeAdapter.ViewHolder>() {
    private var items: List<DomainBadge> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerBadgeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    inner class ViewHolder(private val binding: ItemRecyclerBadgeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: DomainBadge){
            Log.e("TAG", "setItem: ${item.badge_img}", )
            binding.tvBadgeTitle.text = item.title
            binding.tvBadgeDetail.text = item.detail

            //TODO 이미지 여기 주소
            val imgUrl = BASE_URL+"/upload/"+item.badge_img

            Glide.with(binding.root).load(imgUrl).into(binding.ivBadge)
            item.badge_date?.let {
                binding.tvBadgeDate.text = item.badge_date
            }

            if(item.badge_date == null)
                binding.badgeLayout.setBackgroundResource(R.drawable.bg_badge_null_item)
            else
                binding.badgeLayout.setBackgroundResource(R.drawable.bg_badge_item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun setData(newItems: List<DomainBadge>) {
        this.items = newItems
        notifyDataSetChanged()
    }
//    override fun getItemViewType(position: Int): Int {
//        return position
//    }

    override fun getItemCount():Int{
        return items.size
    }




}