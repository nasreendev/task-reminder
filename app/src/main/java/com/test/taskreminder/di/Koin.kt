package com.test.taskreminder.di

import com.test.taskreminder.data.local.TaskDao
import com.test.taskreminder.data.local.TaskDataBase
import com.test.taskreminder.data.repository.AlarmRepositoryImpl
import com.test.taskreminder.data.repository.TaskRepositoryImpl
import com.test.taskreminder.domain.repository.AlarmRepository
import com.test.taskreminder.domain.repository.TaskRepository
import com.test.taskreminder.presentation.add_task.AddTaskViewModel
import com.test.taskreminder.presentation.task_list.TaskViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {


    singleOf(::TaskRepositoryImpl) {
        bind<TaskRepository>()
    }

    viewModelOf(::AddTaskViewModel)
    viewModelOf(::TaskViewModel)

    singleOf(::AlarmRepositoryImpl){
        bind<AlarmRepository>()
    }
    single {
        TaskDataBase.getInstance(get())
    }

    single<TaskDao> {
        get<TaskDataBase>().dao()
    }
}