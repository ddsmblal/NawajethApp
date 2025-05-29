package com.example.nawajeth.features.settings.domain.repository

import com.example.nawajeth.features.settings.domain.model.LicenseStatus
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun getLicenseStatus(): LicenseStatus
    suspend fun activateApp(activationCode: String): Result<Boolean>
    suspend fun getDeviceId(): String
    
    fun getAppTheme(): Flow<String>
    suspend fun setAppTheme(theme: String)
    
    fun getAppLanguage(): Flow<String>
    suspend fun setAppLanguage(language: String)
    
    suspend fun createBackup(path: String): Result<Boolean>
    suspend fun restoreBackup(path: String): Result<Boolean>
    
    suspend fun isAutoBackupEnabled(): Boolean
    suspend fun setAutoBackupEnabled(enabled: Boolean)
    
    suspend fun getBackupFrequency(): Int
    suspend fun setBackupFrequency(days: Int)
    
    suspend fun getBackupLocation(): String
    suspend fun setBackupLocation(path: String)
}
