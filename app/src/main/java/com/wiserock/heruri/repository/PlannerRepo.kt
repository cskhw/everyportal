package com.wiserock.heruri.repository

import com.wiserock.heruri.model.Homework
import com.wiserock.heruri.model.course.Course
import com.wiserock.heruri.navigation.planner.PlannerFragment.Companion.plannerViewModel
import com.wiserock.heruri.utils.MyApp.mainContext
import com.wiserock.template.model.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlannerRepo {
    val context = mainContext
    private val database = AppDatabase.getInstance(context)

    fun getPlan() {
        val dao = database.dayDAO()
        CoroutineScope(Dispatchers.IO).launch {
            println("init dayWithPlan")
            val temp = dao.getAllPlan()
            withContext(Dispatchers.Main) {
                plannerViewModel.plan.value = temp
                println("plannerViewModel.plan.value = ${plannerViewModel.plan.value}")
            }
        }
    }
}