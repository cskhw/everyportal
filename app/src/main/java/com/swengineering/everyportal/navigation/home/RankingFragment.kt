package com.swengineering.everyportal.navigation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.swengineering.everyportal.R
import com.swengineering.everyportal.databinding.FragmentRankingBinding
import com.swengineering.everyportal.navigation.home.HomeFragment.Companion.homeViewModel
import com.swengineering.everyportal.utils.AppPreferenceManager
import com.swengineering.everyportal.view.adapter.recyclers.RankingRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_ranking.view.*


class RankingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentRankingBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_ranking, container, false)
        val view = binding.root
        val recycler = view.fragment_ranking_recycler
        val preference = AppPreferenceManager
        val check = preference.getString(context, "check")
        homeViewModel.naverRanking.observe(viewLifecycleOwner, Observer {
            println("init naver ranking adapter")
            if (check != "" && check != null) {
                binding.fragmentRankingText1.visibility = View.GONE
                recycler.adapter?.notifyDataSetChanged()
                recycler.adapter = RankingRecyclerAdapter()
                println("rankings: ${homeViewModel.naverRanking.value}")
            }
        })
        return view
    }
}