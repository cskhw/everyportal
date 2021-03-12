package com.wiserock.heruri.model

import android.os.Parcelable
import androidx.room.*
import com.wiserock.heruri.model.day.Day
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "homework",
    foreignKeys = [ForeignKey(
        entity = Day::class,
        parentColumns = arrayOf("day_time"),
        childColumns = arrayOf("homework_time")
    )]
    , indices = [Index(value = ["homework_time"], unique = true)]
)
data class Homework(
    @PrimaryKey
    @ColumnInfo(name = "homework_id")
    var id: Long,
    @ColumnInfo(name = "homework_time")
    var time: Long? = null,
    @ColumnInfo(name = "homework_course")
    var course: String,
    @ColumnInfo(name = "homework_name")
    var name: String,
    @ColumnInfo(name = "homework_done")
    var done: Boolean,
    @ColumnInfo(name = "homework_deadline")
    var deadline: String,
    @ColumnInfo(name = "homework_href")
    var href: String
)