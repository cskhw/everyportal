package com.wiserock.heruri.utils.interfaces

import android.view.View
import com.wiserock.heruri.MainActivity
import com.wiserock.heruri.api.Value
import com.wiserock.heruri.model.course.Course
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.view.adapter.CourseAdapter
import com.wiserock.heruri.view.adapter.HomeworkAdapter
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
                        val professor =
                            courseResponse?.select("div.media-left.media-middle img")?.attr("alt")
                        try {
                            for (i in 2..coursesHref.size + 2) {
                                MyApp.courseArrayList.add(
                                    Course(
                                        id = id,
                                        name = names[i],
                                        done = checks[i - 2] == "O",
                                        href = coursesHref[i - 2],
                                        professor = professor!!,
                                        deadline = deadlines[i - 2]
                                    )
                                )
                            }
                        } catch (e: Exception) {
                            println("안녕하신가제트")
                        }
                    } catch (e: Exception) {
                        println("course의 id가 없음 ")
                    }
                }
                //element 끝
                withContext(Dispatchers.Main) {
                    CourseAdapter.itemSize = MyApp.courseArrayList.size
                    MyApp.courseArrayList.reverse()
                    HomeworkAdapter.viewModel.courseList.value = MyApp.courseArrayList
                    MainActivity.dialog.visibility = View.GONE
                    println("loadCourse finished")
                }
            }
        }
    }
}