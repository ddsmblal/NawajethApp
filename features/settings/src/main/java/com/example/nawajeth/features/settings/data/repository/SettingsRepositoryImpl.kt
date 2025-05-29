package com.example.nawajeth.features.settings.data.repository

import android.content.Context
import android.provider.Settings
import com.example.nawajeth.data.db.dao.AppSettingsDao
import com.example.nawajeth.features.settings.data.mapper.toEntity
import com.example.nawajeth.features.settings.data.mapper.toModel
import com.example.nawajeth.features.settings.domain.model.LicenseStatus
import com.example.nawajeth.features.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File
import java.security.MessageDigest
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val appSettingsDao: AppSettingsDao,
    private val context: Context
) : SettingsRepository {

    override suspend fun getLicenseStatus(): LicenseStatus {
        val settings = appSettingsDao.getAppSettings()
        return LicenseStatus(
            isActivated = settings?.isActivated ?: false,
            deviceId = getDeviceId(),
            activationDate = settings?.activationDate
        )
    }

    override suspend fun activateApp(activationCode: String): Result<Boolean> {
        return try {
            val deviceId = getDeviceId()
            val expectedCode = generateActivationCode(deviceId)
            
            if (activationCode == expectedCode) {
                val settings = appSettingsDao.getAppSettings()
                if (settings != null) {
                    val updatedSettings = settings.copy(
                        isActivated = true,
                        activationDate = System.currentTimeMillis()
                    )
                    appSettingsDao.updateSettings(updatedSettings)
                } else {
                    val newSettings = com.example.nawajeth.data.db.entity.AppSettingsEntity(
                        id = 1,
                        theme = "system",
                        language = "ar",
                        isActivated = true,
                        activationDate = System.currentTimeMillis(),
                        autoBackupEnabled = false,
                        backupFrequency = 7,
                        backupLocation = ""
                    )
                    appSettingsDao.insertSettings(newSettings)
                }
                Result.success(true)
            } else {
                Result.failure(Exception("رمز التفعيل غير صحيح"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getDeviceId(): String {
        val androidId = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )
        
        // تحويل معرف الجهاز إلى تنسيق أكثر قابلية للقراءة
        return androidId.chunked(4).joinToString("-").uppercase()
    }

    override fun getAppTheme(): Flow<String> {
        return appSettingsDao.getAppSettingsFlow().map { settings ->
            settings?.theme ?: "system"
        }
    }

    override suspend fun setAppTheme(theme: String) {
        val settings = appSettingsDao.getAppSettings()
        if (settings != null) {
            val updatedSettings = settings.copy(theme = theme)
            appSettingsDao.updateSettings(updatedSettings)
        } else {
            val newSettings = com.example.nawajeth.data.db.entity.AppSettingsEntity(
                id = 1,
                theme = theme,
                language = "ar",
                isActivated = false,
                autoBackupEnabled = false,
                backupFrequency = 7,
                backupLocation = ""
            )
            appSettingsDao.insertSettings(newSettings)
        }
    }

    override fun getAppLanguage(): Flow<String> {
        return appSettingsDao.getAppSettingsFlow().map { settings ->
            settings?.language ?: "ar"
        }
    }

    override suspend fun setAppLanguage(language: String) {
        val settings = appSettingsDao.getAppSettings()
        if (settings != null) {
            val updatedSettings = settings.copy(language = language)
            appSettingsDao.updateSettings(updatedSettings)
        } else {
            val newSettings = com.example.nawajeth.data.db.entity.AppSettingsEntity(
                id = 1,
                theme = "system",
                language = language,
                isActivated = false,
                autoBackupEnabled = false,
                backupFrequency = 7,
                backupLocation = ""
            )
            appSettingsDao.insertSettings(newSettings)
        }
    }

    override suspend fun createBackup(path: String): Result<Boolean> {
        return try {
            // تنفيذ عملية النسخ الاحتياطي (سيتم تنفيذها لاحقاً)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun restoreBackup(path: String): Result<Boolean> {
        return try {
            // تنفيذ عملية استعادة النسخ الاحتياطي (سيتم تنفيذها لاحقاً)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isAutoBackupEnabled(): Boolean {
        val settings = appSettingsDao.getAppSettings()
        return settings?.autoBackupEnabled ?: false
    }

    override suspend fun setAutoBackupEnabled(enabled: Boolean) {
        val settings = appSettingsDao.getAppSettings()
        if (settings != null) {
            val updatedSettings = settings.copy(autoBackupEnabled = enabled)
            appSettingsDao.updateSettings(updatedSettings)
        } else {
            val newSettings = com.example.nawajeth.data.db.entity.AppSettingsEntity(
                id = 1,
                theme = "system",
                language = "ar",
                isActivated = false,
                autoBackupEnabled = enabled,
                backupFrequency = 7,
                backupLocation = ""
            )
            appSettingsDao.insertSettings(newSettings)
        }
    }

    override suspend fun getBackupFrequency(): Int {
        val settings = appSettingsDao.getAppSettings()
        return settings?.backupFrequency ?: 7
    }

    override suspend fun setBackupFrequency(days: Int) {
        val settings = appSettingsDao.getAppSettings()
        if (settings != null) {
            val updatedSettings = settings.copy(backupFrequency = days)
            appSettingsDao.updateSettings(updatedSettings)
        } else {
            val newSettings = com.example.nawajeth.data.db.entity.AppSettingsEntity(
                id = 1,
                theme = "system",
                language = "ar",
                isActivated = false,
                autoBackupEnabled = false,
                backupFrequency = days,
                backupLocation = ""
            )
            appSettingsDao.insertSettings(newSettings)
        }
    }

    override suspend fun getBackupLocation(): String {
        val settings = appSettingsDao.getAppSettings()
        return settings?.backupLocation ?: ""
    }

    override suspend fun setBackupLocation(path: String) {
        val settings = appSettingsDao.getAppSettings()
        if (settings != null) {
            val updatedSettings = settings.copy(backupLocation = path)
            appSettingsDao.updateSettings(updatedSettings)
        } else {
            val newSettings = com.example.nawajeth.data.db.entity.AppSettingsEntity(
                id = 1,
                theme = "system",
                language = "ar",
                isActivated = false,
                autoBackupEnabled = false,
                backupFrequency = 7,
                backupLocation = path
            )
            appSettingsDao.insertSettings(newSettings)
        }
    }
    
    // دالة لتوليد رمز التفعيل من معرف الجهاز
    private fun generateActivationCode(deviceId: String): String {
        return deviceId
            .replace("-", "")
            .reversed()
            .toCharArray()
            .map { char ->
                when {
                    char.isDigit() -> ((char.digitToInt() + 5) % 10).digitToChar()
                    char.isLetter() -> {
                        val base = if (char.isUpperCase()) 'A' else 'a'
                        ((char.code - base.code + 13) % 26 + base.code).toChar()
                    }
                    else -> char
                }
            }
            .joinToString("")
            .chunked(4)
            .joinToString("-")
            .uppercase()
    }
}
