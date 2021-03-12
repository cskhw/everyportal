package com.wiserock.heruri.model.push

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PushDAO {

    @Query("select * from push order By time desc")
    fun getAll(): List<Push>

    @Insert
    fun insert(push: Push)

    @Delete()
    fun delete(push: Push)
}