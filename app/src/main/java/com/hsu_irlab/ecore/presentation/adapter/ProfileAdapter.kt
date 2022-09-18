package com.hsu_irlab.ecore.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.domain.model.DomainCampaign
import com.hsu_irlab.domain.model.DomainImages
import com.hsu_irlab.ecore.databinding.ItemRecyclerProfileBinding

class ProfileAdapter():RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {
    lateinit var onClick : (Int)->Unit

    private var items: List<DomainImages> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemRecyclerProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.setImage(items[position])
        holder.itemView.setOnClickListener {
            items[position].img_id?.let { it1 -> onClick(it1) }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    inner class ProfileViewHolder(private val binding: ItemRecyclerProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setImage(images: DomainImages)
        {
            Glide.with(binding.imageView2)
                .load("${BuildConfig.BASE_URL}/upload/${images.img}")
                .into(binding.imageView2)
        }
    }
    fun setData(images:List<DomainImages>){
        this.items=images
        notifyDataSetChanged()
    }
}