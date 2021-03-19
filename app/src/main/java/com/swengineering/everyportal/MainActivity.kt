package com.swengineering.everyportal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.swengineering.everyportal.navigation.course.LectureFragment
import com.swengineering.everyportal.navigation.home.HomeFragment
import com.swengineering.everyportal.navigation.notifications.NotificationFragment
import com.swengineering.everyportal.setting.ListSettingActivity
import com.swengineering.everyportal.utils.AppPreferenceManager
import com.swengineering.everyportal.viewmodel.MainViewModel
import com.swengineering.template.model.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var appDatabase: AppDatabase
        lateinit var mainViewModel: MainViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appDatabase = AppDatabase.getInstance(this)
        val preference = AppPreferenceManager
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        activity_main_bottomNavigation.setOnNavigationItemSelectedListener(
            onNavigationItemSelectedListener()
        )
        activity_main_bottomNavigation.selectedItemId = R.id.navigation_home
        activity_main_button_list_setting.setOnClickListener {
            startActivity(Intent(this, ListSettingActivity::class.java))
        }
        var dialog: ProgressBar = activity_main_progressBar
        dialog.visibility = View.GONE
        activity_main_button_menu.setOnClickListener(onClickMenuButtonListener())
        activity_main_menu_block.setOnClickListener(onClickMenuBlockListener())

    }


    private fun onClickMenuBlockListener(): View.OnClickListener? {
        return View.OnClickListener {
            activity_main_constraint.animate().translationX(0f).start()
            activity_main_constraint_menu.animate().translationX(0f).start()
            activity_main_menu_block.animate().translationX(0f).start()
        }
    }

    private fun onClickMenuButtonListener(): View.OnClickListener? {
        return View.OnClickListener {
            activity_main_constraint.animate().translationX(-700f).start()
            activity_main_constraint_menu.animate().translationX(-700f).start()
            activity_main_menu_block.animate().translationX(-1520f).start()
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun onNavigationItemSelectedListener(): BottomNavigationView.OnNavigationItemSelectedListener? {
        return BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_main_fragment, HomeFragment()).commitNow()
                    true
                }
                R.id.navigation_lecture -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_main_fragment, LectureFragment()).commitNow()
                    true
                }
                R.id.navigation_notifications -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_main_fragment, NotificationFragment()).commitNow()
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}