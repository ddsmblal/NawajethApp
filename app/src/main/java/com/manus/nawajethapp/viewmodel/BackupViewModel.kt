package com.manus.nawajethapp.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manus.nawajethapp.data.local.AppDatabase
import com.manus.nawajethapp.data.repository.BackupRepository
import kotlinx.coroutines.launch
import java.io.File

class BackupViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BackupRepository
    private val _backupResult = MutableLiveData<String?>()
    val backupResult: LiveData<String?> = _backupResult

    private val _restoreResult = MutableLiveData<Boolean>()
    val restoreResult: LiveData<Boolean> = _restoreResult

    private val _localBackups = MutableLiveData<List<File>>()
    val localBackups: LiveData<List<File>> = _localBackups

    private val _backupDeletedResult = MutableLiveData<Boolean>()
    val backupDeletedResult: LiveData<Boolean> = _backupDeletedResult
    
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    init {
        val appDatabase = AppDatabase.getDatabase(application)
        repository = BackupRepository(application.applicationContext, appDatabase)
        loadLocalBackups()
    }

    fun backupDatabaseLocal() {
        viewModelScope.launch {
            val result = repository.backupDatabaseLocal()
            _backupResult.postValue(result)
            if (result != null) {
                _toastMessage.postValue("Backup created successfully at: $result")
                loadLocalBackups() // Refresh list
            } else {
                _toastMessage.postValue("Backup failed.")
            }
        }
    }

    fun restoreDatabaseLocal(backupFileUri: Uri) {
        viewModelScope.launch {
            val success = repository.restoreDatabaseLocal(backupFileUri)
            _restoreResult.postValue(success)
             if (success) {
                _toastMessage.postValue("Database restored successfully. Please restart the app.")
            } else {
                _toastMessage.postValue("Database restore failed.")
            }
        }
    }

    fun loadLocalBackups() {
        viewModelScope.launch {
            _localBackups.postValue(repository.getLocalBackups())
        }
    }

    fun deleteLocalBackup(backupFile: File) {
        viewModelScope.launch {
            val success = repository.deleteLocalBackup(backupFile)
            _backupDeletedResult.postValue(success)
            if (success) {
                _toastMessage.postValue("Backup file deleted: ${backupFile.name}")
                loadLocalBackups() // Refresh list
            } else {
                _toastMessage.postValue("Failed to delete backup file: ${backupFile.name}")
            }
        }
    }
    
    // Placeholder for Google Drive functionality
    fun backupToGoogleDrive() {
        viewModelScope.launch {
            _toastMessage.postValue("Google Drive backup is not yet implemented.")
            // val result = repository.backupDatabaseToGoogleDrive()
            // Handle result
        }
    }

    fun restoreFromGoogleDrive(fileId: String) {
        viewModelScope.launch {
             _toastMessage.postValue("Google Drive restore is not yet implemented.")
            // val success = repository.restoreDatabaseFromGoogleDrive(fileId)
            // Handle result
        }
    }
     fun clearToastMessage() {
        _toastMessage.value = ""
    }
}

