package com.manus.nawajethapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.manus.nawajethapp.data.model.LicenseStatus

@Dao
interface LicenseStatusDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLicenseStatus(licenseStatus: LicenseStatus): Long

    @Update
    suspend fun updateLicenseStatus(licenseStatus: LicenseStatus)

    @Query("SELECT * FROM license_status WHERE id = 1")
    fun getLicenseStatus(): LiveData<LicenseStatus?>

    @Query("SELECT COUNT(*) FROM patients")
    suspend fun getPatientsCount(): Int

    @Query("SELECT EXISTS(SELECT 1 FROM license_status WHERE id = 1 AND isFullVersion = 1)")
    suspend fun isFullVersionActivated(): Boolean
}
