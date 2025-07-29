package com.test.taskreminder.presentation.add_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.taskreminder.data.local.Task
import com.test.taskreminder.domain.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AddTaskState(
    val task: String = "",
    val description: String = "",
    val time: Long = System.currentTimeMillis(),
)

class AddTaskViewModel(
    private val repository: TaskRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(AddTaskState())
    val state = _state.asStateFlow()


    fun setTask(task: String) {
        _state.update {
            it.copy(
                task = task
            )
        }
    }

    fun setDescription(desc: String) {
        _state.update {
            it.copy(
                description = desc
            )
        }
    }

    fun setTime(time: Long) {
        _state.update {
            it.copy(
                time = time
            )
        }
    }

    fun addTask() {
        viewModelScope.launch {
            val task = Task(
                title = _state.value.task,
                description = _state.value.description,
                time = _state.value.time
            )
            repository.upsertTask(task)
        }
    }
}