package com.example.nawajeth.data.db.dao;

import androidx.room.*;
import com.example.nawajeth.data.db.entity.PatientEntity;
import kotlinx.coroutines.flow.Flow;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0011\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0014\u001a\u00020\u0010H\'J\u0019\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0017\u001a\u00020\u0018H\'J\u0019\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/example/nawajeth/data/db/dao/PatientDao;", "", "deletePatient", "", "patient", "Lcom/example/nawajeth/data/db/entity/PatientEntity;", "(Lcom/example/nawajeth/data/db/entity/PatientEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePatientById", "patientId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPatients", "Lkotlinx/coroutines/flow/Flow;", "", "getPatientById", "getPatientCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPatientsByDueAmount", "getRecentPatients", "limit", "insertPatient", "searchPatients", "searchQuery", "", "updatePatient", "data_debug"})
public abstract interface PatientDao {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM patients ORDER BY name ASC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.PatientEntity>> getAllPatients();
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT * FROM patients WHERE id = :patientId")
    public abstract java.lang.Object getPatientById(long patientId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.nawajeth.data.db.entity.PatientEntity> continuation);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM patients WHERE name LIKE \'%\' || :searchQuery || \'%\' OR phone LIKE \'%\' || :searchQuery || \'%\'")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.PatientEntity>> searchPatients(@org.jetbrains.annotations.NotNull
    java.lang.String searchQuery);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM patients ORDER BY lastVisitDate DESC LIMIT :limit")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.PatientEntity>> getRecentPatients(int limit);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM patients ORDER BY totalDue DESC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.PatientEntity>> getPatientsByDueAmount();
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT COUNT(*) FROM patients")
    public abstract java.lang.Object getPatientCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 1)
    public abstract java.lang.Object insertPatient(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.PatientEntity patient, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Update
    public abstract java.lang.Object updatePatient(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.PatientEntity patient, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Delete
    public abstract java.lang.Object deletePatient(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.PatientEntity patient, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "DELETE FROM patients WHERE id = :patientId")
    public abstract java.lang.Object deletePatientById(long patientId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}