package com.test.taskreminder.domain.repository

import android.app.PendingIntent
import com.test.taskreminder.data.local.Task

interface AlarmRepository {
    fun createdPendingIntent(task: Task): PendingIntent
    fun schedule(task: Task)
    fun cancel(task: Task)
}