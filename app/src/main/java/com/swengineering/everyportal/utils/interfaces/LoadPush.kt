package com.swengineering.everyportal.utils.interfaces

import android.content.Context
import com.swengineering.template.model.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface LoadPush {
    fun loadPush(context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            val appDatabase = AppDatabase.getInstance(context)
            println("loadPush finished")
        }
    }
}