package com.example.nawajeth.features.settings.domain.repository;

import com.example.nawajeth.features.settings.domain.model.LicenseStatus;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ*\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\n\u001a\u00020\u0006H\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b\u000b\u0010\bJ\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rH&J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\rH&J\u0011\u0010\u000f\u001a\u00020\u0010H\u00a6@\u00f8\u0001\u0002\u00a2\u0006\u0002\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0006H\u00a6@\u00f8\u0001\u0002\u00a2\u0006\u0002\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0006H\u00a6@\u00f8\u0001\u0002\u00a2\u0006\u0002\u0010\u0011J\u0011\u0010\u0014\u001a\u00020\u0015H\u00a6@\u00f8\u0001\u0002\u00a2\u0006\u0002\u0010\u0011J\u0011\u0010\u0016\u001a\u00020\u0004H\u00a6@\u00f8\u0001\u0002\u00a2\u0006\u0002\u0010\u0011J*\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\n\u001a\u00020\u0006H\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b\u0018\u0010\bJ\u0019\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0006H\u00a6@\u00f8\u0001\u0002\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u0006H\u00a6@\u00f8\u0001\u0002\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u0004H\u00a6@\u00f8\u0001\u0002\u00a2\u0006\u0002\u0010 J\u0019\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u0010H\u00a6@\u00f8\u0001\u0002\u00a2\u0006\u0002\u0010#J\u0019\u0010$\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\u0006H\u00a6@\u00f8\u0001\u0002\u00a2\u0006\u0002\u0010\b\u0082\u0002\u000f\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b\u0019\u00a8\u0006%"}, d2 = {"Lcom/example/nawajeth/features/settings/domain/repository/SettingsRepository;", "", "activateApp", "Lkotlin/Result;", "", "activationCode", "", "activateApp-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createBackup", "path", "createBackup-gIAlu-s", "getAppLanguage", "Lkotlinx/coroutines/flow/Flow;", "getAppTheme", "getBackupFrequency", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBackupLocation", "getDeviceId", "getLicenseStatus", "Lcom/example/nawajeth/features/settings/domain/model/LicenseStatus;", "isAutoBackupEnabled", "restoreBackup", "restoreBackup-gIAlu-s", "setAppLanguage", "", "language", "setAppTheme", "theme", "setAutoBackupEnabled", "enabled", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setBackupFrequency", "days", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setBackupLocation", "settings_debug"})
public abstract interface SettingsRepository {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getLicenseStatus(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.nawajeth.features.settings.domain.model.LicenseStatus> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getDeviceId(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> continuation);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.lang.String> getAppTheme();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object setAppTheme(@org.jetbrains.annotations.NotNull
    java.lang.String theme, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.lang.String> getAppLanguage();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object setAppLanguage(@org.jetbrains.annotations.NotNull
    java.lang.String language, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object isAutoBackupEnabled(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object setAutoBackupEnabled(boolean enabled, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getBackupFrequency(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object setBackupFrequency(int days, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getBackupLocation(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object setBackupLocation(@org.jetbrains.annotations.NotNull
    java.lang.String path, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}