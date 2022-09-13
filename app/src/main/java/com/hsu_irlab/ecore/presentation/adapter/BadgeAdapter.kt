package com.hsu_irlab.ecore.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hsu_irlab.domain.model.DomainBadge
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
            binding.tvBadgeTitle.text = item.title
            binding.tvBadgeDetail.text = item.detail
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