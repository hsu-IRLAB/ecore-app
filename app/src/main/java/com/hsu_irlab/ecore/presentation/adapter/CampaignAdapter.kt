package com.hsu_irlab.ecore.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig.BASE_URL
import com.hsu_irlab.domain.model.DomainCampaign
import com.hsu_irlab.ecore.databinding.ItemRecyclerCampaignBinding

class CampaignAdapter : RecyclerView.Adapter<CampaignAdapter.ViewHolder>() {
    private var items: List<DomainCampaign> = ArrayList()
    lateinit var onClick : (DomainCampaign)->Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerCampaignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CampaignAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            onClick(items[position])
        }
        holder.setItem(items[position])
    }

    inner class ViewHolder(private val binding: ItemRecyclerCampaignBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: DomainCampaign){
            val imgUrl = BASE_URL+"/upload/"+item.poster_img
            Glide.with(binding.root).load(imgUrl).into(binding.ivCampaign)
            binding.tvCampaignTitle.text = item.title
            binding.tvCampaignReward.text = item.campaign_reward.toString()
            val start = item.start_date.subSequence(5,7).toString()
            val start2 = item.start_date.subSequence(8,10).toString()
            val end = item.end_date.subSequence(5,7).toString()
            val end2 = item.end_date.subSequence(8,10).toString()
            val date = "$start.$start2~$end.$end2"
            binding.tvCampaignDate.text = date

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun setData(newItems: List<DomainCampaign>) {
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