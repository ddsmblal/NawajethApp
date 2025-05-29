package com.example.nawajeth.data.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@androidx.room.Entity(tableName = "dental_works", foreignKeys = {@androidx.room.ForeignKey(entity = com.example.nawajeth.data.db.entity.PatientEntity.class, childColumns = {"patientId"}, onDelete = 5, parentColumns = {"id"})}, indices = {@androidx.room.Index(value = {"patientId"})})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b,\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bs\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0011\u001a\u00020\t\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0013J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010*\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001bJ\t\u0010+\u001a\u00020\tH\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010%J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\tH\u00c6\u0003J\t\u00101\u001a\u00020\u000bH\u00c6\u0003J\t\u00102\u001a\u00020\u000bH\u00c6\u0003J\t\u00103\u001a\u00020\u000bH\u00c6\u0003J\t\u00104\u001a\u00020\tH\u00c6\u0003J\u0094\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u00106J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010:\u001a\u00020\u0006H\u00d6\u0001J\t\u0010;\u001a\u00020\tH\u00d6\u0001R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u0011\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0011\u0010\r\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0017\u00a8\u0006<"}, d2 = {"Lcom/example/nawajeth/data/db/entity/DentalWorkEntity;", "", "id", "", "patientId", "toothNumber", "", "workTypeId", "description", "", "cost", "", "paidAmount", "remainingAmount", "status", "startDate", "endDate", "notes", "createdAt", "(JJLjava/lang/Integer;JLjava/lang/String;DDDLjava/lang/String;JLjava/lang/Long;Ljava/lang/String;J)V", "getCost", "()D", "getCreatedAt", "()J", "getDescription", "()Ljava/lang/String;", "getEndDate", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getId", "getNotes", "getPaidAmount", "getPatientId", "getRemainingAmount", "getStartDate", "getStatus", "getToothNumber", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getWorkTypeId", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JJLjava/lang/Integer;JLjava/lang/String;DDDLjava/lang/String;JLjava/lang/Long;Ljava/lang/String;J)Lcom/example/nawajeth/data/db/entity/DentalWorkEntity;", "equals", "", "other", "hashCode", "toString", "data_debug"})
public final class DentalWorkEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    private final long patientId = 0L;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer toothNumber = null;
    private final long workTypeId = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String description = null;
    private final double cost = 0.0;
    private final double paidAmount = 0.0;
    private final double remainingAmount = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String status = null;
    private final long startDate = 0L;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long endDate = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String notes = null;
    private final long createdAt = 0L;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.nawajeth.data.db.entity.DentalWorkEntity copy(long id, long patientId, @org.jetbrains.annotations.Nullable
    java.lang.Integer toothNumber, long workTypeId, @org.jetbrains.annotations.NotNull
    java.lang.String description, double cost, double paidAmount, double remainingAmount, @org.jetbrains.annotations.NotNull
    java.lang.String status, long startDate, @org.jetbrains.annotations.Nullable
    java.lang.Long endDate, @org.jetbrains.annotations.NotNull
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
    
    public DentalWorkEntity(long id, long patientId, @org.jetbrains.annotations.Nullable
    java.lang.Integer toothNumber, long workTypeId, @org.jetbrains.annotations.NotNull
    java.lang.String description, double cost, double paidAmount, double remainingAmount, @org.jetbrains.annotations.NotNull
    java.lang.String status, long startDate, @org.jetbrains.annotations.Nullable
    java.lang.Long endDate, @org.jetbrains.annotations.NotNull
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
    public final java.lang.Integer component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getToothNumber() {
        return null;
    }
    
    public final long component4() {
        return 0L;
    }
    
    public final long getWorkTypeId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDescription() {
        return null;
    }
    
    public final double component6() {
        return 0.0;
    }
    
    public final double getCost() {
        return 0.0;
    }
    
    public final double component7() {
        return 0.0;
    }
    
    public final double getPaidAmount() {
        return 0.0;
    }
    
    public final double component8() {
        return 0.0;
    }
    
    public final double getRemainingAmount() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getStatus() {
        return null;
    }
    
    public final long component10() {
        return 0L;
    }
    
    public final long getStartDate() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getEndDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getNotes() {
        return null;
    }
    
    public final long component13() {
        return 0L;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
}