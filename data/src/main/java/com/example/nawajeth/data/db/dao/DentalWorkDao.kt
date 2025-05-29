package com.example.nawajeth.data.db.dao

import androidx.room.*
import com.example.nawajeth.data.db.entity.DentalWorkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DentalWorkDao {
    @Query("SELECT * FROM dental_works WHERE patientId = :patientId ORDER BY startDate DESC")
    fun getDentalWorksByPatientId(patientId: Long): Flow<List<DentalWorkEntity>>
    
    @Query("SELECT * FROM dental_works WHERE id = :workId")
    suspend fun getDentalWorkById(workId: Long): DentalWorkEntity?
    
    @Query("SELECT * FROM dental_works WHERE status = :status ORDER BY startDate DESC")
    fun getDentalWorksByStatus(status: String): Flow<List<DentalWorkEntity>>
    
    @Query("SELECT * FROM dental_works WHERE toothNumber = :toothNumber AND patientId = :patientId")
    fun getDentalWorksByTooth(patientId: Long, toothNumber: Int): Flow<List<DentalWorkEntity>>
    
    @Query("SELECT SUM(remainingAmount) FROM dental_works WHERE patientId = :patientId")
    suspend fun getTotalRemainingAmountForPatient(patientId: Long): Double?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDentalWork(dentalWork: DentalWorkEntity): Long
    
    @Update
    suspend fun updateDentalWork(dentalWork: DentalWorkEntity)
    
    @Delete
    suspend fun deleteDentalWork(dentalWork: DentalWorkEntity)
    
    @Query("DELETE FROM dental_works WHERE id = :workId")
    suspend fun deleteDentalWorkById(workId: Long)
    
    @Query("SELECT COUNT(*) FROM dental_works WHERE patientId = :patientId")
    suspend fun getDentalWorkCountForPatient(patientId: Long): Int
}
