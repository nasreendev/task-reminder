package com.test.taskreminder.presentation.add_task.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.taskreminder.ui.theme.DARK_GREEN

@Composable
fun TaskTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange:(String)-> Unit,
    singleLine: Boolean=true,
    label: String
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        modifier=modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedLabelColor = DARK_GREEN,
            unfocusedLabelColor = DARK_GREEN.copy(0.7f),
            focusedBorderColor = DARK_GREEN,
            unfocusedBorderColor = DARK_GREEN.copy(0.7f)
        ),
        label = {
            Text(label)
        }
    )

}