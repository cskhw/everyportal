package com.wiserock.heruri.utils

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.wiserock.heruri.model.Homework
import com.wiserock.heruri.model.Notification
import com.wiserock.heruri.model.course.Course
import com.wiserock.heruri.model.push.Push
import com.wiserock.heruri.navigation.course.LectureViewModel
import com.wiserock.heruri.view.adapter.CourseAdapter
import com.wiserock.heruri.view.adapter.HomeworkAdapter
import com.wiserock.heruri.view.adapter.NotificationAdapter
import com.wiserock.heruri.view.adapter.PushAdapter
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Response

object MyApp : Application() {
    lateinit var html: Document
    lateinit var index: Connection.Response
    lateinit var cookies: Map<String, String>
    var loading: MutableLiveData<Boolean> = MutableLiveData(false)
    var homeworkIds: ArrayList<Int> = arrayListOf()
    var homeworkArrayList: ArrayList<Homework> = arrayListOf()
    var courseArrayList: ArrayList<Course> = arrayListOf()
    var notificationArrayList: ArrayList<Notification> = arrayListOf()
    var pushArrayList: ArrayList<Push> = arrayListOf()
    lateinit var mainContext: Context

    fun init(viewModel: LectureViewModel) {
        HomeworkAdapter.viewModel = viewModel
        CourseAdapter.viewModel = viewModel
        NotificationAdapter.viewModel = viewModel
        PushAdapter.viewModel = viewModel
        homeworkArrayList = arrayListOf()
        courseArrayList = arrayListOf()
        notificationArrayList = arrayListOf()
        viewModel.courseList.value = arrayListOf()
        viewModel.homeworkList.value = arrayListOf()
        viewModel.notificationList.value = arrayListOf()
    }

    fun getResponseWithUrl(
        url: String,
        method: Connection.Method,
        formData: HashMap<String, String>? = hashMapOf()
    ): Connection.Response? {
        return Jsoup.connect(url)
            .data(formData)
            .cookies(cookies)
            .method(method)
            .userAgent("Android")
            .execute()
    }

    fun <T> debugResponse(response: Response<T>) {
        println("\n================Start Response Debug==================")
        println("response.body() = ${response.body()}")
        println("response.code() = ${response.code()}")
        println("response.message() = ${response.message()}")
        println("response.errorBody() = ${response.errorBody()}")
        println("response.headers() = ${response.headers()}")
        println("response.raw() = ${response.raw()}")
        println("================End Response Debug==================")
    }
}