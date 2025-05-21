package com.manus.nawajethapp.ui.session

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manus.nawajethapp.data.model.TreatmentSession
import com.manus.nawajethapp.viewmodel.TreatmentViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreatmentSessionListScreen(
    patientId: Long,
    patientName: String, // To display in the AppBar
    treatmentViewModel: TreatmentViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onAddSessionClicked: (Long) -> Unit,
    onSessionClicked: (TreatmentSession) -> Unit
) {
    val sessions by treatmentViewModel.getSessionsForPatient(patientId).observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sessions for $patientName") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back to Patient")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddSessionClicked(patientId) }) {
                Icon(Icons.Filled.Add, "Add New Session")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            if (sessions.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("No treatment sessions found for this patient.")
                    Text("Click the '+' button to add a new session.")
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(sessions) { session ->
                        TreatmentSessionItem(session = session, onClick = { onSessionClicked(session) })
                    }
                }
            }
        }
    }
}

@Composable
fun TreatmentSessionItem(session: TreatmentSession, onClick: () -> Unit) {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = session.sessionName,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Date: ${sdf.format(session.sessionDate)}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Status: ${if (session.isClosed) "Closed" else "Open"}",
                style = MaterialTheme.typography.bodyMedium
            )
            // TODO: Display summary of tasks or total amounts if needed
        }
    }
}

// TODO: Add screens for Add/Edit TreatmentSession and Add/Edit TreatmentTask
// For example, AddTreatmentSessionScreen, TreatmentTaskDetailScreen etc.


