package com.example.nawajeth.data.db.dao

import androidx.room.*
import com.example.nawajeth.data.db.entity.WorkTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkTypeDao {
    @Query("SELECT * FROM work_types WHERE isActive = 1 ORDER BY name ASC")
    fun getAllActiveWorkTypes(): Flow<List<WorkTypeEntity>>
    
    @Query("SELECT * FROM work_types ORDER BY name ASC")
    fun getAllWorkTypes(): Flow<List<WorkTypeEntity>>
    
    @Query("SELECT * FROM work_types WHERE id = :workTypeId")
    suspend fun getWorkTypeById(workTypeId: Long): WorkTypeEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkType(workType: WorkTypeEntity): Long
    
    @Update
    suspend fun updateWorkType(workType: WorkTypeEntity)
    
    @Delete
    suspend fun deleteWorkType(workType: WorkTypeEntity)
    
    @Query("DELETE FROM work_types WHERE id = :workTypeId")
    suspend fun deleteWorkTypeById(workTypeId: Long)
}
