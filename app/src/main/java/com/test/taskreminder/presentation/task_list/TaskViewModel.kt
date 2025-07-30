package com.test.taskreminder.presentation.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.taskreminder.data.local.Task
import com.test.taskreminder.domain.repository.AlarmRepository
import com.test.taskreminder.domain.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TaskState(
    val task: List<Task> = emptyList(),
)

class TaskViewModel(
    private val repository: TaskRepository,
    private val alarmRepository: AlarmRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TaskState())
    val state = _state.asStateFlow()

    init {
        getAllTask()
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
            alarmRepository.cancel(task)
        }
    }

    fun getAllTask() {
        viewModelScope.launch {
            repository.getAllTask().collectLatest { tasks ->
                _state.update {
                    it.copy(
                        task = tasks
                    )
                }
            }
        }
    }
}