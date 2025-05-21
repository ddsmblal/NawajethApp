package com.manus.nawajethapp.ui.settings

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manus.nawajethapp.data.model.MedicalSymbol
import com.manus.nawajethapp.data.model.WorkDefinition
import com.manus.nawajethapp.viewmodel.SettingsViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToMedicalSymbols: () -> Unit,
    onNavigateToWorkDefinitions: () -> Unit,
    onNavigateToBackupRestore: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, "Back")
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
                .padding(16.dp)
        ) {
            SettingsItem(title = "Manage Medical Symbols", onClick = onNavigateToMedicalSymbols)
            SettingsItem(title = "Manage Work Definitions & Prices", onClick = onNavigateToWorkDefinitions)
            SettingsItem(title = "Backup and Restore", onClick = onNavigateToBackupRestore)
            // Add more settings items here as needed
        }
    }
}

@Composable
fun SettingsItem(title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicalSymbolsScreen(
    settingsViewModel: SettingsViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val symbols by settingsViewModel.allMedicalSymbols.observeAsState(emptyList())
    var showAddDialog by remember { mutableStateOf(false) }
    var symbolToEdit by remember { mutableStateOf<MedicalSymbol?>(null) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                // For simplicity, assuming SVG content is read as text or PNG path is stored
                // This part needs robust handling for file types and storage
                val fileName = "custom_symbol_${System.currentTimeMillis()}"
                val symbolType = if (context.contentResolver.getType(it)?.contains("svg") == true) "svg" else "png"
                var symbolDataPath: String? = null

                try {
                    val inputStream: InputStream? = context.contentResolver.openInputStream(it)
                    if (inputStream != null) {
                        val directory = File(context.filesDir, "custom_symbols")
                        if (!directory.exists()) directory.mkdirs()
                        val file = File(directory, "$fileName.${if (symbolType == "svg") "svg" else "png"}")
                        val outputStream = FileOutputStream(file)
                        inputStream.copyTo(outputStream)
                        symbolDataPath = file.absolutePath
                        inputStream.close()
                        outputStream.close()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Handle error
                }

                symbolDataPath?.let {
                    val newSymbol = MedicalSymbol(
                        symbolName = symbolToEdit?.symbolName ?: "New Symbol", // Prompt for name
                        symbolType = symbolType,
                        symbolData = it
                    )
                    if (symbolToEdit != null) {
                        settingsViewModel.updateMedicalSymbol(newSymbol.copy(id = symbolToEdit!!.id))
                    } else {
                        settingsViewModel.insertMedicalSymbol(newSymbol)
                    }
                    symbolToEdit = null
                    showAddDialog = false // Close dialog if it was for picking file for existing
                }
            }
        }
    )

    if (showAddDialog || symbolToEdit != null) {
        AddEditMedicalSymbolDialog(
            symbol = symbolToEdit,
            onDismiss = { showAddDialog = false; symbolToEdit = null },
            onConfirm = {
                name, type, data -> // Data here is path or SVG string
                val sym = symbolToEdit?.copy(symbolName = name, symbolType = type, symbolData = data) 
                            ?: MedicalSymbol(symbolName = name, symbolType = type, symbolData = data)
                if (symbolToEdit != null) settingsViewModel.updateMedicalSymbol(sym)
                else settingsViewModel.insertMedicalSymbol(sym)
                showAddDialog = false
                symbolToEdit = null
            },
            onPickFile = {
                // This is tricky if dialog is for new symbol and needs name first
                // Or if editing, it might replace existing file path
                filePickerLauncher.launch("image/*,image/svg+xml") // Accept SVG and common image types
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Medical Symbols") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Filled.Add, "Add Symbol")
            }
        }
    ) {
        paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            items(symbols) { symbol ->
                ListItem(
                    headlineContent = { Text(symbol.symbolName) },
                    supportingContent = { Text("Type: ${symbol.symbolType} | Data: ${File(symbol.symbolData).name}") },
                    trailingContent = {
                        Row {
                            IconButton(onClick = { symbolToEdit = symbol }) {
                                Icon(Icons.Filled.Edit, "Edit Symbol")
                            }
                            IconButton(onClick = { settingsViewModel.deleteMedicalSymbol(symbol) 
                                // Also delete the file from storage
                                try { File(symbol.symbolData).delete() } catch (e: Exception) { e.printStackTrace() }
                            }) {
                                Icon(Icons.Filled.Delete, "Delete Symbol", tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun AddEditMedicalSymbolDialog(
    symbol: MedicalSymbol?,
    onDismiss: () -> Unit,
    onConfirm: (name: String, type: String, data: String) -> Unit,
    onPickFile: () -> Unit
) {
    var symbolName by remember { mutableStateOf(symbol?.symbolName ?: "") }
    // For simplicity, type and data (path) are handled by file picker for now
    // A more robust dialog would allow manual path input or SVG string input

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (symbol == null) "Add Medical Symbol" else "Edit Medical Symbol") },
        text = {
            Column {
                OutlinedTextField(
                    value = symbolName,
                    onValueChange = { symbolName = it },
                    label = { Text("Symbol Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onPickFile) {
                    Text(if (symbol?.symbolData?.isNotEmpty() == true) "Change File (${File(symbol.symbolData).name})" else "Pick Symbol File (PNG/SVG)")
                }
                if (symbol?.symbolData?.isNotEmpty() == true) {
                    Text("Current file: ${File(symbol.symbolData).name}", style = MaterialTheme.typography.bodySmall)
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                // Type and Data should be updated by the file picker callback, not directly here
                // This confirm is more for the name if file is already picked.
                // Or, if no file picked yet, it might just save the name for later file association.
                // For now, we assume file picker updates the symbol being edited or a temporary state.
                // This dialog needs to be more stateful regarding the file picking process.
                if (symbolName.isNotBlank()) { // Basic validation
                    // The actual symbol data (path) should be passed from the state updated by filePickerLauncher
                    // This is a simplified onConfirm, real logic would be more complex
                    // onConfirm(symbolName, symbol?.symbolType ?: "png", symbol?.symbolData ?: "")
                    // Let's assume the onPickFile has already updated a temporary symbol object or called onConfirm
                    // If not, this confirm button might be disabled until a file is picked for a new symbol.
                    // For editing name only:
                    if (symbol != null && symbol.symbolData.isNotBlank()) {
                         onConfirm(symbolName, symbol.symbolType, symbol.symbolData)
                    } else if (symbol == null) {
                        // For new symbol, file must be picked. This button might be disabled.
                        // Or it triggers file picker if name is set.
                    }
                }
                onDismiss() // Dismiss after attempting action
            }) { Text("Save Name") } // Button text might change based on state
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkDefinitionsScreen(
    settingsViewModel: SettingsViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val works by settingsViewModel.allWorkDefinitions.observeAsState(emptyList())
    val symbols by settingsViewModel.allMedicalSymbols.observeAsState(emptyList())
    var showAddDialog by remember { mutableStateOf(false) }
    var workToEdit by remember { mutableStateOf<WorkDefinition?>(null) }

    if (showAddDialog || workToEdit != null) {
        AddEditWorkDefinitionDialog(
            workDefinition = workToEdit,
            symbols = symbols,
            onDismiss = { showAddDialog = false; workToEdit = null },
            onConfirm = { name, price, category, symbolId ->
                val work = workToEdit?.copy(workName = name, defaultPrice = price, category = category, defaultSymbolId = symbolId)
                    ?: WorkDefinition(workName = name, defaultPrice = price, category = category, defaultSymbolId = symbolId)
                if (workToEdit != null) settingsViewModel.updateWorkDefinition(work)
                else settingsViewModel.insertWorkDefinition(work)
                showAddDialog = false
                workToEdit = null
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Work Definitions") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Filled.Add, "Add Work Definition")
            }
        }
    ) {
        paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            items(works) { work ->
                val associatedSymbol = symbols.find { it.id == work.defaultSymbolId }
                ListItem(
                    headlineContent = { Text(work.workName) },
                    supportingContent = { 
                        Text("Price: ${work.defaultPrice} | Category: ${work.category ?: "N/A"} | Symbol: ${associatedSymbol?.symbolName ?: "None"}") 
                    },
                    trailingContent = {
                        Row {
                            IconButton(onClick = { workToEdit = work }) {
                                Icon(Icons.Filled.Edit, "Edit Work")
                            }
                            IconButton(onClick = { settingsViewModel.deleteWorkDefinition(work) }) {
                                Icon(Icons.Filled.Delete, "Delete Work", tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                )
                HorizontalDivider()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditWorkDefinitionDialog(
    workDefinition: WorkDefinition?,
    symbols: List<MedicalSymbol>,
    onDismiss: () -> Unit,
    onConfirm: (name: String, price: Double, category: String?, symbolId: Long?) -> Unit
) {
    var workName by remember { mutableStateOf(workDefinition?.workName ?: "") }
    var defaultPrice by remember { mutableStateOf(workDefinition?.defaultPrice?.toString() ?: "") }
    var category by remember { mutableStateOf(workDefinition?.category ?: "") }
    var selectedSymbolId by remember { mutableStateOf(workDefinition?.defaultSymbolId) }
    var symbolDropdownExpanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (workDefinition == null) "Add Work Definition" else "Edit Work Definition") },
        text = {
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                OutlinedTextField(
                    value = workName,
                    onValueChange = { workName = it },
                    label = { Text("Work Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = defaultPrice,
                    onValueChange = { defaultPrice = it },
                    label = { Text("Default Price") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Category (Optional)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box {
                    OutlinedTextField(
                        value = symbols.find { it.id == selectedSymbolId }?.symbolName ?: "Select Symbol (Optional)",
                        onValueChange = {}, // Not directly editable
                        label = { Text("Default Symbol") },
                        modifier = Modifier.fillMaxWidth().clickable { symbolDropdownExpanded = true },
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = symbolDropdownExpanded) }
                    )
                    ExposedDropdownMenu(
                        expanded = symbolDropdownExpanded,
                        onDismissRequest = { symbolDropdownExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("None") },
                            onClick = { selectedSymbolId = null; symbolDropdownExpanded = false }
                        )
                        symbols.forEach { symbol ->
                            DropdownMenuItem(
                                text = { Text(symbol.symbolName) },
                                onClick = { selectedSymbolId = symbol.id; symbolDropdownExpanded = false }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val priceDouble = defaultPrice.toDoubleOrNull()
                if (workName.isNotBlank() && priceDouble != null) {
                    onConfirm(workName, priceDouble, category.ifBlank { null }, selectedSymbolId)
                    onDismiss()
                }
            }) { Text("Save") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}

// TODO: Backup and Restore Screen (Placeholder)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackupRestoreScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Backup and Restore") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, "Back")
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
            Text("Backup and Restore functionality to be implemented.", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* TODO: Implement manual backup */ }) {
                Text("Manual Backup to Local Storage")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { /* TODO: Implement restore from local */ }) {
                Text("Restore from Local Storage")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Google Drive integration based on user preference (manual with weekly reminder).", style = MaterialTheme.typography.bodyMedium)
            // TODO: Add UI for Google Drive interactions if needed, or just info about the reminder.
        }
    }
}

