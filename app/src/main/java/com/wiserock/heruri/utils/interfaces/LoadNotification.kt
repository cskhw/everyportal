package com.wiserock.heruri.utils.interfaces

import com.wiserock.heruri.api.Value
import com.wiserock.heruri.model.Notification
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.view.adapter.HomeworkAdapter
import com.wiserock.heruri.view.adapter.NotificationAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Connection

interface LoadNotification {
    fun loadNotification() {
        var pageNumber = 1
        GlobalScope.launch(Dispatchers.IO) {
            val notificationResponse = MyApp.getResponseWithUrl(
                url = Value.BASE_URL + "local/ubnotification/index.php?page=$pageNumber",
                method = Connection.Method.GET
            )?.parse()
            notificationResponse?.select("div.media")?.forEach {
                val name = it.select("h4.media-heading").text()
                val time = it.select("p.timeago").text()
                val description = it.select("p").text()
                val href = it.select("a").attr("href")
                MyApp.notificationArrayList.add(
                    Notification(
                        name = name,
                        time = time,
                        description = description,
                        href = href
                    )
                )
            }
            NotificationAdapter.itemSize = MyApp.notificationArrayList.size
            withContext(Dispatchers.Main) {
                HomeworkAdapter.viewModel.notificationList.value = MyApp.notificationArrayList
            }
            println("made by wiseRock")
        }
    }
}