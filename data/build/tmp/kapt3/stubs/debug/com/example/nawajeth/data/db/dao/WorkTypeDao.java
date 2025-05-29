package com.example.nawajeth.data.db.dao;

import androidx.room.*;
import com.example.nawajeth.data.db.entity.WorkTypeEntity;
import kotlinx.coroutines.flow.Flow;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u001b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lcom/example/nawajeth/data/db/dao/WorkTypeDao;", "", "deleteWorkType", "", "workType", "Lcom/example/nawajeth/data/db/entity/WorkTypeEntity;", "(Lcom/example/nawajeth/data/db/entity/WorkTypeEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteWorkTypeById", "workTypeId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllActiveWorkTypes", "Lkotlinx/coroutines/flow/Flow;", "", "getAllWorkTypes", "getWorkTypeById", "insertWorkType", "updateWorkType", "data_debug"})
public abstract interface WorkTypeDao {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM work_types WHERE isActive = 1 ORDER BY name ASC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.WorkTypeEntity>> getAllActiveWorkTypes();
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM work_types ORDER BY name ASC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.WorkTypeEntity>> getAllWorkTypes();
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT * FROM work_types WHERE id = :workTypeId")
    public abstract java.lang.Object getWorkTypeById(long workTypeId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.nawajeth.data.db.entity.WorkTypeEntity> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 1)
    public abstract java.lang.Object insertWorkType(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.WorkTypeEntity workType, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Update
    public abstract java.lang.Object updateWorkType(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.WorkTypeEntity workType, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Delete
    public abstract java.lang.Object deleteWorkType(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.WorkTypeEntity workType, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "DELETE FROM work_types WHERE id = :workTypeId")
    public abstract java.lang.Object deleteWorkTypeById(long workTypeId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}