package com.wiserock.heruri.utils.abstracts

import com.wiserock.heruri.utils.interfaces.LoadCourse
import com.wiserock.heruri.utils.interfaces.LoadHomework
import com.wiserock.heruri.utils.interfaces.LoadNotification

abstract class LoadContent : LoadCourse, LoadHomework, LoadNotification {
    abstract fun mixContent()

}