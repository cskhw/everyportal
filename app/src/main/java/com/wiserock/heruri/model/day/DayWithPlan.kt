package com.wiserock.heruri.model.day

import androidx.room.Embedded
import androidx.room.Relation
import com.wiserock.heruri.model.Homework
import com.wiserock.heruri.model.course.Course

data class DayWithPlan(
    @Embedded
    var day: Day? = null,

    @Relation(
        parentColumn = "day_time",
        entityColumn = "homework_time",
        entity = Homework::class
    )
    var homeworkList: List<Homework> = mutableListOf(),

    @Relation(
        parentColumn = "day_time",
        entityColumn = "course_time",
        entity = Course::class
    )
    var courseList: List<Course> = mutableListOf()
)