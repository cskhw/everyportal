package com.wiserock.heruri.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.ItemNotificationBinding
import com.wiserock.heruri.navigation.course.LectureViewModel

class NotificationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        lateinit var viewModel: LectureViewModel
        var itemSize = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemNotificationBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_notification,
            parent,
            false
        )
        return NotificationViewHolder(binding)
    }


    inner class NotificationViewHolder(private val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.course = viewModel
            binding.pos = position
            binding.executePendingBindings()
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as NotificationViewHolder
        val view = holder.itemView
        viewHolder.bind(position)
    }

    override fun getItemCount(): Int = itemSize
}