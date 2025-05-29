package com.example.nawajeth.data.db.dao;

import androidx.room.*;
import com.example.nawajeth.data.db.entity.DentalConditionEntity;
import kotlinx.coroutines.flow.Flow;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001b\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\r2\u0006\u0010\u000f\u001a\u00020\tH\'J$\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\r2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\'J\u0019\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lcom/example/nawajeth/data/db/dao/DentalConditionDao;", "", "deleteDentalCondition", "", "dentalCondition", "Lcom/example/nawajeth/data/db/entity/DentalConditionEntity;", "(Lcom/example/nawajeth/data/db/entity/DentalConditionEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDentalConditionById", "conditionId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDentalConditionById", "getDentalConditionsByPatientId", "Lkotlinx/coroutines/flow/Flow;", "", "patientId", "getDentalConditionsByTooth", "toothNumber", "", "insertDentalCondition", "updateDentalCondition", "data_debug"})
public abstract interface DentalConditionDao {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM dental_conditions WHERE patientId = :patientId ORDER BY recordDate DESC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.DentalConditionEntity>> getDentalConditionsByPatientId(long patientId);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT * FROM dental_conditions WHERE id = :conditionId")
    public abstract java.lang.Object getDentalConditionById(long conditionId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.nawajeth.data.db.entity.DentalConditionEntity> continuation);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM dental_conditions WHERE toothNumber = :toothNumber AND patientId = :patientId")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.DentalConditionEntity>> getDentalConditionsByTooth(long patientId, int toothNumber);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 1)
    public abstract java.lang.Object insertDentalCondition(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.DentalConditionEntity dentalCondition, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Update
    public abstract java.lang.Object updateDentalCondition(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.DentalConditionEntity dentalCondition, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Delete
    public abstract java.lang.Object deleteDentalCondition(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.DentalConditionEntity dentalCondition, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "DELETE FROM dental_conditions WHERE id = :conditionId")
    public abstract java.lang.Object deleteDentalConditionById(long conditionId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}