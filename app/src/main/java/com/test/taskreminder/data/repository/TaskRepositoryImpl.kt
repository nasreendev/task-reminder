package com.test.taskreminder.data.repository

import com.test.taskreminder.data.local.Task
import com.test.taskreminder.data.local.TaskDao
import com.test.taskreminder.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val dao: TaskDao,
) : TaskRepository {
    override suspend fun upsertTask(task: Task) {
        dao.upsertTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }

    override fun getAllTask(): Flow<List<Task>> {
        return dao.getAllTask()
    }
}