package com.swengineering.everyportal.navigation.home

import HomeViewpagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.swengineering.everyportal.R
import com.swengineering.everyportal.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    companion object {
        lateinit var homeViewModel: HomeViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //get viewModel
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val view = binding.root
        homeViewModel.getNaverRankings()
        
        //tabs setting
        val tabs: TabLayout = view.findViewById(R.id.fragment_home_tablayout)
        val viewpager: ViewPager2 = view.findViewById(R.id.fragment_home_viewpager)
        viewpager.adapter = HomeViewpagerAdapter(childFragmentManager, lifecycle)
        val tabTexts: ArrayList<String> = arrayListOf("네이버", "다음", "줌")
        TabLayoutMediator(tabs, viewpager) { tab, position ->
            tab.text = tabTexts[position]
        }.attach()
        return binding.root
    }
}
