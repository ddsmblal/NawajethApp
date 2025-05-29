package com.example.nawajeth.features.backup.domain.repository

import kotlinx.coroutines.flow.Flow

interface BackupRepository {
    suspend fun createBackup(path: String): Result<Boolean>
    suspend fun restoreBackup(path: String): Result<Boolean>
    suspend fun getBackupHistory(): List<BackupInfo>
    suspend fun deleteBackup(backupId: String): Result<Boolean>
    suspend fun scheduleAutomaticBackup(frequencyDays: Int, path: String): Result<Boolean>
    suspend fun isAutoBackupEnabled(): Boolean
    suspend fun setAutoBackupEnabled(enabled: Boolean)
    suspend fun getBackupFrequency(): Int
    suspend fun setBackupFrequency(days: Int)
    suspend fun getBackupLocation(): String
    suspend fun setBackupLocation(path: String)
}

data class BackupInfo(
    val id: String,
    val path: String,
    val date: Long,
    val size: Long,
    val patientsCount: Int
)
