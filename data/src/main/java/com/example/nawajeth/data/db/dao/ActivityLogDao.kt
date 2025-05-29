package com.example.nawajeth.data.db.dao

import androidx.room.*
import com.example.nawajeth.data.db.entity.ActivityLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityLogDao {
    @Query("SELECT * FROM activity_logs ORDER BY timestamp DESC")
    fun getAllActivityLogs(): Flow<List<ActivityLogEntity>>
    
    @Query("SELECT * FROM activity_logs WHERE patientId = :patientId ORDER BY timestamp DESC")
    fun getActivityLogsByPatientId(patientId: Long): Flow<List<ActivityLogEntity>>
    
    @Query("SELECT * FROM activity_logs WHERE activityType = :activityType ORDER BY timestamp DESC")
    fun getActivityLogsByType(activityType: String): Flow<List<ActivityLogEntity>>
    
    @Query("SELECT * FROM activity_logs ORDER BY timestamp DESC LIMIT :limit")
    fun getRecentActivityLogs(limit: Int): Flow<List<ActivityLogEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivityLog(activityLog: ActivityLogEntity): Long
    
    @Delete
    suspend fun deleteActivityLog(activityLog: ActivityLogEntity)
    
    @Query("DELETE FROM activity_logs WHERE timestamp < :timestamp")
    suspend fun deleteActivityLogsBefore(timestamp: Long): Int
}
