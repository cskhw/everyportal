package com.wiserock.heruri

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.iid.FirebaseInstanceId
import com.wiserock.heruri.api.Value
import com.wiserock.heruri.navigation.course.LectureViewModel
import com.wiserock.heruri.utils.AppPreferenceManager
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.utils.interfaces.LoadCourse
import com.wiserock.heruri.utils.interfaces.LoadHomework
import com.wiserock.heruri.utils.interfaces.LoadNotification
import com.wiserock.heruri.utils.interfaces.LoadPush
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Connection
import org.jsoup.Jsoup

class SplashActivity : AppCompatActivity(), LoadHomework, LoadCourse,
    LoadNotification, LoadPush {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val viewModel =
            ViewModelProvider(this).get(LectureViewModel::class.java)
        MyApp.init(viewModel)

        val preference = AppPreferenceManager
        println("made by wiseRock")
        val formData: HashMap<String, String> = hashMapOf()

        GlobalScope.launch(Dispatchers.IO) {
            MyApp.index = Jsoup.connect(loginUrl)
                .data(formData)
                .method(Connection.Method.POST)
                .userAgent("Android")
                .execute()
            val homepageHtml = MyApp.index.parse()
            MyApp.html = homepageHtml
            MyApp.cookies = MyApp.index.cookies()
            try {
                homepageHtml.select("div.main_login_find_idpw").first().text()!!
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            } catch (e: NullPointerException) {
                withContext(Dispatchers.Main) {
                    MyApp.loading.observe(this@SplashActivity, Observer {
                        val temp = homepageHtml.select("div.user-info-picture").select("h4").text()
                        preference.setString(applicationContext, "name", temp)
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    })
                    loadPush(this@SplashActivity)
                    loadCourse()
                    loadHomework(this@SplashActivity)
                    loadNotification()
                    println("코드를 보러 오셨군요.")
                }
            }
        }
    }
}
