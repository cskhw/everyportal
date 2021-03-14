package com.swengineering.everyportal.utils.interfaces

import com.swengineering.everyportal.api.Value
import com.swengineering.everyportal.utils.MyApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
            }
            println("made by wiseRock")
        }
    }
}