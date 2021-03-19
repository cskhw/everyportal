package com.swengineering.everyportal.setting

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.swengineering.everyportal.MainActivity.Companion.mainViewModel
import com.swengineering.everyportal.R
import com.swengineering.everyportal.utils.AppPreferenceManager
import kotlinx.android.synthetic.main.activity_list_setting.*

class ListSettingActivity : AppCompatActivity() {
    private var checks: String = ""
    private val preference = AppPreferenceManager
    private val tabsString = arrayListOf("네이버", "다음", "줌", "구글")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_setting)
        val tabsList = arrayListOf<CheckBox>(
            activity_list_setting_checkbox_naver,
            activity_list_setting_checkbox_daum,
            activity_list_setting_checkbox_zum,
            activity_list_setting_checkbox_google
        )
        val linearList = arrayListOf<LinearLayout>(
            activity_list_setting_linear_naver,
            activity_list_setting_linear_daum,
            activity_list_setting_linear_zum,
            activity_list_setting_linear_google
        )

        // 탭 리스트 설정
        setRankingList(tabsList)
        // 리니어 레이아웃에 리스터 추가
        activity_list_setting_button_back.setOnClickListener { finish() }
        (0..3).forEach { i ->
            linearList[i].setOnClickListener {
                tabsList[i].isChecked = !tabsList[i].isChecked
            }
        }
        activity_list_setting_button_finish.setOnClickListener(onClickFinishButton(tabsList))
    }

    private fun onClickFinishButton(
        tabsList: ArrayList<CheckBox>
    ): View.OnClickListener = View.OnClickListener { v ->
        if (tabsList[0].isChecked) checks += "네이버,"
        if (tabsList[1].isChecked) checks += "다음,"
        if (tabsList[2].isChecked) checks += "줌,"
        if (tabsList[3].isChecked) checks += "구글,"
        checks = checks.substring(0, checks.length - 1)
        mainViewModel.checks.value = checks
        preference.setString(v.context, "rankingList", checks)
        Toast.makeText(v.context, "목록이 설정되었습니다.", Toast.LENGTH_LONG).show()
        finish()
    }

    private fun setRankingList(tabsList: ArrayList<CheckBox>) {
        val tabList: List<String> =
            preference.getString(this, "rankingList")?.split(',') ?: listOf()
        try {
            var i = 0
            tabsString.forEach { a ->
                tabList.forEach { b ->
                    if (a == b)
                        when (i) {
                            0 -> tabsList[0].isChecked = true
                            1 -> tabsList[1].isChecked = true
                            2 -> tabsList[2].isChecked = true
                            3 -> tabsList[3].isChecked = true
                        }
                }
                i++
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}