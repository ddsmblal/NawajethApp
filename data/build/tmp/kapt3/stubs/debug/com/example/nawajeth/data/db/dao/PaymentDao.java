package com.example.nawajeth.data.db.dao;

import androidx.room.*;
import com.example.nawajeth.data.db.entity.PaymentEntity;
import kotlinx.coroutines.flow.Flow;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001b\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\r2\u0006\u0010\u000f\u001a\u00020\tH\'J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\r2\u0006\u0010\u0011\u001a\u00020\tH\'J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\r2\u0006\u0010\u0013\u001a\u00020\u0014H\'J\u001b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u000f\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0011\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/example/nawajeth/data/db/dao/PaymentDao;", "", "deletePayment", "", "payment", "Lcom/example/nawajeth/data/db/entity/PaymentEntity;", "(Lcom/example/nawajeth/data/db/entity/PaymentEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePaymentById", "paymentId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPaymentById", "getPaymentsByDentalWorkId", "Lkotlinx/coroutines/flow/Flow;", "", "dentalWorkId", "getPaymentsByPatientId", "patientId", "getRecentPayments", "limit", "", "getTotalPaymentsForDentalWork", "", "getTotalPaymentsForPatient", "insertPayment", "updatePayment", "data_debug"})
public abstract interface PaymentDao {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM payments WHERE patientId = :patientId ORDER BY paymentDate DESC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.PaymentEntity>> getPaymentsByPatientId(long patientId);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM payments WHERE dentalWorkId = :dentalWorkId ORDER BY paymentDate DESC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.PaymentEntity>> getPaymentsByDentalWorkId(long dentalWorkId);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT * FROM payments WHERE id = :paymentId")
    public abstract java.lang.Object getPaymentById(long paymentId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.nawajeth.data.db.entity.PaymentEntity> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT SUM(amount) FROM payments WHERE patientId = :patientId")
    public abstract java.lang.Object getTotalPaymentsForPatient(long patientId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT SUM(amount) FROM payments WHERE dentalWorkId = :dentalWorkId")
    public abstract java.lang.Object getTotalPaymentsForDentalWork(long dentalWorkId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> continuation);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM payments ORDER BY paymentDate DESC LIMIT :limit")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.data.db.entity.PaymentEntity>> getRecentPayments(int limit);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 1)
    public abstract java.lang.Object insertPayment(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.PaymentEntity payment, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Update
    public abstract java.lang.Object updatePayment(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.PaymentEntity payment, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Delete
    public abstract java.lang.Object deletePayment(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.PaymentEntity payment, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "DELETE FROM payments WHERE id = :paymentId")
    public abstract java.lang.Object deletePaymentById(long paymentId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}