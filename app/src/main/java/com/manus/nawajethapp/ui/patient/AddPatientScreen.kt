package com.manus.nawajethapp.ui.patient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manus.nawajethapp.data.model.Patient
import com.manus.nawajethapp.ui.license.TrialLimitReachedDialog
import com.manus.nawajethapp.viewmodel.LicenseViewModel
import com.manus.nawajethapp.viewmodel.PatientViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPatientScreen(
    patientViewModel: PatientViewModel = viewModel(),
    licenseViewModel: LicenseViewModel = viewModel(),
    onPatientAdded: () -> Unit, // Callback to navigate back or refresh list
    onNavigateBack: () -> Unit,
    onNavigateToLicenseActivation: () -> Unit // Callback to navigate to license activation screen
) {
    var fullName by remember { mutableStateOf("") }
    val context = LocalContext.current
    
    // حالة إمكانية إضافة مريض جديد
    val canAddPatient by licenseViewModel.canAddPatient.observeAsState(false)
    
    // حالة عرض مربع حوار تجاوز الحد
    var showLimitDialog by remember { mutableStateOf(false) }
    
    // التحقق من إمكانية إضافة مريض جديد عند فتح الشاشة
    LaunchedEffect(Unit) {
        licenseViewModel.checkCanAddPatient()
    }
    
    // عرض مربع حوار تجاوز الحد إذا لم يكن بالإمكان إضافة مريض جديد
    if (showLimitDialog) {
        TrialLimitReachedDialog(
            onDismiss = { 
                showLimitDialog = false
                onNavigateBack()
            },
            onActivateClick = {
                showLimitDialog = false
                onNavigateToLicenseActivation()
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("إضافة مريض جديد") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, "العودة")
                    }
                }
            )
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("أدخل بيانات المريض", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("الاسم الكامل") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (fullName.isNotBlank()) {
                        if (canAddPatient) {
                            val newPatient = Patient(fullName = fullName, registrationDate = Date())
                            patientViewModel.insert(newPatient)
                            
                            // تحديث عدد المرضى وإمكانية إضافة مرضى جدد بعد الإضافة
                            licenseViewModel.updatePatientsCount()
                            licenseViewModel.checkCanAddPatient()
                            
                            onPatientAdded() // الانتقال للخلف أو تحديث القائمة
                        } else {
                            // عرض مربع حوار تجاوز الحد
                            showLimitDialog = true
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = fullName.isNotBlank() && canAddPatient
            ) {
                Text("حفظ المريض")
            }
            
            // عرض معلومات حول حالة النسخة التجريبية
            Spacer(modifier = Modifier.height(16.dp))
            val licenseStatus by licenseViewModel.licenseStatus.observeAsState()
            val patientsCount by licenseViewModel.patientsCount.observeAsState(0)
            
            if (licenseStatus?.isFullVersion != true) {
                Text(
                    text = "النسخة التجريبية: ${patientsCount}/${licenseStatus?.trialPatientsLimit ?: 10} مريض",
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Button(
                    onClick = onNavigateToLicenseActivation,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("تفعيل النسخة الكاملة")
                }
            }
        }
    }
}
