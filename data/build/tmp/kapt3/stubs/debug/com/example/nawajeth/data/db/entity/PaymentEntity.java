package com.example.nawajeth.data.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@androidx.room.Entity(tableName = "payments", foreignKeys = {@androidx.room.ForeignKey(entity = com.example.nawajeth.data.db.entity.PatientEntity.class, childColumns = {"patientId"}, onDelete = 5, parentColumns = {"id"}), @androidx.room.ForeignKey(entity = com.example.nawajeth.data.db.entity.DentalWorkEntity.class, childColumns = {"dentalWorkId"}, onDelete = 3, parentColumns = {"id"})}, indices = {@androidx.room.Index(value = {"patientId"}), @androidx.room.Index(value = {"dentalWorkId"})})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BI\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\rJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013J\t\u0010\u001e\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\nH\u00c6\u0003J\t\u0010!\u001a\u00020\nH\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J`\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010(\u001a\u00020)H\u00d6\u0001J\t\u0010*\u001a\u00020\nH\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017\u00a8\u0006+"}, d2 = {"Lcom/example/nawajeth/data/db/entity/PaymentEntity;", "", "id", "", "patientId", "dentalWorkId", "amount", "", "paymentDate", "paymentMethod", "", "notes", "createdAt", "(JJLjava/lang/Long;DJLjava/lang/String;Ljava/lang/String;J)V", "getAmount", "()D", "getCreatedAt", "()J", "getDentalWorkId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getId", "getNotes", "()Ljava/lang/String;", "getPatientId", "getPaymentDate", "getPaymentMethod", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(JJLjava/lang/Long;DJLjava/lang/String;Ljava/lang/String;J)Lcom/example/nawajeth/data/db/entity/PaymentEntity;", "equals", "", "other", "hashCode", "", "toString", "data_debug"})
public final class PaymentEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    private final long patientId = 0L;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long dentalWorkId = null;
    private final double amount = 0.0;
    private final long paymentDate = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String paymentMethod = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String notes = null;
    private final long createdAt = 0L;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.nawajeth.data.db.entity.PaymentEntity copy(long id, long patientId, @org.jetbrains.annotations.Nullable
    java.lang.Long dentalWorkId, double amount, long paymentDate, @org.jetbrains.annotations.NotNull
    java.lang.String paymentMethod, @org.jetbrains.annotations.NotNull
    java.lang.String notes, long createdAt) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public PaymentEntity(long id, long patientId, @org.jetbrains.annotations.Nullable
    java.lang.Long dentalWorkId, double amount, long paymentDate, @org.jetbrains.annotations.NotNull
    java.lang.String paymentMethod, @org.jetbrains.annotations.NotNull
    java.lang.String notes, long createdAt) {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final long getPatientId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getDentalWorkId() {
        return null;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    public final double getAmount() {
        return 0.0;
    }
    
    public final long component5() {
        return 0L;
    }
    
    public final long getPaymentDate() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPaymentMethod() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getNotes() {
        return null;
    }
    
    public final long component8() {
        return 0L;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
}