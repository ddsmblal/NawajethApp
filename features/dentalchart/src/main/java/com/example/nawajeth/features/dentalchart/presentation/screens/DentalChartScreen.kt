package com.example.nawajeth.features.dentalchart.presentation.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol
import com.example.nawajeth.features.dentalchart.presentation.viewmodel.DentalChartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DentalChartScreen(
    viewModel: DentalChartViewModel,
    patientId: Long,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(patientId) {
        viewModel.loadPatientData(patientId)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "مخطط الأسنان") },
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
                onClick = { viewModel.saveChanges(onSuccess = onNavigateBack) },
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
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = "العمر: ${uiState.patientAge} سنة | الجنس: ${uiState.patientGender}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // شريط الرموز
            Text(
                text = "الرموز",
                style = MaterialTheme.typography.titleSmall
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(uiState.availableSymbols) { symbol ->
                    SymbolItem(
                        symbol = symbol,
                        isSelected = symbol.id == uiState.selectedSymbolId,
                        onClick = { viewModel.selectSymbol(symbol.id) }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // مخطط الأسنان
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // الفك العلوي
                    Text(
                        text = "الفك العلوي",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // الأسنان العلوية اليمنى (18-11)
                        for (i in 18 downTo 11) {
                            ToothItem(
                                toothNumber = i,
                                conditions = uiState.teethConditions[i] ?: emptyList(),
                                onClick = { viewModel.onToothClick(i) }
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // الأسنان العلوية اليسرى (21-28)
                        for (i in 21..28) {
                            ToothItem(
                                toothNumber = i,
                                conditions = uiState.teethConditions[i] ?: emptyList(),
                                onClick = { viewModel.onToothClick(i) }
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // الفك السفلي
                    Text(
                        text = "الفك السفلي",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // الأسنان السفلية اليمنى (48-41)
                        for (i in 48 downTo 41) {
                            ToothItem(
                                toothNumber = i,
                                conditions = uiState.teethConditions[i] ?: emptyList(),
                                onClick = { viewModel.onToothClick(i) }
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // الأسنان السفلية اليسرى (31-38)
                        for (i in 31..38) {
                            ToothItem(
                                toothNumber = i,
                                conditions = uiState.teethConditions[i] ?: emptyList(),
                                onClick = { viewModel.onToothClick(i) }
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // ملاحظات
            OutlinedTextField(
                value = uiState.notes,
                onValueChange = { viewModel.updateNotes(it) },
                label = { Text("ملاحظات") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            
            Spacer(modifier = Modifier.height(80.dp)) // للتأكد من أن FAB لا يغطي المحتوى
        }
    }
    
    // حوار تفاصيل السن
    if (uiState.selectedToothNumber != null) {
        ToothDetailsDialog(
            toothNumber = uiState.selectedToothNumber!!,
            conditions = uiState.teethConditions[uiState.selectedToothNumber!!] ?: emptyList(),
            selectedSymbol = uiState.availableSymbols.find { it.id == uiState.selectedSymbolId },
            onAddCondition = { viewModel.addConditionToTooth() },
            onRemoveCondition = { conditionId -> viewModel.removeConditionFromTooth(conditionId) },
            onDismiss = { viewModel.clearSelectedTooth() }
        )
    }
}

@Composable
fun SymbolItem(
    symbol: DentalSymbol,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(60.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        ),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            // هنا يمكن عرض رمز السن (صورة أو رسم)
            // في هذا المثال، نستخدم لون فقط
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color(android.graphics.Color.parseColor(symbol.color)))
            )
        }
    }
}

@Composable
fun ToothItem(
    toothNumber: Int,
    conditions: List<DentalSymbol>,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .width(40.dp)
    ) {
        Text(
            text = toothNumber.toString(),
            style = MaterialTheme.typography.bodySmall
        )
        
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { onClick() }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            // رسم السن
            Canvas(modifier = Modifier.fillMaxSize()) {
                // رسم مخطط السن
                drawRect(
                    color = Color.Gray,
                    style = Stroke(width = 2f)
                )
                
                // إذا كان هناك حالات، نرسم الرمز الأول فقط هنا
                if (conditions.isNotEmpty()) {
                    val symbol = conditions.first()
                    drawCircle(
                        color = Color(android.graphics.Color.parseColor(symbol.color)),
                        radius = size.minDimension / 3,
                        center = Offset(size.width / 2, size.height / 2)
                    )
                }
            }
            
            // إذا كان هناك أكثر من حالة، نضيف مؤشر
            if (conditions.size > 1) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                )
            }
        }
    }
}

@Composable
fun ToothDetailsDialog(
    toothNumber: Int,
    conditions: List<DentalSymbol>,
    selectedSymbol: DentalSymbol?,
    onAddCondition: () -> Unit,
    onRemoveCondition: (Long) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("السن رقم $toothNumber") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("الحالات المسجلة:")
                
                Spacer(modifier = Modifier.height(8.dp))
                
                if (conditions.isEmpty()) {
                    Text(
                        text = "لا توجد حالات مسجلة لهذا السن",
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else {
                    conditions.forEach { condition ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clip(CircleShape)
                                        .background(Color(android.graphics.Color.parseColor(condition.color)))
                                )
                                
                                Spacer(modifier = Modifier.width(8.dp))
                                
                                Text(text = condition.name)
                            }
                            
                            IconButton(
                                onClick = { onRemoveCondition(condition.id) },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "حذف",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text("إضافة حالة جديدة:")
                
                Spacer(modifier = Modifier.height(8.dp))
                
                if (selectedSymbol == null) {
                    Text(
                        text = "الرجاء اختيار رمز من شريط الرموز أولاً",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color(android.graphics.Color.parseColor(selectedSymbol.color)))
                        )
                        
                        Spacer(modifier = Modifier.width(8.dp))
                        
                        Text(
                            text = selectedSymbol.name,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Button(
                        onClick = onAddCondition,
                        modifier = Modifier.align(Alignment.End),
                        enabled = selectedSymbol != null
                    ) {
                        Text("إضافة")
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("إغلاق")
            }
        }
    )
}
