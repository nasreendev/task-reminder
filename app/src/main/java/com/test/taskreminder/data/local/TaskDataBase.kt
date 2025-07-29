package com.test.taskreminder.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database([Task::class], version = 1)
abstract class TaskDataBase : RoomDatabase() {
    abstract fun dao(): TaskDao

    companion object {
        fun getInstance(context: Context): TaskDataBase {
            return Room.databaseBuilder(
                context,
                TaskDataBase::class.java,
                "task_db"
            ).build()
        }
    }
}