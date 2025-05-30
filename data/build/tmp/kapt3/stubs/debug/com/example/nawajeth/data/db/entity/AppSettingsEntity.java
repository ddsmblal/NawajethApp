package com.example.nawajeth.data.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@androidx.room.Entity(tableName = "app_settings")
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b$\b\u0087\b\u0018\u00002\u00020\u0001B_\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000f\u001a\u00020\t\u00a2\u0006\u0002\u0010\u0010J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\tH\u00c6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00c6\u0003J\t\u0010#\u001a\u00020\u0005H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001bJ\u000b\u0010&\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\'\u001a\u00020\fH\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003Jz\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000f\u001a\u00020\tH\u00c6\u0001\u00a2\u0006\u0002\u0010+J\u0013\u0010,\u001a\u00020\f2\b\u0010-\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010.\u001a\u00020\u0003H\u00d6\u0001J\t\u0010/\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0015\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012R\u0011\u0010\u000f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f\u00a8\u00060"}, d2 = {"Lcom/example/nawajeth/data/db/entity/AppSettingsEntity;", "", "id", "", "language", "", "theme", "backupFrequency", "lastBackupDate", "", "backupLocation", "isActivated", "", "activationCode", "deviceId", "updatedAt", "(ILjava/lang/String;Ljava/lang/String;ILjava/lang/Long;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;J)V", "getActivationCode", "()Ljava/lang/String;", "getBackupFrequency", "()I", "getBackupLocation", "getDeviceId", "getId", "()Z", "getLanguage", "getLastBackupDate", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTheme", "getUpdatedAt", "()J", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(ILjava/lang/String;Ljava/lang/String;ILjava/lang/Long;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;J)Lcom/example/nawajeth/data/db/entity/AppSettingsEntity;", "equals", "other", "hashCode", "toString", "data_debug"})
public final class AppSettingsEntity {
    @androidx.room.PrimaryKey
    private final int id = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String language = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String theme = null;
    private final int backupFrequency = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long lastBackupDate = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String backupLocation = null;
    private final boolean isActivated = false;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String activationCode = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String deviceId = null;
    private final long updatedAt = 0L;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.nawajeth.data.db.entity.AppSettingsEntity copy(int id, @org.jetbrains.annotations.NotNull
    java.lang.String language, @org.jetbrains.annotations.NotNull
    java.lang.String theme, int backupFrequency, @org.jetbrains.annotations.Nullable
    java.lang.Long lastBackupDate, @org.jetbrains.annotations.Nullable
    java.lang.String backupLocation, boolean isActivated, @org.jetbrains.annotations.Nullable
    java.lang.String activationCode, @org.jetbrains.annotations.Nullable
    java.lang.String deviceId, long updatedAt) {
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
    
    public AppSettingsEntity(int id, @org.jetbrains.annotations.NotNull
    java.lang.String language, @org.jetbrains.annotations.NotNull
    java.lang.String theme, int backupFrequency, @org.jetbrains.annotations.Nullable
    java.lang.Long lastBackupDate, @org.jetbrains.annotations.Nullable
    java.lang.String backupLocation, boolean isActivated, @org.jetbrains.annotations.Nullable
    java.lang.String activationCode, @org.jetbrains.annotations.Nullable
    java.lang.String deviceId, long updatedAt) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLanguage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTheme() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int getBackupFrequency() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getLastBackupDate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBackupLocation() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    public final boolean isActivated() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getActivationCode() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDeviceId() {
        return null;
    }
    
    public final long component10() {
        return 0L;
    }
    
    public final long getUpdatedAt() {
        return 0L;
    }
}