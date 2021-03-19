package com.swengineering.everyportal.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.swengineering.everyportal.R
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        activity_setting_button_back.setOnClickListener {
            finish()
            println("made by wiseRock")
        }
    }
}