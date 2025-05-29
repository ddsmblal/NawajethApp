package com.example.nawajeth.features.settings.presentation.screens;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ContentCopy;
import androidx.compose.material.icons.filled.LockOpen;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import com.example.nawajeth.features.settings.presentation.viewmodel.ActivationViewModel;
import java.text.SimpleDateFormat;
import java.util.*;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u001a<\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0003H\u0007\u001a@\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a \u0010\r\u001a\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007\u001a\u001f\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007\u00a2\u0006\u0002\u0010\u0016\u001a\u001e\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u00a8\u0006\u0019"}, d2 = {"ActivationCodeSection", "", "activationCode", "", "onActivationCodeChanged", "Lkotlin/Function1;", "onActivateClick", "Lkotlin/Function0;", "error", "ActivationContent", "uiState", "Lcom/example/nawajeth/features/settings/presentation/viewmodel/ActivationUiState;", "onCopyDeviceId", "ActivationScreen", "onNavigateBack", "viewModel", "Lcom/example/nawajeth/features/settings/presentation/viewmodel/ActivationViewModel;", "ActivationStatusCard", "isActivated", "", "activationDate", "", "(ZLjava/lang/Long;)V", "DeviceIdSection", "deviceId", "settings_debug"})
public final class ActivationScreenKt {
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void ActivationScreen(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.settings.presentation.viewmodel.ActivationViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ActivationContent(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.settings.presentation.viewmodel.ActivationUiState uiState, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onActivationCodeChanged, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onActivateClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onCopyDeviceId) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ActivationStatusCard(boolean isActivated, @org.jetbrains.annotations.Nullable
    java.lang.Long activationDate) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void DeviceIdSection(@org.jetbrains.annotations.NotNull
    java.lang.String deviceId, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onCopyDeviceId) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ActivationCodeSection(@org.jetbrains.annotations.NotNull
    java.lang.String activationCode, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onActivationCodeChanged, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onActivateClick, @org.jetbrains.annotations.Nullable
    java.lang.String error) {
    }
}