package com.wiserock.heruri.model.day

import androidx.room.*

@Entity(
    tableName = "day",
    indices = [Index(value = ["day_time"], unique = true)]
)
data class Day(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "day_id")
    var id: Long = 0,
    @ColumnInfo(name = "day_time")
    var time: Long? = null,

    @ColumnInfo(name = "day_date")
    var date: String
)