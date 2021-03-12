package com.wiserock.heruri.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.ItemPlanBinding
import com.wiserock.heruri.navigation.planner.PlannerViewModel

class PlanAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        var itemSize = 0
        lateinit var planAdapterViewModel: PlannerViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemPlanBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_plan,
            parent,
            false
        )
        return PlanViewHolder(binding)
    }

    inner class PlanViewHolder(val binding: ItemPlanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.pos = position
            binding.plan = planAdapterViewModel
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int = itemSize

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as PlanViewHolder
        val view = holder.itemView
        viewHolder.bind(position)
    }
}