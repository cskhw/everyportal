package com.wiserock.heruri.navigation.course

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wiserock.heruri.MainActivity
import com.wiserock.heruri.model.course.Course
import com.wiserock.heruri.model.Homework
import com.wiserock.heruri.model.Notification
import com.wiserock.heruri.model.push.Push
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.utils.interfaces.TimeStamp
import com.wiserock.heruri.view.adapter.CourseAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class LectureViewModel : ViewModel(), TimeStamp {
    var homeworkList: MutableLiveData<ArrayList<Homework>> = MutableLiveData()
    var courseList: MutableLiveData<ArrayList<Course>> = MutableLiveData()
    var notificationList: MutableLiveData<ArrayList<Notification>> = MutableLiveData()
    var pushList: MutableLiveData<ArrayList<Push>> = MutableLiveData()

    fun setOnClickCourseListener(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        ContextCompat.startActivity(context, intent, null)
    }

    fun setPushTime(position: Int): String {
        val time = Date(pushList.value!![position].time)
        return timeStampToString(time)
    }

    fun deletePush(position: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            MainActivity.appDatabase.pushDAO().delete(MyApp.pushArrayList[position])
            MyApp.pushArrayList.removeAt(position)
            launch(Dispatchers.Main) {
                CourseAdapter.viewModel.pushList.value = MyApp.pushArrayList
            }
        }
    }
}
