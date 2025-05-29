package com.example.nawajeth.data.db.dao

import androidx.room.*
import com.example.nawajeth.data.db.entity.PatientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {
    @Query("SELECT * FROM patients ORDER BY name ASC")
    fun getAllPatients(): Flow<List<PatientEntity>>
    
    @Query("SELECT * FROM patients WHERE id = :patientId")
    suspend fun getPatientById(patientId: Long): PatientEntity?
    
    @Query("SELECT * FROM patients WHERE name LIKE '%' || :searchQuery || '%' OR phone LIKE '%' || :searchQuery || '%'")
    fun searchPatients(searchQuery: String): Flow<List<PatientEntity>>
    
    @Query("SELECT * FROM patients ORDER BY lastVisitDate DESC LIMIT :limit")
    fun getRecentPatients(limit: Int): Flow<List<PatientEntity>>
    
    @Query("SELECT * FROM patients ORDER BY totalDue DESC")
    fun getPatientsByDueAmount(): Flow<List<PatientEntity>>
    
    @Query("SELECT COUNT(*) FROM patients")
    suspend fun getPatientCount(): Int
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatient(patient: PatientEntity): Long
    
    @Update
    suspend fun updatePatient(patient: PatientEntity)
    
    @Delete
    suspend fun deletePatient(patient: PatientEntity)
    
    @Query("DELETE FROM patients WHERE id = :patientId")
    suspend fun deletePatientById(patientId: Long)
}
