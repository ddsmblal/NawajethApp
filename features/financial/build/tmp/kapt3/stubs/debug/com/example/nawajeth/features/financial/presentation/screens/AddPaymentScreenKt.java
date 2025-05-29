package com.example.nawajeth.features.financial.presentation.screens;

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
import com.example.nawajeth.features.financial.domain.model.DentalWork;
import com.example.nawajeth.features.financial.presentation.viewmodel.AddPaymentViewModel;
import java.text.SimpleDateFormat;
import java.util.*;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0007\u001a7\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007\u00a2\u0006\u0002\u0010\t\u001a,\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00052\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u000fH\u0007\u001a9\u0010\u0010\u001a\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u000fH\u0007\u00a2\u0006\u0002\u0010\u0015\u001a$\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\f2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000fH\u0007\u00a8\u0006\u0019"}, d2 = {"AddPaymentScreen", "", "viewModel", "error/NonExistentClass", "patientId", "", "dentalWorkId", "onNavigateBack", "Lkotlin/Function0;", "(Lerror/NonExistentClass;JLjava/lang/Long;Lkotlin/jvm/functions/Function0;)V", "DateSelector", "label", "", "date", "onDateSelected", "Lkotlin/Function1;", "DentalWorkSelector", "dentalWorks", "", "selectedDentalWorkId", "onDentalWorkSelected", "(Ljava/util/List;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "PaymentMethodSelector", "selectedMethod", "onMethodSelected", "financial_debug"})
public final class AddPaymentScreenKt {
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void AddPaymentScreen(@org.jetbrains.annotations.NotNull
    AddPaymentViewModel viewModel, long patientId, @org.jetbrains.annotations.Nullable
    java.lang.Long dentalWorkId, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void DentalWorkSelector(@org.jetbrains.annotations.NotNull
    java.util.List<? extends DentalWork> dentalWorks, @org.jetbrains.annotations.Nullable
    java.lang.Long selectedDentalWorkId, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onDentalWorkSelected) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void PaymentMethodSelector(@org.jetbrains.annotations.NotNull
    java.lang.String selectedMethod, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onMethodSelected) {
    }
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void DateSelector(@org.jetbrains.annotations.NotNull
    java.lang.String label, long date, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onDateSelected) {
    }
}