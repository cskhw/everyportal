package com.swengineering.everyportal.view.adapter.recyclers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.swengineering.everyportal.R
import com.swengineering.everyportal.databinding.ItemRankingBinding
import com.swengineering.everyportal.navigation.home.HomeFragment.Companion.homeViewModel
import kotlinx.android.synthetic.main.item_ranking.view.*

class RankingRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemRankingBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_ranking,
                parent,
                false
            )
        return RankingViewHolder(binding)
    }

    inner class RankingViewHolder(val binding: ItemRankingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val view = binding.root

        @SuppressLint("SetTextI18n")
        fun bind(pos: Int) {
            val id = homeViewModel.naverRanking.value?.get(pos)?.id.toString() ?: ""
            val title = homeViewModel.naverRanking.value?.get(pos)?.title ?: ""
            view.item_ranking_title.text = "$id. $title"
            binding.pos = pos
            binding.home = homeViewModel
            binding.executePendingBindings()
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as RankingViewHolder
        viewHolder.bind(position)
    }

    override fun getItemCount(): Int = homeViewModel.naverRanking.value?.size ?: 0
}