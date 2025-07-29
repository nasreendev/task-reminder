package com.test.taskreminder.domain.repository

import com.test.taskreminder.data.local.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun upsertTask(task: Task)

    suspend fun deleteTask(task: Task)

    fun getAllTask(): Flow<List<Task>>
}