package com.wiserock.heruri.navigation.notifications

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.wiserock.heruri.databinding.FragmentPushBinding
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.view.adapter.CourseAdapter
import com.wiserock.heruri.view.adapter.PushAdapter
import kotlinx.android.synthetic.main.fragment_push.view.*

class PushFragment : Fragment() {

    private lateinit var notificationsViewModel: PushViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(PushViewModel::class.java)
        val binding: FragmentPushBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_push, container, false)
        val view = binding.root
        MainActivity.dialog.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            MainActivity.dialog.visibility = View.GONE
        }, 2000)
        val recyclerView = view.fragment_push_recycler
        recyclerView.itemAnimator = DefaultItemAnimator()
        CourseAdapter.viewModel.pushList.value = MyApp.pushArrayList
        CourseAdapter.viewModel.pushList.observe(
            viewLifecycleOwner,
            Observer {
                recyclerView.animate().setDuration(200).alpha(0.3f).withEndAction {
                    PushAdapter.itemSize = MyApp.pushArrayList.size
                    recyclerView.adapter = PushAdapter()
                    recyclerView.animate().setDuration(100).alpha(1f).start()
                }.start()
                MainActivity.dialog.visibility = View.GONE
            }
        )
        return view
    }
}