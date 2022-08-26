package com.hsu_irlab.ecore.presentaion.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.domain.model.DomainRanking
import com.hsu_irlab.ecore.databinding.ItemRecyclerRankingBinding
import java.text.DecimalFormat

class RankingAdapter :RecyclerView.Adapter<RankingAdapter.ViewHolder>(){

    private var items: List<DomainRanking> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(items[position])
        holder.itemView.setOnClickListener{ //클릭 이벤트 발생 !!
            Toast.makeText(holder.itemView.context,"${items[position].name}",Toast.LENGTH_SHORT).show()
        }
    }

    inner class ViewHolder(private val binding: ItemRecyclerRankingBinding) : RecyclerView.ViewHolder(binding.root) {
        val dec=DecimalFormat("#,###")
        @SuppressLint("SetTextI18n")
        fun setItem(item: DomainRanking){
            binding.tvRankingName.text =  item.name
            binding.tvRankingScore.text =  dec.format(item.total_score.toInt())
            //binding.tvRankingGrade.text = "${adapterPosition+1}."
            binding.tvRankingGrade.text = item.row_num+"."
            when(binding.tvRankingGrade.text){
                "1." -> binding.tvRankingGrade.setTextColor(Color.parseColor("#FFBD1B"))
                "2." -> binding.tvRankingGrade.setTextColor(Color.parseColor("#C0C0C0"))
                "3." -> binding.tvRankingGrade.setTextColor(Color.parseColor("#BF8970"))
            }
            Glide.with(itemView)
                .load("${BuildConfig.BASE_URL}/upload/${item.profile_picture}")
                .circleCrop()
                .into(binding.ivProfile)
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