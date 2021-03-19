package com.swengineering.everyportal.setting

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.swengineering.everyportal.MainActivity.Companion.mainViewModel
import com.swengineering.everyportal.R
import com.swengineering.everyportal.utils.AppPreferenceManager
import kotlinx.android.synthetic.main.activity_list_setting.*

class ListSettingActivity : AppCompatActivity() {
    var checks: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_setting)
        val preference = AppPreferenceManager
        val naver = activity_list_setting_checkbox_naver
        val daum = activity_list_setting_checkbox_daum
        val zum = activity_list_setting_checkbox_zum
        val google = activity_list_setting_checkbox_google
        activity_list_setting_button_back.setOnClickListener { finish() }
        activity_list_setting_linear_naver.setOnClickListener { naver.isChecked = !naver.isChecked }
        activity_list_setting_linear_daum.setOnClickListener { daum.isChecked = !daum.isChecked }
        activity_list_setting_linear_zum.setOnClickListener { zum.isChecked = !zum.isChecked }
        activity_list_setting_linear_google.setOnClickListener {
            google.isChecked = !google.isChecked
        }
        activity_list_setting_button_finish.setOnClickListener {
            println("설정 완료 버튼 클릭")
            if (activity_list_setting_checkbox_naver.isChecked) checks += "naver,"
            if (activity_list_setting_checkbox_daum.isChecked) checks += "daum,"
            if (activity_list_setting_checkbox_zum.isChecked) checks += "zum,"
            if (activity_list_setting_checkbox_google.isChecked) checks += "google,"
            checks = checks.substring(0, checks.length - 1)
            mainViewModel.checks.value = checks
            println("check: $checks")
            preference.setString(this, "rankingList", checks)
            Toast.makeText(this, "목록이 설정되었습니다.", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}