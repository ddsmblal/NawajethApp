package com.example.nawajeth.data.db.dao

import androidx.room.*
import com.example.nawajeth.data.db.entity.DentalConditionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DentalConditionDao {
    @Query("SELECT * FROM dental_conditions WHERE patientId = :patientId ORDER BY recordDate DESC")
    fun getDentalConditionsByPatientId(patientId: Long): Flow<List<DentalConditionEntity>>
    
    @Query("SELECT * FROM dental_conditions WHERE id = :conditionId")
    suspend fun getDentalConditionById(conditionId: Long): DentalConditionEntity?
    
    @Query("SELECT * FROM dental_conditions WHERE toothNumber = :toothNumber AND patientId = :patientId")
    fun getDentalConditionsByTooth(patientId: Long, toothNumber: Int): Flow<List<DentalConditionEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDentalCondition(dentalCondition: DentalConditionEntity): Long
    
    @Update
    suspend fun updateDentalCondition(dentalCondition: DentalConditionEntity)
    
    @Delete
    suspend fun deleteDentalCondition(dentalCondition: DentalConditionEntity)
    
    @Query("DELETE FROM dental_conditions WHERE id = :conditionId")
    suspend fun deleteDentalConditionById(conditionId: Long)
}
