package com.hsu_irlab.ecore.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.domain.model.DomainRanking
import com.hsu_irlab.ecore.databinding.ItemRecyclerRankingBinding
import java.text.DecimalFormat

class RankingAdapter(val onClick: (Int) -> Unit) :RecyclerView.Adapter<RankingAdapter.ViewHolder>(){

    private var items: List<DomainRanking> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(items[position])
        holder.itemView.setOnClickListener{ //클릭 이벤트 발생 !!
            Toast.makeText(holder.itemView.context,"${items[position].name}",Toast.LENGTH_SHORT).show()
            onClick(items[position].user_id)
        }
    }

    inner class ViewHolder(private val binding: ItemRecyclerRankingBinding) : RecyclerView.ViewHolder(binding.root) {
        val dec=DecimalFormat("#,###")
        @SuppressLint("SetTextI18n")
        fun setItem(item: DomainRanking){
            binding.tvRankingName.text =  item.name
            binding.tvRankingScore.text =  dec.format(item.total_score.toInt())
            binding.tvRankingGrade.text = item.row_num.toString()+"."
            when(binding.tvRankingGrade.text){
                "1." -> binding.tvRankingGrade.setTextColor(Color.parseColor("#FFBD1B"))
                "2." -> binding.tvRankingGrade.setTextColor(Color.parseColor("#C0C0C0"))
                "3." -> binding.tvRankingGrade.setTextColor(Color.parseColor("#BF8970"))
            }
            Glide.with(itemView)
                .load("${BuildConfig.BASE_URL}/upload/${item.profile_img}")
                .circleCrop()
                .into(binding.ivProfile)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<DomainRanking>) {
        this.items = newItems
        notifyDataSetChanged()
    }


    override fun getItemCount():Int{
        return items.size
    }

}