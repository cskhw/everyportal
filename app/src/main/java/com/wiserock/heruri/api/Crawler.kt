package com.wiserock.heruri.api

import android.provider.Browser
import com.wiserock.heruri.model.course.Course
import java.util.*

class Crawler {
    lateinit var lastUpdate: Date
    lateinit var browser: Browser
    lateinit var courses: ArrayList<Course>
}