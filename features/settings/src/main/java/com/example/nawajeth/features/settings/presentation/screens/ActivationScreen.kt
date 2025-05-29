package com.example.nawajeth.features.settings.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nawajeth.features.settings.presentation.viewmodel.ActivationViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivationScreen(
    onNavigateBack: () -> Unit,
    viewModel: ActivationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val clipboardManager = LocalClipboardManager.current
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("تفعيل التطبيق") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = androidx.compose.material.icons.filled.ArrowBack,
                            contentDescription = "رجوع"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                ActivationContent(
                    uiState = uiState,
                    onActivationCodeChanged = viewModel::updateActivationCode,
                    onActivateClick = viewModel::activateApp,
                    onCopyDeviceId = {
                        clipboardManager.setText(AnnotatedString(uiState.deviceId))
                    }
                )
            }
        }
    }
}

@Composable
fun ActivationContent(
    uiState: com.example.nawajeth.features.settings.presentation.viewmodel.ActivationUiState,
    onActivationCodeChanged: (String) -> Unit,
    onActivateClick: () -> Unit,
    onCopyDeviceId: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // حالة التفعيل الحالية
        ActivationStatusCard(
            isActivated = uiState.isActivated,
            activationDate = uiState.activationDate
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        if (!uiState.isActivated) {
            // معلومات التفعيل
            Text(
                text = "لتفعيل النسخة الكاملة من التطبيق، يرجى اتباع الخطوات التالية:",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // رقم الجهاز
            DeviceIdSection(
                deviceId = uiState.deviceId,
                onCopyDeviceId = onCopyDeviceId
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // إدخال رمز التفعيل
            ActivationCodeSection(
                activationCode = uiState.activationCode,
                onActivationCodeChanged = onActivationCodeChanged,
                onActivateClick = onActivateClick,
                error = uiState.activationError
            )
        }
        
        // رسالة النجاح
        uiState.successMessage?.let { message ->
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ActivationStatusCard(
    isActivated: Boolean,
    activationDate: Long?
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = if (isActivated) Icons.Default.LockOpen else Icons.Default.Lock,
                contentDescription = if (isActivated) "مفعل" else "غير مفعل",
                modifier = Modifier.size(48.dp),
                tint = if (isActivated) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = if (isActivated) "النسخة الكاملة (مفعلة)" else "النسخة التجريبية (غير مفعلة)",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = if (isActivated) {
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    "تم التفعيل بتاريخ: ${dateFormat.format(Date(activationDate ?: 0))}"
                } else {
                    "الإصدار التجريبي محدود بـ 10 مرضى كحد أقصى"
                },
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun DeviceIdSection(
    deviceId: String,
    onCopyDeviceId: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "1. انسخ رقم الجهاز التالي وأرسله للمطور:",
            style = MaterialTheme.typography.bodyMedium
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = deviceId,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            
            IconButton(onClick = onCopyDeviceId) {
                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = "نسخ رقم الجهاز"
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "2. انتظر حتى يرسل لك المطور رمز التفعيل",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ActivationCodeSection(
    activationCode: String,
    onActivationCodeChanged: (String) -> Unit,
    onActivateClick: () -> Unit,
    error: String?
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "3. أدخل رمز التفعيل الذي استلمته:",
            style = MaterialTheme.typography.bodyMedium
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = activationCode,
            onValueChange = onActivationCodeChanged,
            label = { Text("رمز التفعيل") },
            modifier = Modifier.fillMaxWidth(),
            isError = error != null,
            supportingText = error?.let { { Text(it) } }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = onActivateClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = activationCode.isNotBlank()
        ) {
            Text("تفعيل التطبيق")
        }
    }
}
