package com.manus.nawajethapp.ui.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock // Icon for closed session
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manus.nawajethapp.data.model.TreatmentSession
import com.manus.nawajethapp.data.model.TreatmentTask
import com.manus.nawajethapp.viewmodel.TreatmentViewModel
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreatmentTaskListScreen(
    sessionId: Long,
    treatmentViewModel: TreatmentViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onAddTaskClicked: (Long) -> Unit,
    onTaskClicked: (TreatmentTask) -> Unit // For editing or viewing details
) {
    // Observe the specific session to get its details, including its name and closed status
    val sessionState by treatmentViewModel.getSessionById(sessionId).observeAsState()
    // Observe tasks for the current session
    val tasks by treatmentViewModel.loadTasksForSession(sessionId).observeAsState(initial = emptyList())

    // Observe calculated totals from ViewModel
    val totalDue by treatmentViewModel.totalDueForCurrentSession.observeAsState(BigDecimal.ZERO)
    val totalPaid by treatmentViewModel.totalPaidForCurrentSession.observeAsState(BigDecimal.ZERO)
    val remaining by treatmentViewModel.remainingForCurrentSession.observeAsState(BigDecimal.ZERO)

    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("ar", "AE")) // For AED
    var showCloseSessionDialog by remember { mutableStateOf(false) }

    // Update ViewModel when session ID changes or screen is first composed
    LaunchedEffect(sessionId) {
        treatmentViewModel.loadTasksForSession(sessionId) // Ensure tasks are loaded for current session
    }

    if (showCloseSessionDialog) {
        AlertDialog(
            onDismissRequest = { showCloseSessionDialog = false },
            title = { Text("Confirm Close Session") },
            text = { Text("Are you sure you want to close this session? This will mark it as financially settled and prevent further modifications to its tasks.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        sessionState?.let { treatmentViewModel.closeSession(it) }
                        showCloseSessionDialog = false
                    }
                ) {
                    Text("Close Session")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCloseSessionDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(sessionState?.sessionName ?: "Tasks") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back to Session List")
                    }
                },
                actions = {
                    if (sessionState?.isClosed == false) { // Only show if session is open
                        Button(
                            onClick = { showCloseSessionDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("Close Session")
                        }
                    } else if (sessionState?.isClosed == true) {
                        Icon(Icons.Filled.Lock, contentDescription = "Session Closed", tint = MaterialTheme.colorScheme.primary)
                    }
                }
            )
        },
        floatingActionButton = {
            if (sessionState?.isClosed == false) { // Only allow adding tasks if session is open
                FloatingActionButton(onClick = { onAddTaskClicked(sessionId) }) {
                    Icon(Icons.Filled.Add, "Add New Task")
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            if (tasks.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("No tasks found for this session.")
                    if (sessionState?.isClosed == false) {
                        Text("Click the '+' button to add a new task.")
                    }
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(tasks) { task ->
                        TreatmentTaskItem(
                            task = task, 
                            currencyFormat = currencyFormat, 
                            onClick = { 
                                if (sessionState?.isClosed == false) { // Allow editing only if session is open
                                    onTaskClicked(task) 
                                }
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            SessionTotalsView(totalDue, totalPaid, remaining, currencyFormat)
        }
    }
}

@Composable
fun SessionTotalsView(totalDue: BigDecimal, totalPaid: BigDecimal, remaining: BigDecimal, currencyFormat: NumberFormat) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Text("Session Summary", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total Due:", style = MaterialTheme.typography.bodyLarge)
                Text(currencyFormat.format(totalDue), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total Paid:", style = MaterialTheme.typography.bodyLarge)
                Text(currencyFormat.format(totalPaid), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Remaining:", style = MaterialTheme.typography.bodyLarge)
                Text(currencyFormat.format(remaining), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = if (remaining > BigDecimal.ZERO) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Composable
fun TreatmentTaskItem(task: TreatmentTask, currencyFormat: NumberFormat, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.taskName,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
                task.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Due: ${currencyFormat.format(task.amountDue)}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Paid: ${currencyFormat.format(task.amountPaid)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            if (task.isCompleted) {
                Icon(Icons.Filled.Done, contentDescription = "Completed", tint = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

