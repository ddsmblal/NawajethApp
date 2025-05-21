package com.manus.nawajethapp.ui.session

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manus.nawajethapp.data.model.TreatmentSession
import com.manus.nawajethapp.viewmodel.TreatmentViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTreatmentSessionScreen(
    patientId: Long,
    sessionId: Long? = null, // Null for new session, non-null for editing existing
    treatmentViewModel: TreatmentViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    var sessionName by remember { mutableStateOf("") }
    var sessionDate by remember { mutableStateOf(Date()) }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = sessionDate.time)

    val existingSession by if (sessionId != null) {
        treatmentViewModel.getSessionById(sessionId).observeAsState()
    } else {
        remember { mutableStateOf(null) }
    }

    LaunchedEffect(existingSession) {
        existingSession?.let {
            sessionName = it.sessionName
            sessionDate = it.sessionDate
            datePickerState.selectedDateMillis = it.sessionDate.time
        }
    }
    
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        sessionDate = Date(it)
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (sessionId == null) "Add New Session" else "Edit Session") },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = sessionName,
                onValueChange = { sessionName = it },
                label = { Text("Session Name (e.g., Visit 1, Consultation)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedButton(
                onClick = { showDatePicker = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Session Date: ${sdf.format(sessionDate)}")
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (sessionName.isNotBlank()) {
                        val sessionToSave = existingSession?.copy(
                            sessionName = sessionName,
                            sessionDate = sessionDate
                        ) ?: TreatmentSession(
                            patientId = patientId,
                            sessionName = sessionName,
                            sessionDate = sessionDate
                        )

                        if (sessionId == null) {
                            treatmentViewModel.insertSession(sessionToSave)
                        } else {
                            treatmentViewModel.updateSession(sessionToSave)
                        }
                        onNavigateBack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (sessionId == null) "Create Session" else "Save Changes")
            }
        }
    }
}

