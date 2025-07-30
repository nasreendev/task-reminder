package com.test.taskreminder.presentation.add_task.components

import android.app.TimePickerDialog
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onConfirm: (hour: Int,minute: Int) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )
    AlertDialog(
        onDismissRequest = {
            onDismiss.invoke()
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss.invoke()
                }
            ) {
                Text("Cancel")
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm.invoke(
                     timePickerState.hour,
                        timePickerState.minute
                    )
                }
            ) {
                Text("Ok")
            }
        },
        title = {
            Text("Select Time")
        },
        text = {
            TimeInput(
                state = timePickerState,
            )
        }
    )
}