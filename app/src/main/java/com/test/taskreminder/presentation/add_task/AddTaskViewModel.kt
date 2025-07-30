package com.test.taskreminder.presentation.add_task

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.taskreminder.data.local.Task
import com.test.taskreminder.domain.repository.AlarmRepository
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
    private val alarmRepository: AlarmRepository,
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

    fun setTime(hour: Int, minute: Int) {
        val calender = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (before(Calendar.getInstance())) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }
        _state.update {
            it.copy(
                time = calender.timeInMillis
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
            val id = repository.upsertTask(task)
            if (task.id == 0) {
                val newTask = task.copy(
                    id.toInt()
                )
                alarmRepository.schedule(newTask)
            }
        }
    }
}