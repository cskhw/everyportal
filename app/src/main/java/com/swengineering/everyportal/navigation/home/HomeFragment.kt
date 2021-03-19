package com.swengineering.everyportal.navigation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.swengineering.everyportal.MainActivity.Companion.mainViewModel
import com.swengineering.everyportal.R
import com.swengineering.everyportal.databinding.FragmentHomeBinding
import com.swengineering.everyportal.utils.AppPreferenceManager
import com.swengineering.everyportal.view.adapter.viewpagers.HomeViewpagerAdapter

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
        //get naver ranking
        homeViewModel.getNaverRankings()

        //tabs setting
        val tabs: TabLayout = view.findViewById(R.id.fragment_home_tablayout)
        val viewpager: ViewPager2 = view.findViewById(R.id.fragment_home_viewpager)
        viewpager.adapter = HomeViewpagerAdapter(childFragmentManager, lifecycle)
        val preference = AppPreferenceManager
        var rankingString: String? = preference.getString(context, "rankingList")
        println("rankingString: $rankingString")
        var tabTexts: List<String>? = rankingString?.split(",")
        println("@@@@: $tabTexts")
        mainViewModel.checksSize.value = tabTexts?.size ?: 0
        TabLayoutMediator(tabs, viewpager) { tab, position ->
            tab.text = tabTexts?.get(position)
        }.attach()
        // 메인 뷰모델의 checks 가 바뀌면 실행됨
        mainViewModel.checks.observe(viewLifecycleOwner, Observer {
            rankingString = preference.getString(context, "rankingList")
            tabTexts = rankingString?.split(",")
            println("!!!! $tabTexts")
            mainViewModel.checksSize.value = tabTexts?.size ?: 0
            if (rankingString != "" && rankingString != null) {
                TabLayoutMediator(tabs, viewpager) { tab, position ->
                    tab.text = tabTexts?.get(position)
                }.attach()
            }
        })
        return binding.root
    }
}
