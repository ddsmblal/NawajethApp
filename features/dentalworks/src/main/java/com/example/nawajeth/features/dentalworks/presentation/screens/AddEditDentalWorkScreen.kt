package com.example.nawajeth.features.dentalworks.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.nawajeth.features.dentalworks.domain.model.WorkType
import com.example.nawajeth.features.dentalworks.presentation.viewmodel.AddEditDentalWorkViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditDentalWorkScreen(
    viewModel: AddEditDentalWorkViewModel,
    patientId: Long,
    dentalWorkId: Long? = null,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val isEditMode = dentalWorkId != null
    
    LaunchedEffect(patientId, dentalWorkId) {
        viewModel.initialize(patientId, dentalWorkId)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = if (isEditMode) "تعديل عمل سني" else "إضافة عمل سني جديد") },
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
                onClick = { viewModel.saveDentalWork(onSuccess = onNavigateBack) },
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
            // معلومات المريض
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = uiState.patientName,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            
            // نوع العمل
            Text(
                text = "نوع العمل",
                style = MaterialTheme.typography.titleSmall
            )
            
            WorkTypeSelector(
                workTypes = uiState.availableWorkTypes,
                selectedWorkTypeId = uiState.workTypeId,
                onWorkTypeSelected = { viewModel.updateWorkType(it) }
            )
            
            // رقم السن
            OutlinedTextField(
                value = uiState.toothNumber,
                onValueChange = { viewModel.updateToothNumber(it) },
                label = { Text("رقم السن (اختياري)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            
            // الوصف
            OutlinedTextField(
                value = uiState.description,
                onValueChange = { viewModel.updateDescription(it) },
                label = { Text("وصف العمل") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                isError = uiState.descriptionError != null,
                supportingText = { 
                    uiState.descriptionError?.let { Text(it) }
                }
            )
            
            // التكلفة
            OutlinedTextField(
                value = uiState.cost,
                onValueChange = { viewModel.updateCost(it) },
                label = { Text("التكلفة (ر.س)") },
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.costError != null,
                supportingText = { 
                    uiState.costError?.let { Text(it) }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                )
            )
            
            // المبلغ المدفوع
            OutlinedTextField(
                value = uiState.paidAmount,
                onValueChange = { viewModel.updatePaidAmount(it) },
                label = { Text("المبلغ المدفوع (ر.س)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                )
            )
            
            // حالة العمل
            Text(
                text = "حالة العمل",
                style = MaterialTheme.typography.titleSmall
            )
            
            StatusSelector(
                selectedStatus = uiState.status,
                onStatusSelected = { viewModel.updateStatus(it) }
            )
            
            // تاريخ البدء
            DateSelector(
                label = "تاريخ البدء",
                date = uiState.startDate,
                onDateSelected = { viewModel.updateStartDate(it) }
            )
            
            // تاريخ الانتهاء (اختياري)
            DateSelector(
                label = "تاريخ الانتهاء (اختياري)",
                date = uiState.endDate,
                onDateSelected = { viewModel.updateEndDate(it) },
                isOptional = true
            )
            
            // ملاحظات
            OutlinedTextField(
                value = uiState.notes,
                onValueChange = { viewModel.updateNotes(it) },
                label = { Text("ملاحظات") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
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

@Composable
fun WorkTypeSelector(
    workTypes: List<WorkType>,
    selectedWorkTypeId: Long?,
    onWorkTypeSelected: (Long) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        workTypes.forEach { workType ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = workType.id == selectedWorkTypeId,
                    onClick = { onWorkTypeSelected(workType.id) }
                )
                
                Text(
                    text = "${workType.name} (${workType.defaultCost} ر.س)",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun StatusSelector(
    selectedStatus: String,
    onStatusSelected: (String) -> Unit
) {
    val statuses = listOf("مخطط له", "قيد التنفيذ", "مكتمل", "ملغي")
    
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        statuses.forEach { status ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = status == selectedStatus,
                    onClick = { onStatusSelected(status) }
                )
                
                Text(
                    text = status,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelector(
    label: String,
    date: Long?,
    onDateSelected: (Long) -> Unit,
    isOptional: Boolean = false
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        
        OutlinedButton(
            onClick = { showDatePicker = true }
        ) {
            Text(
                text = if (date != null) dateFormat.format(Date(date)) else "اختر التاريخ"
            )
        }
        
        if (isOptional && date != null) {
            IconButton(
                onClick = { onDateSelected(0) }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "مسح التاريخ"
                )
            }
        }
    }
    
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = date ?: System.currentTimeMillis()
        )
        
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { onDateSelected(it) }
                        showDatePicker = false
                    }
                ) {
                    Text("تأكيد")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { showDatePicker = false }
                ) {
                    Text("إلغاء")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
