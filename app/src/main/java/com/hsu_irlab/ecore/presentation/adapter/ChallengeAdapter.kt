package com.hsu_irlab.ecore.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hsu_irlab.data.BuildConfig
import com.hsu_irlab.domain.model.DomainChallenge
import com.hsu_irlab.domain.model.DomainRanking
import com.hsu_irlab.ecore.databinding.ItemRecyclerChallengeBinding
import com.hsu_irlab.ecore.databinding.ItemRecyclerRankingBinding
import java.text.DecimalFormat

class ChallengeAdapter :RecyclerView.Adapter<ChallengeAdapter.ViewHolder>(){

    private var items: List<DomainChallenge> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerChallengeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    inner class ViewHolder(private val binding: ItemRecyclerChallengeBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setItem(item: DomainChallenge){
            binding.tvChallengeTitle.text =  item.title
            binding.tvChallengeReward.text =  item.challenge_reward.toString()
            binding.tvChallengeCnt.text = "\uD83D\uDC64"+item.participating_person.toString()
            binding.tvChallengeDate.text = item.term.toString()+"일"

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<DomainChallenge>) {
        this.items = newItems
        notifyDataSetChanged()
    }


    override fun getItemCount():Int{
        return items.size
    }

}