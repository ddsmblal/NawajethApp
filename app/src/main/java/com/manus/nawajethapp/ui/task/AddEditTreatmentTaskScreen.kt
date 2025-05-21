package com.manus.nawajethapp.ui.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manus.nawajethapp.data.model.TreatmentTask
import com.manus.nawajethapp.viewmodel.TreatmentViewModel
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTreatmentTaskScreen(
    sessionId: Long,
    taskId: Long? = null, // Null for new task, non-null for editing
    treatmentViewModel: TreatmentViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    var taskName by remember { mutableStateOf("") }
    var amountDueString by remember { mutableStateOf("") }
    var amountPaidString by remember { mutableStateOf("0") }
    var description by remember { mutableStateOf("") }
    var isCompleted by remember { mutableStateOf(false) }

    val existingTask by if (taskId != null) {
        // Assuming you'll add a getTaskById function in ViewModel/Repository
        // For now, we'll fetch all and filter, or pass the task object directly if possible
        // This is a placeholder for fetching a single task if needed for editing.
        // A better approach would be: treatmentViewModel.getTaskById(taskId).observeAsState()
        treatmentViewModel.getTasksForSession(sessionId).observeAsState(initial = emptyList())
            .value.find { it.id == taskId }?.let { mutableStateOf(it) } ?: mutableStateOf(null)
    } else {
        remember { mutableStateOf(null) }
    }

    LaunchedEffect(existingTask) {
        existingTask?.let {
            taskName = it.taskName
            amountDueString = it.amountDue.toPlainString()
            amountPaidString = it.amountPaid.toPlainString()
            description = it.description ?: ""
            isCompleted = it.isCompleted
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (taskId == null) "Add New Task" else "Edit Task") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = taskName,
                onValueChange = { taskName = it },
                label = { Text("Task Name (e.g., Filling, Extraction)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = amountDueString,
                onValueChange = { amountDueString = it },
                label = { Text("Amount Due") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = amountPaidString,
                onValueChange = { amountPaidString = it },
                label = { Text("Amount Paid") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = isCompleted,
                    onCheckedChange = { isCompleted = it }
                )
                Text("Mark as Completed")
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    val amountDue = amountDueString.toBigDecimalOrNull() ?: BigDecimal.ZERO
                    val amountPaid = amountPaidString.toBigDecimalOrNull() ?: BigDecimal.ZERO

                    if (taskName.isNotBlank()) {
                        val taskToSave = existingTask?.copy(
                            taskName = taskName,
                            amountDue = amountDue,
                            amountPaid = amountPaid,
                            description = description.ifBlank { null },
                            isCompleted = isCompleted
                        ) ?: TreatmentTask(
                            sessionId = sessionId,
                            taskName = taskName,
                            amountDue = amountDue,
                            amountPaid = amountPaid,
                            description = description.ifBlank { null },
                            isCompleted = isCompleted
                        )

                        if (taskId == null) {
                            treatmentViewModel.insertTask(taskToSave)
                        } else {
                            treatmentViewModel.updateTask(taskToSave)
                        }
                        onNavigateBack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (taskId == null) "Add Task" else "Save Changes")
            }
        }
    }
}

