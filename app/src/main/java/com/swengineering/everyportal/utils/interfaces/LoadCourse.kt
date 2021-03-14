package com.swengineering.everyportal.utils.interfaces

import com.swengineering.everyportal.api.Value
import com.swengineering.everyportal.utils.MyApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Connection

interface LoadCourse {
    fun loadCourse() {
        val elements = MyApp.html.select(".course_link")
        val regex = "[0-9]{1,10}".toRegex()
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                elements.forEach { element ->
                    var id: Long
                    try {
                        id = regex.find(element.attr("href"))?.value?.toLong()!!

                        val reportResponse =
                            MyApp.getResponseWithUrl(
                                Value.BASE_URL + "report/ubcompletion/user_progress_a.php?id=${id}",
                                Connection.Method.GET
                            )?.parse()
                        val names: ArrayList<String> = arrayListOf()
                        reportResponse?.select("td.text-left")?.forEach {
                            names.add(it.text())
                        }
                        val checks: ArrayList<String> = arrayListOf()
                        reportResponse?.select("tr")?.forEach {
                            val checkElements = it.select("td.text-center")
                            when (checkElements.size) {
                                5 -> {
                                    checks.add(checkElements[3].text())
                                }
                                3 -> {
                                    checks.add(checkElements.last().text())
                                }
                                else -> {
                                }
                            }
                        }

                        val courseResponse = MyApp.getResponseWithUrl(
                            url = Value.BASE_URL + "course/view.php?id=$id",
                            method = Connection.Method.GET
                        )?.parse()
                        val coursesHref: ArrayList<String> = arrayListOf()
                        val deadlines: ArrayList<String> = arrayListOf()
                        val courseRegex =
                            """http://eruri.kangwon.ac.kr/mod/vod/viewer.php\?id=[0-9]{1,10}""".toRegex()
                        courseResponse?.select("div.total_sections div.activityinstance")?.forEach {
                            val course = courseRegex.find(it.toString())?.value
                            val deadlineString = it.select("span.text-ubstrap").text()
                            try {
                                val deadline = deadlineString.substring(22, 41)
                                deadlines.add(deadline)
                            } catch (e: Exception) {
                            }
                            if (course != "" && course != null) coursesHref.add(course)
                        }
                    } catch (e: Exception) {
                    }
                }
                //element 끝
            }
        }
    }
}