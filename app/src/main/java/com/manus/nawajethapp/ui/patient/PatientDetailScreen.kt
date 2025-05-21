package com.manus.nawajethapp.ui.patient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manus.nawajethapp.data.model.Patient
import com.manus.nawajethapp.viewmodel.PatientViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDetailScreen(
    patientId: Long,
    patientViewModel: PatientViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val patientState by patientViewModel.getPatientById(patientId).observeAsState()
    var patientName by remember { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(false) }
    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }

    LaunchedEffect(patientState) {
        patientState?.let {
            patientName = it.fullName
        }
    }

    if (showDeleteConfirmationDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmationDialog = false },
            title = { Text("Confirm Deletion") },
            text = { Text("Are you sure you want to delete this patient? This action cannot be undone.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        patientState?.let { patientToDelete ->
                            patientViewModel.delete(patientToDelete)
                            showDeleteConfirmationDialog = false
                            onNavigateBack() // Navigate back after deletion
                        }
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmationDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEditing) "Edit Patient" else "Patient Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (!isEditing && patientState != null) {
                        IconButton(onClick = { isEditing = true }) {
                            Icon(Icons.Filled.Edit, contentDescription = "Edit Patient")
                        }
                        IconButton(onClick = { showDeleteConfirmationDialog = true }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Delete Patient")
                        }
                    }
                }
            )
        }
    ) {
        paddingValues ->
        patientState?.let { patient ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (isEditing) {
                    OutlinedTextField(
                        value = patientName,
                        onValueChange = { patientName = it },
                        label = { Text("Full Name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Button(onClick = { 
                            // Reset name if user cancels editing
                            patientName = patient.fullName
                            isEditing = false 
                        }) {
                            Text("Cancel")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            if (patientName.isNotBlank()) {
                                val updatedPatient = patient.copy(fullName = patientName)
                                patientViewModel.update(updatedPatient)
                                isEditing = false
                            }
                        }) {
                            Text("Save")
                        }
                    }
                } else {
                    Text("Name: ${patient.fullName}", style = MaterialTheme.typography.headlineSmall)
                    val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.getDefault())
                    Text("Registered: ${sdf.format(patient.registrationDate)}", style = MaterialTheme.typography.bodyMedium)
                    // TODO: Add more details: list of treatment sessions, X-rays, photos etc.
                    Text("Treatment Sessions: (To be implemented)", style = MaterialTheme.typography.titleMedium)
                    Text("X-Ray Images: (To be implemented)", style = MaterialTheme.typography.titleMedium)
                    Text("Photographic Images: (To be implemented)", style = MaterialTheme.typography.titleMedium)
                }
            }
        } ?: Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Loading patient details or patient not found...")
        }
    }
}

