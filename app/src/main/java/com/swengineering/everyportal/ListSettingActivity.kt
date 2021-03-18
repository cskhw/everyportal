package com.swengineering.everyportal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_list_setting.*

class ListSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_setting)
        activity_list_setting_button_back.setOnClickListener { finish() }
        
    }
}