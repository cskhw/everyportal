package com.wiserock.heruri.utils.interfaces

import androidx.lifecycle.LifecycleOwner
import com.wiserock.heruri.api.Value
import com.wiserock.heruri.model.Homework
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.view.adapter.HomeworkAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Connection
import org.jsoup.nodes.Document
import java.util.*

interface LoadHomework : OnClickListener {
    fun loadHomework(life: LifecycleOwner) {
        val elements = MyApp.html.select(".course_link")
        val numberRegex = "[0-9]{1,10}".toRegex()
        val homeworkRegex =
            """mod/assign/view.php\?id=[0-9]{1,10}""".toRegex()
        val homeworkIds: HashSet<Int> = hashSetOf()
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                elements.forEach { element ->
                    val id = numberRegex.find(element.attr("href"))?.value?.toInt()
                    val courseResponse =
                        MyApp.getResponseWithUrl(
                            Value.BASE_URL + "course/view.php?id=${id}",
                            Connection.Method.GET
                        )?.parse()
                    courseResponse?.select("a")?.forEach {
                        val temp = it.attr("href")
                        temp()
                        val value = homeworkRegex.find(temp)?.value
                        if (value != null) {
                            val homeworkId = numberRegex.find(value.toString())?.value?.toInt()!!
                            homeworkIds.add(homeworkId)
                        }
                    }
                    courseResponse?.select("#section-0")?.forEach {
                        try {
                            homeworkIds.add(numberRegex.find("temp")?.value?.toInt()!!)
                        } catch (e: Exception) {
                            println("과제가 없다람쥐")
                        }
                    }
                }
            }
            val temp = ArrayList(homeworkIds)
            temp.sort()
            temp.reverse()
            MyApp.homeworkIds = temp
            HomeworkAdapter.itemSize = temp.size
            withContext(Dispatchers.IO) {
                temp.forEach {
                    var done = false
                    var homeworkResponse: Document?
                    withContext(Dispatchers.IO) {
                        homeworkResponse = MyApp.getResponseWithUrl(
                            Value.BASE_URL + "mod/assign/view.php?id=$it",
                            Connection.Method.GET
                        )?.parse()
                    }
                    val course = homeworkResponse?.select("div.coursename h1")?.text()
                    val name =
                        homeworkResponse?.select("li a[title=과제]")?.text()
                    val check =
                        homeworkResponse?.select("td.cell.c1.lastcol")?.get(1)?.text()
                    if (check == "제출 완료") done = true
                    val deadline =
                        homeworkResponse?.select("tr td.cell.c1.lastcol")?.get(3)
                            ?.text()
                    MyApp.homeworkArrayList.add(
                        Homework(
                            id = it.toLong(),
                            course = course!!,
                            name = name!!,
                            done = done,
                            deadline = deadline!!,
                            href = Value.BASE_URL + "mod/assign/view.php?id=$it"
                        )
                    )
                }
            }
            withContext(Dispatchers.Main) {
                HomeworkAdapter.viewModel.homeworkList.value = MyApp.homeworkArrayList
                MyApp.loading.value = true
            }
            println("loadHomework finished")
        }
    }
}



