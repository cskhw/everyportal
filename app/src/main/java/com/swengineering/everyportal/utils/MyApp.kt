package com.swengineering.everyportal.utils

import android.app.Application
import androidx.lifecycle.MutableLiveData
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Response

object MyApp : Application() {
    lateinit var html: Document
    lateinit var index: Connection.Response
    var cookies: Map<String, String> = mapOf()
    var loading: MutableLiveData<Boolean> = MutableLiveData(false)
    var homeworkIds: ArrayList<Int> = arrayListOf()
    var temp: ArrayList<String> = arrayListOf()

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