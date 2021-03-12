package com.wiserock.heruri.navigation.course

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.wiserock.heruri.MainActivity
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.FragmentLectureBinding
import com.wiserock.heruri.view.adapter.CourseViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_lecture.*
import kotlinx.android.synthetic.main.fragment_lecture.view.*

class LectureFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val binding: FragmentLectureBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_lecture, container, false)
        val view = binding.root
        MainActivity.dialog.visibility = View.VISIBLE
        val viewPager = view.fragment_lecture_viewPager
        viewPager.offscreenPageLimit = 2
        val fragmentManager = childFragmentManager
        val adapter = CourseViewPagerAdapter(fragmentManager, 2)
        viewPager.adapter = adapter
        return binding.root
    }
}