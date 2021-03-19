package com.swengineering.everyportal.view.adapter.viewpagers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.swengineering.everyportal.MainActivity.Companion.mainViewModel
import com.swengineering.everyportal.navigation.home.RankingFragment

class HomeViewpagerAdapter(fragmentManager: FragmentManager, life: Lifecycle) :
    FragmentStateAdapter(fragmentManager, life) {
    override fun getItemCount(): Int = mainViewModel.checksSize.value ?: 0

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RankingFragment()
            else -> RankingFragment()
        }
    }
}