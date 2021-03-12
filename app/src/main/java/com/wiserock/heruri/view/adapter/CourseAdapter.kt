package com.wiserock.heruri.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.ItemCourseBinding
import com.wiserock.heruri.navigation.course.LectureViewModel
import kotlinx.android.synthetic.main.item_homework.view.*

class CourseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        var itemSize = 0
        lateinit var viewModel: LectureViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemCourseBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_course,
            parent,
            false
        )
        return CourseViewHolder(binding)
    }

    inner class CourseViewHolder(private val binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.pos = position
            binding.course = viewModel
            binding.executePendingBindings()
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as CourseViewHolder
        val view = holder.itemView
        viewHolder.setIsRecyclable(false)
        viewHolder.bind(position)

        try {
            if (viewModel.courseList.value?.get(position)?.done == true) {
                view.setBackgroundResource(R.color.white)
                view.item_course_text1.setTextColor(R.color.black)
                view.item_course_text2.setTextColor(R.color.black)
                view.item_course_text3.setTextColor(R.color.black)
                notifyDataSetChanged()
            }
        } catch (e: Exception) {
        }
    }

    override fun getItemCount(): Int = itemSize
}