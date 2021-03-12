package com.wiserock.heruri.view.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.ItemDayBinding
import com.wiserock.heruri.model.Homework
import com.wiserock.heruri.model.course.Course
import com.wiserock.heruri.navigation.planner.PlannerViewModel
import com.wiserock.heruri.view.adapter.PlanAdapter.Companion.planAdapterViewModel
import kotlinx.android.synthetic.main.item_day.view.*

class DayAdapter(val viewLifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        var itemSize = 0
        lateinit var dayAdapterViewModel: PlannerViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemDayBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_day,
                parent,
                false
            )
        val dp = binding.root.resources.displayMetrics.widthPixels
        binding.root.layoutParams = LinearLayout.LayoutParams(
            dp / 7, dp / 4 + 20
        )
        return PlannerViewHolder(binding)
    }

    override fun getItemCount(): Int = itemSize

    inner class PlannerViewHolder(val binding: ItemDayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val recycler: RecyclerView = binding.root.item_plan_recycler
        fun bind(position: Int) {
            binding.planner = dayAdapterViewModel
            binding.pos = position
            binding.executePendingBindings()
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as PlannerViewHolder
        val view = holder.itemView
        if (position % 7 == 0) view.item_day_day.setTextColor(Color.parseColor("#FF0000"))
        if (position % 7 == 6) view.item_day_day.setTextColor(Color.parseColor("#0000FF"))
        viewHolder.bind(position)
        val recycler = viewHolder.recycler
        val temp = dayAdapterViewModel.dayArrayList.value?.get(position)

        // 플랜 값을 가져온 후 각 데이에 초기화
        planAdapterViewModel.plan.observe(viewLifecycleOwner, Observer { list ->
            list.forEach {
                if (it.day?.time == temp?.day?.time) {
                    temp?.courseList = it.courseList
                    temp?.homeworkList = it.homeworkList
                    println("it.day?.time값이 등록되었습니다. = ${it.day?.time}")
                    println(
                        "dayAdapterViewModel.dayArrayList.value?.get(position) = ${dayAdapterViewModel.dayArrayList.value?.get(
                            position
                        )?.courseList}"
                    )

                    dayAdapterViewModel.planArrayList.value?.get(position)
                        ?.addAll(temp?.courseList as Iterable<Any>)
                    if (temp != null) {
                        dayAdapterViewModel.planArrayList.value?.get(position)
                            ?.addAll(temp.homeworkList as ArrayList<*>)
                    }
                    println(
                        "dayAdapterViewModel.planArrayList[position] = ${dayAdapterViewModel.planArrayList.value?.get(
                            position
                        )}"
                    )
                }
            }
            dayAdapterViewModel.position.observe(viewLifecycleOwner, Observer {
                recycler.adapter?.notifyDataSetChanged()
                recycler.adapter = PlanAdapter()
            })
        })
    }
}
