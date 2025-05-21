package com.manus.nawajethapp.ui.settings

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material.icons.filled.SettingsApplications
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manus.nawajethapp.viewmodel.BackupViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackupRestoreScreen(
    backupViewModel: BackupViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val localBackups by backupViewModel.localBackups.observeAsState(emptyList())
    val toastMessage by backupViewModel.toastMessage.observeAsState()
    var showRestoreConfirmationDialog by remember { mutableStateOf<Uri?>(null) }
    var showDeleteConfirmationDialog by remember { mutableStateOf<File?>(null) }
    var showPermissionRationaleDialog by remember { mutableStateOf(false) }

    LaunchedEffect(toastMessage) {
        if (!toastMessage.isNullOrEmpty()) {
            // You would typically show a Toast here
            // For simplicity in this environment, we might just log or update a Text field
            println("Toast: $toastMessage")
            backupViewModel.clearToastMessage() // Clear message after showing
        }
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true && permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true) {
            backupViewModel.backupDatabaseLocal()
        } else {
            showPermissionRationaleDialog = true
        }
    }

    val restoreFilePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                showRestoreConfirmationDialog = it
            }
        }
    )

    if (showRestoreConfirmationDialog != null) {
        AlertDialog(
            onDismissRequest = { showRestoreConfirmationDialog = null },
            title = { Text("Confirm Restore") },
            text = { Text("Restoring will overwrite current data. Are you sure? This action requires an app restart after completion.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showRestoreConfirmationDialog?.let { backupViewModel.restoreDatabaseLocal(it) }
                        showRestoreConfirmationDialog = null
                    }
                ) { Text("Restore") }
            },
            dismissButton = { TextButton(onClick = { showRestoreConfirmationDialog = null }) { Text("Cancel") } }
        )
    }
    
    if (showDeleteConfirmationDialog != null) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmationDialog = null },
            title = { Text("Confirm Delete") },
            text = { Text("Are you sure you want to delete this backup file?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteConfirmationDialog?.let { backupViewModel.deleteLocalBackup(it) }
                        showDeleteConfirmationDialog = null
                    }
                ) { Text("Delete") }
            },
            dismissButton = { TextButton(onClick = { showDeleteConfirmationDialog = null }) { Text("Cancel") } }
        )
    }

    if (showPermissionRationaleDialog) {
        AlertDialog(
            onDismissRequest = { showPermissionRationaleDialog = false },
            title = { Text("Permission Required") },
            text = { Text("Storage permission is required to create and manage backups. Please grant the permission in app settings.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        openAppSettings(context)
                        showPermissionRationaleDialog = false
                    }
                ) { Text("Open Settings") }
            },
            dismissButton = { TextButton(onClick = { showPermissionRationaleDialog = false }) { Text("Cancel") } }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Backup & Restore") },
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Local Backups", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissionLauncher.launch(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE))
                    } else {
                        backupViewModel.backupDatabaseLocal()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.Backup, contentDescription = "Backup Icon", modifier = Modifier.padding(end = 8.dp))
                Text("Create Local Backup")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { restoreFilePickerLauncher.launch("*/*") }, // Or be more specific like "application/octet-stream" or "application/x-sqlite3"
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.Restore, contentDescription = "Restore Icon", modifier = Modifier.padding(end = 8.dp))
                Text("Restore from Local Backup")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Available Local Backups", style = MaterialTheme.typography.titleSmall)
            if (localBackups.isEmpty()) {
                Text("No local backups found.", modifier = Modifier.padding(vertical = 16.dp))
            } else {
                LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth()) {
                    items(localBackups) { file ->
                        BackupItem(file = file, 
                            onDeleteClicked = { showDeleteConfirmationDialog = file },
                            onShareClicked = { shareBackupFile(context, file) }
                        )
                        Divider()
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Google Drive (Manual Backup with Weekly Reminder - As Per User Preference)", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { backupViewModel.backupToGoogleDrive() }, // Placeholder
                modifier = Modifier.fillMaxWidth(),
                enabled = false // Disabled until implemented
            ) {
                Icon(Icons.Filled.CloudUpload, contentDescription = "Google Drive Backup Icon", modifier = Modifier.padding(end = 8.dp))
                Text("Backup to Google Drive (Not Implemented)")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { backupViewModel.restoreFromGoogleDrive("") }, // Placeholder
                modifier = Modifier.fillMaxWidth(),
                enabled = false // Disabled until implemented
            ) {
                Icon(Icons.Filled.Restore, contentDescription = "Google Drive Restore Icon", modifier = Modifier.padding(end = 8.dp))
                Text("Restore from Google Drive (Not Implemented)")
            }
            Text("Note: Google Drive integration is planned. For now, please use local backups. You will be reminded weekly to perform a manual backup.", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top=8.dp))
        }
    }
}

@Composable
fun BackupItem(file: File, onDeleteClicked: () -> Unit, onShareClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        ListItem(
            headlineContent = { Text(file.name, fontWeight = FontWeight.SemiBold) },
            supportingContent = { 
                Text("Size: ${file.length() / 1024} KB\nDate: ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date(file.lastModified()))}") 
            },
            leadingContent = { Icon(Icons.Filled.Description, contentDescription = "Backup File") },
            trailingContent = {
                Row {
                    IconButton(onClick = onShareClicked) {
                        Icon(Icons.Filled.Share, contentDescription = "Share Backup")
                    }
                    IconButton(onClick = onDeleteClicked) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete Backup", tint = MaterialTheme.colorScheme.error)
                    }
                }
            }
        )
    }
}

fun openAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", context.packageName, null)
    intent.data = uri
    context.startActivity(intent)
}

fun shareBackupFile(context: Context, file: File) {
    val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    val shareIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "application/octet-stream" // Or specific DB MIME type if known
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share Backup File"))
}

// Ensure you have a FileProvider set up in your AndroidManifest.xml
// <provider
//     android:name="androidx.core.content.FileProvider"
//     android:authorities="${applicationId}.provider"
//     android:exported="false"
//     android:grantUriPermissions="true">
//     <meta-data
//         android:name="android.support.FILE_PROVIDER_PATHS"
//         android:resource="@xml/file_paths" />
// </provider>

// And res/xml/file_paths.xml:
// <?xml version="1.0" encoding="utf-8"?>
// <paths xmlns:android="http://schemas.android.com/apk/res/android">
//     <external-files-path name="my_backups" path="NawajethAppBackups/" />
// </paths>


