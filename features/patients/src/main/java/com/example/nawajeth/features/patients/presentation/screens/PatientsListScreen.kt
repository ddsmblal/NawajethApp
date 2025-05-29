package com.example.nawajeth.features.patients.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.nawajeth.core.presentation.components.EmptyState
import com.example.nawajeth.core.presentation.components.LoadingState
import com.example.nawajeth.core.presentation.components.SearchBar
import com.example.nawajeth.features.patients.domain.model.Patient
import com.example.nawajeth.features.patients.presentation.components.PatientItem
import com.example.nawajeth.features.patients.presentation.viewmodel.PatientsListViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientsListScreen(
    viewModel: PatientsListViewModel,
    onPatientClick: (Long) -> Unit,
    onAddPatientClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "المرضى") },
                actions = {
                    IconButton(onClick = { viewModel.toggleSearchBar() }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "بحث"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddPatientClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "إضافة مريض جديد"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (uiState.isSearchBarVisible) {
                SearchBar(
                    query = searchQuery,
                    onQueryChange = { viewModel.updateSearchQuery(it) },
                    onSearch = { viewModel.searchPatients() },
                    onClose = { viewModel.toggleSearchBar() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
            
            when {
                uiState.isLoading -> {
                    LoadingState(message = "جاري تحميل قائمة المرضى...")
                }
                uiState.patients.isEmpty() -> {
                    EmptyState(
                        message = if (searchQuery.isNotEmpty()) 
                            "لا توجد نتائج مطابقة للبحث" 
                        else 
                            "لا يوجد مرضى حالياً. أضف مريضاً جديداً للبدء."
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.patients) { patient ->
                            PatientItem(
                                patient = patient,
                                onClick = { onPatientClick(patient.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PatientItem(
    patient: Patient,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = patient.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Text(
                    text = "${patient.age} سنة",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = patient.phone,
                    style = MaterialTheme.typography.bodyMedium
                )
                
                Text(
                    text = if (patient.totalDue > 0) "مستحق: ${patient.totalDue} ر.س" else "لا يوجد مستحقات",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (patient.totalDue > 0) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                )
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            
            patient.lastVisitDate?.let { lastVisit ->
                val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                Text(
                    text = "آخر زيارة: ${dateFormat.format(Date(lastVisit))}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
