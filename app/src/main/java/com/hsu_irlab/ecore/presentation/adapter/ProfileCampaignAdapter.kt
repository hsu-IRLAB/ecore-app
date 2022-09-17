package com.hsu_irlab.ecore.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.domain.model.DomainImages
import com.hsu_irlab.ecore.databinding.ItemRecyclerProfileBinding

class ProfileCampaignAdapter(val onClick: (Int) -> Unit): RecyclerView.Adapter<ProfileCampaignAdapter.ProfileCampaignViewHolder>() {
    private var items: List<DomainImages> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileCampaignViewHolder {
        val binding = ItemRecyclerProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileCampaignViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileCampaignViewHolder, position: Int) {
        holder.setImage(items[position])
        holder.itemView.setOnClickListener {
            items[position].campaign_image_id?.let { it1 -> onClick(it1) }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    inner class ProfileCampaignViewHolder(private val binding: ItemRecyclerProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setImage(images: DomainImages)
        {
            Glide.with(binding.imageView2)
                .load("${BuildConfig.BASE_URL}/upload/${images.campaign_img}")
                .into(binding.imageView2)
        }
    }
    fun setData(images:List<DomainImages>){
        this.items=images
        notifyDataSetChanged()
    }
}