package com.test.taskreminder.presentation.task_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.test.taskreminder.data.local.Task
import com.test.taskreminder.ui.theme.DARK_GREEN
import com.test.taskreminder.ui.theme.LIGHT_SOFT_GREEN
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TaskItem(
    task: Task,
    onDeleteClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
            containerColor = LIGHT_SOFT_GREEN
        ), shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title, style = MaterialTheme.typography.titleMedium.copy(DARK_GREEN)
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyMedium.copy(DARK_GREEN)
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "${convertMillisToTime(task.time)}",
                    style = MaterialTheme.typography.bodyMedium.copy(Color.DarkGray)
                )
            }
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "",
                tint = DARK_GREEN,
                modifier = Modifier.clickable {
                    onDeleteClick.invoke()
                })
        }
    }
}

fun convertMillisToTime(millis: Long): String {
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return formatter.format(Date(millis))
}