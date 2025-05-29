package com.example.nawajeth.data.db.dao;

import androidx.room.*;
import com.example.nawajeth.data.db.entity.AppSettingsEntity;
import kotlinx.coroutines.flow.Flow;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\'J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u001b\u0010\t\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0019\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u0019\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0019\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001aH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bJ\u0019\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001e"}, d2 = {"Lcom/example/nawajeth/data/db/dao/AppSettingsDao;", "", "getAppSettings", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/nawajeth/data/db/entity/AppSettingsEntity;", "insertAppSettings", "", "appSettings", "(Lcom/example/nawajeth/data/db/entity/AppSettingsEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateActivationCode", "code", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateActivationStatus", "isActivated", "", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAppSettings", "updateBackupFrequency", "frequency", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLanguage", "language", "updateLastBackupDate", "date", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTheme", "theme", "data_debug"})
public abstract interface AppSettingsDao {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM app_settings WHERE id = 1")
    public abstract kotlinx.coroutines.flow.Flow<com.example.nawajeth.data.db.entity.AppSettingsEntity> getAppSettings();
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 1)
    public abstract java.lang.Object insertAppSettings(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.AppSettingsEntity appSettings, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Update
    public abstract java.lang.Object updateAppSettings(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.data.db.entity.AppSettingsEntity appSettings, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "UPDATE app_settings SET language = :language WHERE id = 1")
    public abstract java.lang.Object updateLanguage(@org.jetbrains.annotations.NotNull
    java.lang.String language, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "UPDATE app_settings SET theme = :theme WHERE id = 1")
    public abstract java.lang.Object updateTheme(@org.jetbrains.annotations.NotNull
    java.lang.String theme, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "UPDATE app_settings SET backupFrequency = :frequency WHERE id = 1")
    public abstract java.lang.Object updateBackupFrequency(int frequency, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "UPDATE app_settings SET lastBackupDate = :date WHERE id = 1")
    public abstract java.lang.Object updateLastBackupDate(long date, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "UPDATE app_settings SET isActivated = :isActivated WHERE id = 1")
    public abstract java.lang.Object updateActivationStatus(boolean isActivated, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "UPDATE app_settings SET activationCode = :code WHERE id = 1")
    public abstract java.lang.Object updateActivationCode(@org.jetbrains.annotations.Nullable
    java.lang.String code, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}