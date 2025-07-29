package com.test.taskreminder.presentation.add_task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.test.taskreminder.R
import com.test.taskreminder.presentation.add_task.components.TaskTextField
import com.test.taskreminder.ui.theme.DARK_GREEN
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    viewModel: AddTaskViewModel = koinViewModel(),
    popBackStack: () -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Task Reminder",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DARK_GREEN
                ),
                actions = {
                    Icon(
                        painter = painterResource(R.drawable.ic_time),
                        "",
                        tint = Color.White,
                        modifier = Modifier.padding(
                            end = 8.dp
                        )
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.addTask()
                    popBackStack.invoke()
                },
                containerColor = DARK_GREEN
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            TaskTextField(
                value = state.task,
                onValueChange = {
                    viewModel.setTask(it)
                },
                label = "Task"
            )

            TaskTextField(
                value = state.description,
                onValueChange = {
                    viewModel.setDescription(it)
                },
                singleLine = false,
                modifier = Modifier
                    .weight(1f),
                label = "Description"
            )
        }
    }
}