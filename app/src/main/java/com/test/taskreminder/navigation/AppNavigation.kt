package com.test.taskreminder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.taskreminder.presentation.add_task.AddTaskScreen
import com.test.taskreminder.presentation.task_list.TaskListScreen
import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    data object TaskListScreen : Routes()

    @Serializable
    data object AddTaskScreen : Routes()
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.TaskListScreen
    ) {

        composable<Routes.TaskListScreen> {
            TaskListScreen(
                navToAddTask = {
                    navController.navigate(Routes.AddTaskScreen)
                }
            )
        }
        composable<Routes.AddTaskScreen> {
            AddTaskScreen(
                popBackStack = {
                    navController.popBackStack()
                }
            )
        }
    }
}