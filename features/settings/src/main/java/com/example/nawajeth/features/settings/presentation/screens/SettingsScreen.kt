package com.example.nawajeth.features.settings.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nawajeth.features.settings.presentation.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "الإعدادات") },
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // إعدادات المظهر
            SettingsCard(
                title = "المظهر",
                icon = Icons.Default.Palette,
                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "سمة التطبيق",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            ThemeButton(
                                text = "فاتح",
                                isSelected = uiState.theme == "light",
                                onClick = { viewModel.updateTheme("light") }
                            )
                            
                            ThemeButton(
                                text = "داكن",
                                isSelected = uiState.theme == "dark",
                                onClick = { viewModel.updateTheme("dark") }
                            )
                            
                            ThemeButton(
                                text = "تلقائي",
                                isSelected = uiState.theme == "system",
                                onClick = { viewModel.updateTheme("system") }
                            )
                        }
                    }
                }
            )
            
            // إعدادات اللغة
            SettingsCard(
                title = "اللغة",
                icon = Icons.Default.Language,
                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            LanguageButton(
                                text = "العربية",
                                isSelected = uiState.language == "ar",
                                onClick = { viewModel.updateLanguage("ar") }
                            )
                            
                            LanguageButton(
                                text = "English",
                                isSelected = uiState.language == "en",
                                onClick = { viewModel.updateLanguage("en") }
                            )
                        }
                    }
                }
            )
            
            // إعدادات الرموز والأسعار
            SettingsCard(
                title = "الرموز والأسعار",
                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { viewModel.navigateToWorkTypes() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("إدارة أنواع الأعمال والأسعار")
                        }
                        
                        Button(
                            onClick = { viewModel.navigateToDentalSymbols() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("إدارة رموز الأسنان")
                        }
                    }
                }
            )
            
            // النسخ الاحتياطي
            SettingsCard(
                title = "النسخ الاحتياطي",
                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "النسخ الاحتياطي التلقائي",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            
                            Switch(
                                checked = uiState.autoBackupEnabled,
                                onCheckedChange = { viewModel.toggleAutoBackup() }
                            )
                        }
                        
                        if (uiState.autoBackupEnabled) {
                            Text(
                                text = "تكرار النسخ الاحتياطي",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                BackupFrequencyButton(
                                    text = "يومياً",
                                    isSelected = uiState.backupFrequency == 1,
                                    onClick = { viewModel.updateBackupFrequency(1) }
                                )
                                
                                BackupFrequencyButton(
                                    text = "أسبوعياً",
                                    isSelected = uiState.backupFrequency == 7,
                                    onClick = { viewModel.updateBackupFrequency(7) }
                                )
                                
                                BackupFrequencyButton(
                                    text = "شهرياً",
                                    isSelected = uiState.backupFrequency == 30,
                                    onClick = { viewModel.updateBackupFrequency(30) }
                                )
                            }
                            
                            OutlinedTextField(
                                value = uiState.backupLocation,
                                onValueChange = { viewModel.updateBackupLocation(it) },
                                label = { Text("مسار النسخ الاحتياطي") },
                                modifier = Modifier.fillMaxWidth(),
                                trailingIcon = {
                                    IconButton(onClick = { viewModel.selectBackupLocation() }) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = "اختيار المسار"
                                        )
                                    }
                                }
                            )
                        }
                        
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = { viewModel.createBackup() },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("إنشاء نسخة احتياطية")
                            }
                            
                            Spacer(modifier = Modifier.width(8.dp))
                            
                            Button(
                                onClick = { viewModel.restoreBackup() },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("استعادة من نسخة احتياطية")
                            }
                        }
                    }
                }
            )
            
            // معلومات التفعيل
            SettingsCard(
                title = "معلومات التفعيل",
                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (uiState.isActivated) {
                            Text(
                                text = "التطبيق مفعل بالكامل",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            Text(
                                text = "النسخة التجريبية (محدودة بـ 10 مرضى)",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                            
                            Text(
                                text = "الرقم الفريد للجهاز:",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            
                            OutlinedTextField(
                                value = uiState.deviceId,
                                onValueChange = { },
                                readOnly = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                            
                            Text(
                                text = "أرسل هذا الرقم للمطور للحصول على رمز التفعيل",
                                style = MaterialTheme.typography.bodySmall
                            )
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            Text(
                                text = "رمز التفعيل:",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            
                            OutlinedTextField(
                                value = uiState.activationCode,
                                onValueChange = { viewModel.updateActivationCode(it) },
                                modifier = Modifier.fillMaxWidth(),
                                isError = uiState.activationError != null,
                                supportingText = { 
                                    uiState.activationError?.let { Text(it) }
                                }
                            )
                            
                            Button(
                                onClick = { viewModel.activateApp() },
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Text("تفعيل")
                            }
                        }
                    }
                }
            )
            
            // معلومات التطبيق
            SettingsCard(
                title = "معلومات التطبيق",
                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Nawajeth - Dental Clinic Manager",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        
                        Text(
                            text = "الإصدار: ${uiState.appVersion}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        
                        Text(
                            text = "تطوير: ${uiState.developer}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SettingsCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                }
                
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            
            Divider()
            
            content()
        }
    }
}

@Composable
fun ThemeButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
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
fun LanguageButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
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
fun BackupFrequencyButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
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
