package com.example.nawajeth.data.db.dao;

import androidx.room.*;
import com.example.nawajeth.data.db.entity.ActivityLogEntity;
import kotlinx.coroutines.flow.Flow;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\r2\u0006\u0010\u000f\u001a\u00020\nH\'J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\r2\u0006\u0010\u0011\u001a\u00020\u0012H\'J\u0014\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\rH\'J\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\r2\u0006\u0010\u0015\u001a\u00020\bH\'J\u0019\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/example/nawajeth/data/db/dao/ActivityLogDao;", "", "deleteActivityLog", "", "activityLog", "Lcom/example/nawajeth/data/db/entity/ActivityLogEntity;", "(Lcom/example/nawajeth/data/db/entity/ActivityLogEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteActivityLogsBefore", "", "timestamp", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActivityLogsByPatientId", "Lkotlinx/coroutines/flow/Flow;", "", "patientId", "getActivityLogsByType", "activityType", "", "getAllActivityLogs", "getRecentActivityLogs", "limit", "insertActivityLog", "data_debug"})
public abstract interface ActivityLogDao {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM activity_logs ORDER BY timestamp DESC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.ActivityLogEntity>> getAllActivityLogs();
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM activity_logs WHERE patientId = :patientId ORDER BY timestamp DESC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.ActivityLogEntity>> getActivityLogsByPatientId(long patientId);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM activity_logs WHERE activityType = :activityType ORDER BY timestamp DESC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.ActivityLogEntity>> getActivityLogsByType(@org.jetbrains.annotations.NotNull
    java.lang.String activityType);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM activity_logs ORDER BY timestamp DESC LIMIT :limit")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.ActivityLogEntity>> getRecentActivityLogs(int limit);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 1)
    public abstract java.lang.Object insertActivityLog(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.ActivityLogEntity activityLog, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Delete
    public abstract java.lang.Object deleteActivityLog(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.ActivityLogEntity activityLog, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "DELETE FROM activity_logs WHERE timestamp < :timestamp")
    public abstract java.lang.Object deleteActivityLogsBefore(long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation);
}