package com.wiserock.heruri.navigation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.wiserock.heruri.MainActivity
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.FragmentNotificationBinding
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.view.adapter.HomeworkAdapter
import com.wiserock.heruri.view.adapter.NotificationAdapter
import kotlinx.android.synthetic.main.fragment_notification.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val binding: FragmentNotificationBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false)
        val view = binding.root
        val homeworkViewModel = HomeworkAdapter.viewModel
        val recyclerView = view.fragment_home_recycler
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter?.notifyDataSetChanged()
        recyclerView.adapter = NotificationAdapter()
        homeworkViewModel.notificationList.value = MyApp.notificationArrayList
        homeworkViewModel.notificationList.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter?.notifyDataSetChanged()
            recyclerView.adapter = NotificationAdapter()
            MainActivity.dialog.visibility = View.GONE
        })

        return binding.root
    }
}