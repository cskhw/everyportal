package com.wiserock.heruri.model.day

import androidx.room.*

@Dao
interface DayDAO {

    @Transaction
    @Query("select * from day")
    fun getAllPlan(): List<DayWithPlan>

    @Insert
    fun insert(day: Day)

    @Delete
    fun course(day: Day)

    @Query("delete from day")
    fun deleteAll()

}

