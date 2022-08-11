package com.hsu_irlab.ecore.presentaion.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hsu_irlab.data.response.BaseResponse
import com.hsu_irlab.data.response.RankingResponse
//import com.hsu_irlab.domain.model.DomainBaseRanking
//import com.hsu_irlab.domain.model.DomainRanking
import com.hsu_irlab.ecore.databinding.ItemRecyclerRankingBinding

class RankingAdapter :RecyclerView.Adapter<RankingAdapter.ViewHolder>(){

    private var items: BaseResponse = BaseResponse(ArrayList())

//    private var items: DomainBaseRanking = DomainBaseRanking(listOf<DomainRanking>(DomainRanking("","","",2)))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(items.data[position])

    }

    inner class ViewHolder(private val binding: ItemRecyclerRankingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: RankingResponse){
            binding.tvRankingName.text =  item.name
            binding.tvRankingScore.text =  item.total_score
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun setData(newItems: BaseResponse) {
        this.items = newItems
        notifyDataSetChanged()
    }

    //뭐임
    override fun getItemViewType(position: Int): Int {
        return position
    }

    //item
//    override fun getItemCount() = items.data.size

    override fun getItemCount():Int{
        Log.e("fffffffff", "getItemCount:    ${items.data.size}", )

        return items.data.size
    }

}