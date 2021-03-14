package com.swengineering.everyportal.navigation.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.swengineering.everyportal.R
import com.swengineering.everyportal.databinding.FragmentHomeworkBinding
import kotlinx.android.synthetic.main.fragment_homework.view.*

class HomeworkFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeworkBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_homework, container, false)
        val view = binding.root
        val progressBar = view.fragment_homework_progressBar
        progressBar.visibility = View.VISIBLE
        val recyclerView = view.fragment_homework_recycler
        recyclerView.itemAnimator = DefaultItemAnimator()
        return view
    }
}