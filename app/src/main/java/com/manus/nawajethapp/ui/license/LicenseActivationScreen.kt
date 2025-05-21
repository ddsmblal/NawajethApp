package com.manus.nawajethapp.ui.license

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manus.nawajethapp.viewmodel.LicenseViewModel

/**
 * شاشة تفعيل النسخة الكاملة
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LicenseActivationScreen(
    licenseViewModel: LicenseViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val licenseStatus by licenseViewModel.licenseStatus.observeAsState()
    val activationResult by licenseViewModel.activationResult.observeAsState()
    val patientsCount by licenseViewModel.patientsCount.observeAsState(0)
    val toastMessage by licenseViewModel.toastMessage.observeAsState("")
    
    var activationKey by remember { mutableStateOf("") }
    var showSuccessDialog by remember { mutableStateOf(false) }
    
    // تحديث عدد المرضى عند فتح الشاشة
    LaunchedEffect(Unit) {
        licenseViewModel.updatePatientsCount()
    }
    
    // عرض رسالة نجاح التفعيل
    LaunchedEffect(activationResult) {
        if (activationResult == true) {
            showSuccessDialog = true
        }
    }
    
    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = { Text("تم التفعيل بنجاح") },
            text = { Text("تم تفعيل النسخة الكاملة من تطبيق النواجذ. يمكنك الآن إضافة عدد غير محدود من المرضى.") },
            confirmButton = {
                TextButton(onClick = { 
                    showSuccessDialog = false
                    onNavigateBack()
                }) {
                    Text("موافق")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("تفعيل النسخة الكاملة") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, "العودة")
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // حالة النسخة الحالية
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (licenseStatus?.isFullVersion == true) 
                        MaterialTheme.colorScheme.primaryContainer 
                    else 
                        MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = if (licenseStatus?.isFullVersion == true) 
                            Icons.Filled.LockOpen 
                        else 
                            Icons.Filled.Lock,
                        contentDescription = "حالة النسخة",
                        tint = if (licenseStatus?.isFullVersion == true) 
                            MaterialTheme.colorScheme.primary 
                        else 
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    Text(
                        text = if (licenseStatus?.isFullVersion == true) 
                            "النسخة الكاملة مفعلة" 
                        else 
                            "النسخة التجريبية",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    if (licenseStatus?.isFullVersion != true) {
                        Text(
                            text = "عدد المرضى الحالي: $patientsCount / ${licenseStatus?.trialPatientsLimit ?: 10}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        
                        Text(
                            text = "المرضى المتبقين: ${licenseViewModel.getRemainingPatientsCount()}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (licenseViewModel.getRemainingPatientsCount() <= 2) 
                                MaterialTheme.colorScheme.error 
                            else 
                                MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else {
                        Text(
                            text = "عدد المرضى الحالي: $patientsCount (غير محدود)",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        
                        if (licenseStatus?.activationDate != 0L) {
                            Text(
                                text = "تاريخ التفعيل: ${java.text.SimpleDateFormat("yyyy-MM-dd").format(java.util.Date(licenseStatus?.activationDate ?: 0))}",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // قسم التفعيل (يظهر فقط في النسخة التجريبية)
            if (licenseStatus?.isFullVersion != true) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "تفعيل النسخة الكاملة",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        
                        OutlinedTextField(
                            value = activationKey,
                            onValueChange = { activationKey = it },
                            label = { Text("مفتاح التفعيل") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            singleLine = true
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Button(
                            onClick = { licenseViewModel.activateFullVersion(activationKey) },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = activationKey.isNotBlank()
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "تفعيل",
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text("تفعيل")
                        }
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // ملاحظة: في التطبيق الحقيقي، يجب توفير معلومات حول كيفية الحصول على مفتاح التفعيل
                        Text(
                            text = "ملاحظة: للحصول على مفتاح تفعيل، يرجى التواصل مع فريق الدعم الفني.",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // معلومات إضافية
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "معلومات حول النسخة التجريبية",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    Text(
                        text = "النسخة التجريبية تسمح بإضافة حتى 10 مرضى فقط. لإضافة عدد غير محدود من المرضى، يرجى تفعيل النسخة الكاملة.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

/**
 * مربع حوار تنبيه عند الوصول للحد الأقصى من المرضى
 */
@Composable
fun TrialLimitReachedDialog(
    onDismiss: () -> Unit,
    onActivateClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = { Icon(Icons.Filled.Info, contentDescription = null) },
        title = { Text("تم الوصول للحد الأقصى") },
        text = { 
            Text("لقد وصلت إلى الحد الأقصى لعدد المرضى في النسخة التجريبية (10 مرضى). لإضافة المزيد من المرضى، يرجى تفعيل النسخة الكاملة.")
        },
        confirmButton = {
            Button(onClick = onActivateClick) {
                Text("تفعيل النسخة الكاملة")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("إلغاء")
            }
        }
    )
}
