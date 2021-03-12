package com.wiserock.heruri.utils.interfaces

import android.content.Context
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.view.adapter.PushAdapter
import com.wiserock.template.model.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface LoadPush {
    fun loadPush(context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            val appDatabase = AppDatabase.getInstance(context)
            MyApp.pushArrayList = appDatabase.pushDAO().getAll() as ArrayList
            PushAdapter.itemSize = MyApp.pushArrayList.size
            println("loadPush finished")
        }
    }
}