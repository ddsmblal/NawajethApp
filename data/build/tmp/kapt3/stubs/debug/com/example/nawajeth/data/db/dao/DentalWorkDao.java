package com.example.nawajeth.data.db.dao;

import androidx.room.*;
import com.example.nawajeth.data.db.entity.DentalWorkEntity;
import kotlinx.coroutines.flow.Flow;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001b\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00110\u00102\u0006\u0010\u000e\u001a\u00020\tH\'J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00110\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\'J$\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00110\u00102\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\rH\'J\u001b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u000e\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001b"}, d2 = {"Lcom/example/nawajeth/data/db/dao/DentalWorkDao;", "", "deleteDentalWork", "", "dentalWork", "Lcom/example/nawajeth/data/db/entity/DentalWorkEntity;", "(Lcom/example/nawajeth/data/db/entity/DentalWorkEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDentalWorkById", "workId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDentalWorkById", "getDentalWorkCountForPatient", "", "patientId", "getDentalWorksByPatientId", "Lkotlinx/coroutines/flow/Flow;", "", "getDentalWorksByStatus", "status", "", "getDentalWorksByTooth", "toothNumber", "getTotalRemainingAmountForPatient", "", "insertDentalWork", "updateDentalWork", "data_debug"})
public abstract interface DentalWorkDao {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM dental_works WHERE patientId = :patientId ORDER BY startDate DESC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.DentalWorkEntity>> getDentalWorksByPatientId(long patientId);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT * FROM dental_works WHERE id = :workId")
    public abstract java.lang.Object getDentalWorkById(long workId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.nawajeth.data.db.entity.DentalWorkEntity> continuation);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM dental_works WHERE status = :status ORDER BY startDate DESC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.DentalWorkEntity>> getDentalWorksByStatus(@org.jetbrains.annotations.NotNull
    java.lang.String status);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM dental_works WHERE toothNumber = :toothNumber AND patientId = :patientId")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.DentalWorkEntity>> getDentalWorksByTooth(long patientId, int toothNumber);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT SUM(remainingAmount) FROM dental_works WHERE patientId = :patientId")
    public abstract java.lang.Object getTotalRemainingAmountForPatient(long patientId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 1)
    public abstract java.lang.Object insertDentalWork(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.DentalWorkEntity dentalWork, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Update
    public abstract java.lang.Object updateDentalWork(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.DentalWorkEntity dentalWork, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Delete
    public abstract java.lang.Object deleteDentalWork(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.DentalWorkEntity dentalWork, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "DELETE FROM dental_works WHERE id = :workId")
    public abstract java.lang.Object deleteDentalWorkById(long workId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT COUNT(*) FROM dental_works WHERE patientId = :patientId")
    public abstract java.lang.Object getDentalWorkCountForPatient(long patientId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation);
}