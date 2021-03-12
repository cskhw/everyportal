package com.wiserock.heruri

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.wiserock.heruri.api.Api
import com.wiserock.heruri.api.Value
import com.wiserock.heruri.navigation.course.LectureViewModel
import com.wiserock.heruri.utils.AppPreferenceManager
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.utils.interfaces.LoadCourse
import com.wiserock.heruri.utils.interfaces.LoadHomework
import com.wiserock.heruri.utils.interfaces.LoadNotification
import com.wiserock.heruri.utils.interfaces.LoadPush
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Connection
import org.jsoup.Jsoup

class LoginActivity : AppCompatActivity(), LoadHomework, LoadCourse, LoadNotification, LoadPush {
    private var username: String = ""
    private var password: String = ""
    private val userAgent =
        "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36"

    @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Api.update(Value.BASE_URL)
        val viewModel =
            ViewModelProvider(this).get(LectureViewModel::class.java)
        val loginUrl = Value.BASE_URL + "login/index.php"
        MyApp.init(viewModel)

        activity_login_button.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {

                username = activity_login_username.text.toString()
                password = activity_login_password.text.toString()

                val preference = AppPreferenceManager

                preference.setString(applicationContext, "username", username)
                preference.setString(applicationContext, "password", password)

                val formData: HashMap<String, String> = hashMapOf()

                formData["username"] = username
                formData["password"] = password
                MyApp.index = Jsoup.connect(loginUrl)
                    .data(formData)
                    .method(Connection.Method.POST)
                    .userAgent(userAgent)
                    .execute()
                val homepageHtml = MyApp.index.parse()
                MyApp.html = homepageHtml
                MyApp.cookies = MyApp.index.cookies()

                try {
                    homepageHtml.select("div.main_login_find_idpw").first().text()!!
                    GlobalScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "아이디 혹은 비밀번호가 잘못되었습니다.",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                } catch (e: NullPointerException) {
                    val temp = homepageHtml.select("div.user-info-picture").select("h4").text()
                    preference.setString(applicationContext, "name", temp)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    loadPush(this@LoginActivity)
                    loadCourse()
                    loadHomework(this@LoginActivity)
                    loadNotification()
                    finish()
                }
            }
        }
    }
}
