package com.wiserock.heruri.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.wiserock.heruri.navigation.course.CourseFragment
import com.wiserock.heruri.navigation.course.HomeworkFragment

class CourseViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val size: Int
) : FragmentStatePagerAdapter(fragmentManager, size) {
    override fun getCount(): Int = size

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CourseFragment()
            1 -> HomeworkFragment()
            else -> CourseFragment()
        }
    }
}