package com.wiserock.heruri.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.ItemPushBinding
import com.wiserock.heruri.navigation.course.LectureViewModel

class PushAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        var itemSize = 0
        lateinit var viewModel: LectureViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemPushBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_push, parent, false
        )
        return PushViewHolder(binding)
    }

    inner class PushViewHolder(val binding: ItemPushBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.executePendingBindings()
            binding.course = viewModel
            binding.pos = position
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as PushViewHolder
        viewHolder.bind(position)
    }

    override fun getItemCount(): Int = itemSize
}
