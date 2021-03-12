package com.wiserock.heruri.navigation.planner

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wiserock.heruri.R
import com.wiserock.heruri.model.Homework
import com.wiserock.heruri.model.course.Course
import com.wiserock.heruri.model.day.Day
import com.wiserock.heruri.model.day.DayWithPlan
import com.wiserock.template.model.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlannerViewModel : ViewModel() {
    val dayArrayList: MutableLiveData<ArrayList<DayWithPlan>> = MutableLiveData()
    val year: MutableLiveData<String> = MutableLiveData()
    val month: MutableLiveData<String> = MutableLiveData()
    val plan: MutableLiveData<List<DayWithPlan>> = MutableLiveData()
    var courseList: ArrayList<Course> = arrayListOf()
    val homeworkList: ArrayList<Homework> = arrayListOf()
    val planArrayList: MutableLiveData<ArrayList<ArrayList<Any>>> = MutableLiveData()
    val position: MutableLiveData<Int> = MutableLiveData()

    fun onClickDayButton(context: Context, day: DayWithPlan) {
        val builder = AlertDialog.Builder(context)
        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getInstance(context)
            val dao = database.dayDAO()
        }

        val view = LayoutInflater.from(context).inflate(R.layout.dialog_day, null)
        val dialog = builder
            .setTitle("일정")
            .setView(view)
            .create()
        val appDatabase: AppDatabase = AppDatabase.getInstance(context)
        val dayDAO = appDatabase.dayDAO()
    }

    fun setTitle(position1: Int): String {
        val temp = planArrayList.value?.get(position.value!!)?.get(position1)
        return if (temp is Course) temp.name else (temp as Homework).course
    }
}