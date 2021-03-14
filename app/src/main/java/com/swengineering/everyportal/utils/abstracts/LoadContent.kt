package com.swengineering.everyportal.utils.abstracts

import com.swengineering.everyportal.utils.interfaces.LoadCourse
import com.swengineering.everyportal.utils.interfaces.LoadHomework
import com.swengineering.everyportal.utils.interfaces.LoadNotification

abstract class LoadContent : LoadCourse, LoadHomework, LoadNotification {
    abstract fun mixContent()

}