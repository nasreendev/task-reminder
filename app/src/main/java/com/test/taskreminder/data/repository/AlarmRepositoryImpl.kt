package com.test.taskreminder.data.repository

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.test.taskreminder.data.local.Task
import com.test.taskreminder.domain.repository.AlarmRepository

class AlarmRepositoryImpl(
    private val context: Context,
) : AlarmRepository {

    private val alarmManger = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun createdPendingIntent(task: Task): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("title", task.title)
        }
        return PendingIntent.getBroadcast(
            context,
            task.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    @SuppressLint("ScheduleExactAlarm")
    override fun schedule(task: Task) {
        alarmManger.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            task.time,
            createdPendingIntent(task)
        )
    }

    override fun cancel(task: Task) {
        alarmManger.cancel(createdPendingIntent(task))
    }
}