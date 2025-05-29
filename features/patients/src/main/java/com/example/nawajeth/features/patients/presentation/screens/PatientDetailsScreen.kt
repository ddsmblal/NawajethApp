package com.example.nawajeth.features.patients.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nawajeth.core.presentation.components.LoadingState
import com.example.nawajeth.features.patients.presentation.viewmodel.PatientDetailsViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDetailsScreen(
    viewModel: PatientDetailsViewModel,
    onNavigateBack: () -> Unit,
    onEditPatient: (Long) -> Unit,
    onAddDentalWork: (Long) -> Unit,
    onDentalWorkClick: (Long) -> Unit,
    onAddPayment: (Long) -> Unit,
    onViewDentalChart: (Long) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "تفاصيل المريض") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "رجوع"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { 
                        uiState.patient?.id?.let { onEditPatient(it) }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "تعديل"
                        )
                    }
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "حذف"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SmallFloatingActionButton(
                    onClick = { uiState.patient?.id?.let { onAddPayment(it) } },
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {
                    Text("+دفعة")
                }
                
                SmallFloatingActionButton(
                    onClick = { uiState.patient?.id?.let { onAddDentalWork(it) } },
                    containerColor = MaterialTheme.colorScheme.tertiary
                ) {
                    Text("+عمل")
                }
                
                FloatingActionButton(
                    onClick = { uiState.patient?.id?.let { onViewDentalChart(it) } },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Text("مخطط الأسنان")
                }
            }
        }
    ) { paddingValues ->
        if (uiState.isLoading) {
            LoadingState(message = "جاري تحميل بيانات المريض...")
        } else if (uiState.patient == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "لم يتم العثور على المريض",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            val patient = uiState.patient!!
            
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // معلومات المريض
                item {
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
                                text = "المعلومات الشخصية",
                                style = MaterialTheme.typography.titleMedium
                            )
                            
                            Divider()
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "الاسم:")
                                Text(text = patient.name)
                            }
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "العمر:")
                                Text(text = "${patient.age} سنة")
                            }
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "الجنس:")
                                Text(text = patient.gender)
                            }
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "رقم الهاتف:")
                                Text(text = patient.phone)
                            }
                            
                            if (patient.notes.isNotEmpty()) {
                                Text(text = "ملاحظات:")
                                Text(text = patient.notes)
                            }
                        }
                    }
                }
                
                // ملخص مالي
                item {
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
                                text = "الملخص المالي",
                                style = MaterialTheme.typography.titleMedium
                            )
                            
                            Divider()
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "إجمالي التكاليف:")
                                Text(text = "${uiState.totalCost} ر.س")
                            }
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "إجمالي المدفوعات:")
                                Text(text = "${uiState.totalPaid} ر.س")
                            }
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "المبلغ المستحق:")
                                Text(
                                    text = "${patient.totalDue} ر.س",
                                    color = if (patient.totalDue > 0) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
                
                // الأعمال السنية
                item {
                    Text(
                        text = "الأعمال السنية",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                
                if (uiState.dentalWorks.isEmpty()) {
                    item {
                        Text(
                            text = "لا توجد أعمال سنية مسجلة لهذا المريض",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    items(uiState.dentalWorks) { dentalWork ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { onDentalWorkClick(dentalWork.id) }
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = dentalWork.workTypeName,
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    
                                    val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                                    Text(
                                        text = dateFormat.format(Date(dentalWork.startDate)),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                
                                dentalWork.toothNumber?.let {
                                    Text(text = "السن: $it")
                                }
                                
                                Text(text = dentalWork.description)
                                
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = "التكلفة: ${dentalWork.cost} ر.س")
                                    Text(
                                        text = "المتبقي: ${dentalWork.remainingAmount} ر.س",
                                        color = if (dentalWork.remainingAmount > 0) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                
                                Text(
                                    text = "الحالة: ${dentalWork.status}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
                
                // المدفوعات
                item {
                    Text(
                        text = "المدفوعات",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
                
                if (uiState.payments.isEmpty()) {
                    item {
                        Text(
                            text = "لا توجد مدفوعات مسجلة لهذا المريض",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    items(uiState.payments) { payment ->
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "دفعة: ${payment.amount} ر.س",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    
                                    val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                                    Text(
                                        text = dateFormat.format(Date(payment.paymentDate)),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                
                                payment.dentalWorkName?.let {
                                    Text(text = "للعمل: $it")
                                }
                                
                                Text(text = "طريقة الدفع: ${payment.paymentMethod}")
                                
                                if (payment.notes.isNotEmpty()) {
                                    Text(text = "ملاحظات: ${payment.notes}")
                                }
                            }
                        }
                    }
                }
                
                // مساحة إضافية في الأسفل
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
    
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("حذف المريض") },
            text = { Text("هل أنت متأكد من حذف هذا المريض وجميع بياناته؟ لا يمكن التراجع عن هذا الإجراء.") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deletePatient(onSuccess = onNavigateBack)
                        showDeleteDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("حذف")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showDeleteDialog = false }) {
                    Text("إلغاء")
                }
            }
        )
    }
}
