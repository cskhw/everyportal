package com.wiserock.heruri.model.course

import android.os.Parcelable
import androidx.room.*
import com.wiserock.heruri.model.day.Day
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "course",
    foreignKeys = [ForeignKey(
        entity = Day::class,
        parentColumns = ["day_time"],
        childColumns = ["course_time"]
    )],
    indices = [Index(value = ["course_time"], unique = true)]
)
data class Course(

    @PrimaryKey
    @ColumnInfo(name = "course_id")
    var id: Long,

    @ColumnInfo(name = "course_time")
    var time: Long? = null,
    @ColumnInfo(name = "course_name")
    var name: String,
    @ColumnInfo(name = "course_done")
    var done: Boolean,
    @ColumnInfo(name = "course_href")
    var href: String,
    @ColumnInfo(name = "course_professor")
    var professor: String,
    @ColumnInfo(name = "course_deadline")
    var deadline: String
) : Parcelable