package com.hsu_irlab.ecore.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hsu_irlab.domain.model.DomainFollow
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.ecore.databinding.ItemRecyclerFollowBinding

class FollowAdapter : RecyclerView.Adapter<FollowAdapter.FollowViewHolder>() {
    private var items: List<DomainFollow> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        val binding =
            ItemRecyclerFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class FollowViewHolder(private val binding: ItemRecyclerFollowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(follow: DomainFollow) {
            binding.tvFollowItem.text = follow.name
            Glide.with(itemView)
                .load("${BuildConfig.BASE_URL}/upload/${follow.profile_img}")
                .circleCrop()
                .into(binding.ivFollowItem)
        }
    }

    fun setData(follows:List<DomainFollow>){
        this.items=follows
        notifyDataSetChanged()
    }
}