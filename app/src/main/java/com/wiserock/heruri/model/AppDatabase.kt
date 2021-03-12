package com.wiserock.template.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wiserock.heruri.model.Homework
import com.wiserock.heruri.model.course.Course
import com.wiserock.heruri.model.course.CourseDAO
import com.wiserock.heruri.model.day.Day
import com.wiserock.heruri.model.day.DayDAO
import com.wiserock.heruri.model.push.Push
import com.wiserock.heruri.model.push.PushDAO
import com.wiserock.template.model.user.User
import com.wiserock.template.model.user.UserDAO

@Database(
    entities = [
        User::class,
        Push::class,
        Day::class,
        Homework::class,
        Course::class
    ], version = 4, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun pushDAO(): PushDAO
    abstract fun dayDAO(): DayDAO
    abstract fun courseDAO(): CourseDAO

    companion object {
        const val DB_NAME = "application-db"
        val instance: AppDatabase? = null


        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    // write callback function
                })
                .build()
        }
    }

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " + "PRIMARY KEY(`id`))")
        }
    }
    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Book ADD COLUMN pub_year INTEGER")
        }
    }
}
