package com.swengineering.everyportal.navigation.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.swengineering.everyportal.R

class NotificationFragment : Fragment() {

    private lateinit var notificationViewModel: NotificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationViewModel =
            ViewModelProvider(this).get(NotificationViewModel::class.java)
        val binding: com.swengineering.everyportal.databinding.FragmentNotificationBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false)
        val view = binding.root
        return view
    }
}