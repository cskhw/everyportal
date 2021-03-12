package com.wiserock.template.model.user

import androidx.room.*

@Dao
interface UserDAO {

    @Query("Select * From user")
    fun getAll(): List<User>

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}




