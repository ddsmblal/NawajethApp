package com.example.nawajeth.features.dentalworks.presentation.screens;

import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.Save;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import com.example.nawajeth.features.dentalworks.domain.model.WorkType;
import com.example.nawajeth.features.dentalworks.presentation.viewmodel.AddEditDentalWorkViewModel;
import java.text.SimpleDateFormat;
import java.util.*;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\u001a7\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007\u00a2\u0006\u0002\u0010\t\u001a=\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007\u00a2\u0006\u0002\u0010\u0012\u001a$\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\f2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000fH\u0007\u001a9\u0010\u0016\u001a\u00020\u00012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u000fH\u0007\u00a2\u0006\u0002\u0010\u001b\u00a8\u0006\u001c"}, d2 = {"AddEditDentalWorkScreen", "", "viewModel", "error/NonExistentClass", "patientId", "", "dentalWorkId", "onNavigateBack", "Lkotlin/Function0;", "(Lerror/NonExistentClass;JLjava/lang/Long;Lkotlin/jvm/functions/Function0;)V", "DateSelector", "label", "", "date", "onDateSelected", "Lkotlin/Function1;", "isOptional", "", "(Ljava/lang/String;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Z)V", "StatusSelector", "selectedStatus", "onStatusSelected", "WorkTypeSelector", "workTypes", "", "selectedWorkTypeId", "onWorkTypeSelected", "(Ljava/util/List;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "dentalworks_debug"})
public final class AddEditDentalWorkScreenKt {
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void AddEditDentalWorkScreen(@org.jetbrains.annotations.NotNull
    AddEditDentalWorkViewModel viewModel, long patientId, @org.jetbrains.annotations.Nullable
    java.lang.Long dentalWorkId, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void WorkTypeSelector(@org.jetbrains.annotations.NotNull
    java.util.List<? extends WorkType> workTypes, @org.jetbrains.annotations.Nullable
    java.lang.Long selectedWorkTypeId, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onWorkTypeSelected) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void StatusSelector(@org.jetbrains.annotations.NotNull
    java.lang.String selectedStatus, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onStatusSelected) {
    }
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void DateSelector(@org.jetbrains.annotations.NotNull
    java.lang.String label, @org.jetbrains.annotations.Nullable
    java.lang.Long date, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onDateSelected, boolean isOptional) {
    }
}