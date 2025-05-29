package com.example.nawajeth.features.financial.presentation.screens

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
import com.example.nawajeth.features.financial.domain.model.DentalWork
import com.example.nawajeth.features.financial.presentation.viewmodel.AddPaymentViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPaymentScreen(
    viewModel: AddPaymentViewModel,
    patientId: Long,
    dentalWorkId: Long? = null,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(patientId, dentalWorkId) {
        viewModel.initialize(patientId, dentalWorkId)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "إضافة دفعة جديدة") },
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
                onClick = { viewModel.savePayment(onSuccess = onNavigateBack) },
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
                    
                    if (uiState.patientTotalDue > 0) {
                        Spacer(modifier = Modifier.height(4.dp))
                        
                        Text(
                            text = "المبلغ المستحق: ${uiState.patientTotalDue} ر.س",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
            
            // العمل السني (اختياري)
            if (uiState.dentalWorkId == null && uiState.availableDentalWorks.isNotEmpty()) {
                Text(
                    text = "العمل السني (اختياري)",
                    style = MaterialTheme.typography.titleSmall
                )
                
                DentalWorkSelector(
                    dentalWorks = uiState.availableDentalWorks,
                    selectedDentalWorkId = uiState.dentalWorkId,
                    onDentalWorkSelected = { viewModel.updateDentalWork(it) }
                )
            } else if (uiState.dentalWorkId != null) {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "العمل السني",
                            style = MaterialTheme.typography.titleSmall
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        val selectedWork = uiState.availableDentalWorks.find { it.id == uiState.dentalWorkId }
                        if (selectedWork != null) {
                            Text(text = "نوع العمل: ${selectedWork.workTypeName}")
                            
                            selectedWork.toothNumber?.let {
                                Text(text = "السن: $it")
                            }
                            
                            Text(text = "التكلفة: ${selectedWork.cost} ر.س")
                            Text(text = "المتبقي: ${selectedWork.remainingAmount} ر.س")
                            
                            if (uiState.dentalWorkId != dentalWorkId) {
                                TextButton(
                                    onClick = { viewModel.clearDentalWork() },
                                    modifier = Modifier.align(Alignment.End)
                                ) {
                                    Text("إلغاء الربط")
                                }
                            }
                        }
                    }
                }
            }
            
            // المبلغ
            OutlinedTextField(
                value = uiState.amount,
                onValueChange = { viewModel.updateAmount(it) },
                label = { Text("المبلغ (ر.س)") },
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.amountError != null,
                supportingText = { 
                    uiState.amountError?.let { Text(it) }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                )
            )
            
            // طريقة الدفع
            Text(
                text = "طريقة الدفع",
                style = MaterialTheme.typography.titleSmall
            )
            
            PaymentMethodSelector(
                selectedMethod = uiState.paymentMethod,
                onMethodSelected = { viewModel.updatePaymentMethod(it) }
            )
            
            // تاريخ الدفع
            DateSelector(
                label = "تاريخ الدفع",
                date = uiState.paymentDate,
                onDateSelected = { viewModel.updatePaymentDate(it) }
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
fun DentalWorkSelector(
    dentalWorks: List<DentalWork>,
    selectedDentalWorkId: Long?,
    onDentalWorkSelected: (Long) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedDentalWorkId == null,
                onClick = { onDentalWorkSelected(0) }
            )
            
            Text(
                text = "دفعة عامة (غير مرتبطة بعمل محدد)",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        
        dentalWorks.forEach { dentalWork ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = dentalWork.id == selectedDentalWorkId,
                    onClick = { onDentalWorkSelected(dentalWork.id) }
                )
                
                Column(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = dentalWork.workTypeName + 
                            (dentalWork.toothNumber?.let { " (السن: $it)" } ?: "")
                    )
                    
                    Text(
                        text = "المتبقي: ${dentalWork.remainingAmount} ر.س",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (dentalWork.remainingAmount > 0) 
                            MaterialTheme.colorScheme.error 
                        else 
                            MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun PaymentMethodSelector(
    selectedMethod: String,
    onMethodSelected: (String) -> Unit
) {
    val methods = listOf("نقداً", "بطاقة ائتمان", "تحويل بنكي", "أخرى")
    
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        methods.forEach { method ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = method == selectedMethod,
                    onClick = { onMethodSelected(method) }
                )
                
                Text(
                    text = method,
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
    date: Long,
    onDateSelected: (Long) -> Unit
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
                text = dateFormat.format(Date(date))
            )
        }
    }
    
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = date
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
