package com.hsu_irlab.ecore.presentaion.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hsu_irlab.domain.model.DomainRanking
import com.hsu_irlab.ecore.databinding.ItemRecyclerRankingBinding

class RankingAdapter :RecyclerView.Adapter<RankingAdapter.ViewHolder>(){

    private var items: List<DomainRanking> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    inner class ViewHolder(private val binding: ItemRecyclerRankingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: DomainRanking){
            binding.tvRankingName.text =  item.name
            binding.tvRankingScore.text =  item.total_score
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun setData(newItems: List<DomainRanking>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    //뭐임
    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun getItemCount():Int{
        return items.size
    }

}