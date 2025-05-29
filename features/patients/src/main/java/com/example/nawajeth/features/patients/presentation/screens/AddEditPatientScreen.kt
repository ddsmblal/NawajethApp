package com.example.nawajeth.features.patients.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.nawajeth.features.patients.presentation.viewmodel.AddEditPatientViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditPatientScreen(
    viewModel: AddEditPatientViewModel,
    onNavigateBack: () -> Unit,
    onShowLimitReachedDialog: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val isEditMode = uiState.patientId != null
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = if (isEditMode) "تعديل بيانات المريض" else "إضافة مريض جديد") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "رجوع"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.savePatient(
                        onSuccess = onNavigateBack,
                        onLimitReached = onShowLimitReachedDialog
                    )
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "حفظ"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = uiState.name,
                onValueChange = { viewModel.updateName(it) },
                label = { Text("اسم المريض") },
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.nameError != null,
                supportingText = { 
                    uiState.nameError?.let { Text(it) }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = uiState.age,
                    onValueChange = { viewModel.updateAge(it) },
                    label = { Text("العمر") },
                    modifier = Modifier.weight(1f),
                    isError = uiState.ageError != null,
                    supportingText = { 
                        uiState.ageError?.let { Text(it) }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
                
                OutlinedTextField(
                    value = uiState.gender,
                    onValueChange = { viewModel.updateGender(it) },
                    label = { Text("الجنس") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
            }
            
            OutlinedTextField(
                value = uiState.phone,
                onValueChange = { viewModel.updatePhone(it) },
                label = { Text("رقم الهاتف") },
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.phoneError != null,
                supportingText = { 
                    uiState.phoneError?.let { Text(it) }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                )
            )
            
            OutlinedTextField(
                value = uiState.notes,
                onValueChange = { viewModel.updateNotes(it) },
                label = { Text("ملاحظات") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )
            
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally)
                )
            }
            
            if (uiState.errorMessage != null) {
                Text(
                    text = uiState.errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(80.dp)) // للتأكد من أن FAB لا يغطي المحتوى
        }
    }
}
