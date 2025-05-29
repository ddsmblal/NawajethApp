package com.example.nawajeth.features.reports.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nawajeth.features.reports.presentation.components.ReportCard
import com.example.nawajeth.features.reports.presentation.viewmodel.ReportsViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(
    viewModel: ReportsViewModel,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "التقارير") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "رجوع"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // اختيار الفترة الزمنية
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "الفترة الزمنية",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DateRangeSelector(
                            startDate = uiState.startDate,
                            endDate = uiState.endDate,
                            onStartDateSelected = { viewModel.updateStartDate(it) },
                            onEndDateSelected = { viewModel.updateEndDate(it) }
                        )
                    }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        PeriodButton(
                            text = "اليوم",
                            onClick = { viewModel.selectToday() },
                            isSelected = uiState.selectedPeriod == "today"
                        )
                        
                        PeriodButton(
                            text = "هذا الأسبوع",
                            onClick = { viewModel.selectThisWeek() },
                            isSelected = uiState.selectedPeriod == "week"
                        )
                        
                        PeriodButton(
                            text = "هذا الشهر",
                            onClick = { viewModel.selectThisMonth() },
                            isSelected = uiState.selectedPeriod == "month"
                        )
                        
                        PeriodButton(
                            text = "هذه السنة",
                            onClick = { viewModel.selectThisYear() },
                            isSelected = uiState.selectedPeriod == "year"
                        )
                    }
                    
                    Button(
                        onClick = { viewModel.generateReport() },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("عرض التقرير")
                    }
                }
            }
            
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (uiState.isReportGenerated) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // ملخص التقرير
                    item {
                        ReportCard(
                            title = "ملخص التقرير",
                            content = {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                                    Text(
                                        text = "الفترة: من ${dateFormat.format(Date(uiState.startDate))} إلى ${dateFormat.format(Date(uiState.endDate))}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    
                                    Divider()
                                    
                                    SummaryRow(
                                        label = "عدد المرضى الجدد:",
                                        value = "${uiState.newPatientsCount}"
                                    )
                                    
                                    SummaryRow(
                                        label = "عدد الأعمال المنجزة:",
                                        value = "${uiState.completedWorksCount}"
                                    )
                                    
                                    SummaryRow(
                                        label = "إجمالي الإيرادات:",
                                        value = "${uiState.totalRevenue} ر.س"
                                    )
                                    
                                    SummaryRow(
                                        label = "إجمالي المدفوعات:",
                                        value = "${uiState.totalPayments} ر.س"
                                    )
                                    
                                    SummaryRow(
                                        label = "المبالغ المستحقة:",
                                        value = "${uiState.totalDue} ر.س"
                                    )
                                }
                            }
                        )
                    }
                    
                    // المرضى الجدد
                    item {
                        ReportCard(
                            title = "المرضى الجدد",
                            content = {
                                if (uiState.newPatients.isEmpty()) {
                                    Text(
                                        text = "لا يوجد مرضى جدد خلال هذه الفترة",
                                        style = MaterialTheme.typography.bodyMedium,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                } else {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        uiState.newPatients.forEach { patient ->
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Text(text = patient.name)
                                                
                                                val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                                                Text(
                                                    text = dateFormat.format(Date(patient.registrationDate)),
                                                    style = MaterialTheme.typography.bodySmall
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        )
                    }
                    
                    // الأعمال المنجزة
                    item {
                        ReportCard(
                            title = "الأعمال المنجزة",
                            content = {
                                if (uiState.completedWorks.isEmpty()) {
                                    Text(
                                        text = "لا توجد أعمال منجزة خلال هذه الفترة",
                                        style = MaterialTheme.typography.bodyMedium,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                } else {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        uiState.completedWorks.forEach { work ->
                                            Column(
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.SpaceBetween
                                                ) {
                                                    Text(
                                                        text = "${work.workTypeName} - ${work.patientName}",
                                                        style = MaterialTheme.typography.bodyMedium
                                                    )
                                                    
                                                    Text(
                                                        text = "${work.cost} ر.س",
                                                        style = MaterialTheme.typography.bodyMedium
                                                    )
                                                }
                                                
                                                val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                                                Text(
                                                    text = "تاريخ الإنجاز: ${dateFormat.format(Date(work.completionDate))}",
                                                    style = MaterialTheme.typography.bodySmall
                                                )
                                            }
                                            
                                            Divider()
                                        }
                                    }
                                }
                            }
                        )
                    }
                    
                    // المدفوعات
                    item {
                        ReportCard(
                            title = "المدفوعات",
                            content = {
                                if (uiState.payments.isEmpty()) {
                                    Text(
                                        text = "لا توجد مدفوعات خلال هذه الفترة",
                                        style = MaterialTheme.typography.bodyMedium,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                } else {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        uiState.payments.forEach { payment ->
                                            Column(
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.SpaceBetween
                                                ) {
                                                    Text(
                                                        text = payment.patientName,
                                                        style = MaterialTheme.typography.bodyMedium
                                                    )
                                                    
                                                    Text(
                                                        text = "${payment.amount} ر.س",
                                                        style = MaterialTheme.typography.bodyMedium
                                                    )
                                                }
                                                
                                                payment.dentalWorkName?.let {
                                                    Text(
                                                        text = "للعمل: $it",
                                                        style = MaterialTheme.typography.bodySmall
                                                    )
                                                }
                                                
                                                val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                                                Text(
                                                    text = "تاريخ الدفع: ${dateFormat.format(Date(payment.paymentDate))}",
                                                    style = MaterialTheme.typography.bodySmall
                                                )
                                            }
                                            
                                            Divider()
                                        }
                                    }
                                }
                            }
                        )
                    }
                    
                    // زر طباعة التقرير
                    item {
                        Button(
                            onClick = { viewModel.printReport() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("طباعة التقرير")
                        }
                    }
                    
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "اختر الفترة الزمنية واضغط على 'عرض التقرير' لعرض البيانات",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangeSelector(
    startDate: Long,
    endDate: Long,
    onStartDateSelected: (Long) -> Unit,
    onEndDateSelected: (Long) -> Unit
) {
    var showStartDatePicker by remember { mutableStateOf(false) }
    var showEndDatePicker by remember { mutableStateOf(false) }
    val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "من",
                style = MaterialTheme.typography.bodyMedium
            )
            
            OutlinedButton(
                onClick = { showStartDatePicker = true },
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "اختر تاريخ البداية"
                )
                
                Spacer(modifier = Modifier.width(4.dp))
                
                Text(
                    text = dateFormat.format(Date(startDate))
                )
            }
        }
        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "إلى",
                style = MaterialTheme.typography.bodyMedium
            )
            
            OutlinedButton(
                onClick = { showEndDatePicker = true },
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "اختر تاريخ النهاية"
                )
                
                Spacer(modifier = Modifier.width(4.dp))
                
                Text(
                    text = dateFormat.format(Date(endDate))
                )
            }
        }
    }
    
    if (showStartDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = startDate
        )
        
        DatePickerDialog(
            onDismissRequest = { showStartDatePicker = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { onStartDateSelected(it) }
                        showStartDatePicker = false
                    }
                ) {
                    Text("تأكيد")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { showStartDatePicker = false }
                ) {
                    Text("إلغاء")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
    
    if (showEndDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = endDate
        )
        
        DatePickerDialog(
            onDismissRequest = { showEndDatePicker = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { onEndDateSelected(it) }
                        showEndDatePicker = false
                    }
                ) {
                    Text("تأكيد")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { showEndDatePicker = false }
                ) {
                    Text("إلغاء")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Composable
fun PeriodButton(
    text: String,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        )
    ) {
        Text(text)
    }
}

@Composable
fun SummaryRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
        
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
