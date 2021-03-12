package com.wiserock.heruri.model.course

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CourseDAO {

    @Query("select * from course")
    fun getAll(): List<Course>

    @Insert
    fun insert(course: Course)

    @Delete
    fun delete(course: Course)

    @Query("delete from course")
    fun deleteAll()
}