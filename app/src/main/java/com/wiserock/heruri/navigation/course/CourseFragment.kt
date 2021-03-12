package com.wiserock.heruri.navigation.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.wiserock.heruri.MainActivity
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.FragmentCourseBinding
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.view.adapter.CourseAdapter
import com.wiserock.heruri.view.adapter.HomeworkAdapter
import kotlinx.android.synthetic.main.fragment_course.view.*

class CourseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCourseBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_course, container, false)
        val view = binding.root
        val homeworkViewModel = HomeworkAdapter.viewModel
        val recyclerView = view.fragment_course_recycler
        recyclerView.itemAnimator = DefaultItemAnimator()
        homeworkViewModel.courseList.value = MyApp.courseArrayList
        homeworkViewModel.courseList.observe(viewLifecycleOwner, Observer {
            MainActivity.dialog.visibility = View.GONE
            recyclerView.adapter?.notifyDataSetChanged()
            recyclerView.adapter = CourseAdapter()
        })

        return view
    }
}