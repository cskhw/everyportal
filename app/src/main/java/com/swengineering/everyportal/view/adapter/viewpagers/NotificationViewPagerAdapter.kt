package com.swengineering.everyportal.view.adapter.viewpagers

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.swengineering.everyportal.navigation.notifications.NotificationFragment

class NotificationViewPagerAdapter(val fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NotificationFragment()
            else -> NotificationFragment()
        }
    }
}

