package com.example.nawajeth.data.db.dao

import androidx.room.*
import com.example.nawajeth.data.db.entity.AppSettingsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppSettingsDao {
    @Query("SELECT * FROM app_settings WHERE id = 1")
    fun getAppSettings(): Flow<AppSettingsEntity?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppSettings(appSettings: AppSettingsEntity)
    
    @Update
    suspend fun updateAppSettings(appSettings: AppSettingsEntity)
    
    @Query("UPDATE app_settings SET language = :language WHERE id = 1")
    suspend fun updateLanguage(language: String)
    
    @Query("UPDATE app_settings SET theme = :theme WHERE id = 1")
    suspend fun updateTheme(theme: String)
    
    @Query("UPDATE app_settings SET backupFrequency = :frequency WHERE id = 1")
    suspend fun updateBackupFrequency(frequency: Int)
    
    @Query("UPDATE app_settings SET lastBackupDate = :date WHERE id = 1")
    suspend fun updateLastBackupDate(date: Long)
    
    @Query("UPDATE app_settings SET isActivated = :isActivated WHERE id = 1")
    suspend fun updateActivationStatus(isActivated: Boolean)
    
    @Query("UPDATE app_settings SET activationCode = :code WHERE id = 1")
    suspend fun updateActivationCode(code: String?)
}
