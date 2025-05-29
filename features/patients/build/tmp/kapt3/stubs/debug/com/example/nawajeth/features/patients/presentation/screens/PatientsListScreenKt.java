package com.example.nawajeth.features.patients.presentation.screens;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.style.TextOverflow;
import com.example.nawajeth.features.patients.domain.model.Patient;
import com.example.nawajeth.features.patients.presentation.components.PatientItem;
import com.example.nawajeth.features.patients.presentation.viewmodel.PatientsListViewModel;
import java.text.SimpleDateFormat;
import java.util.*;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u00a2\u0006\u0002\u0010\u0006\u001a7\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00032\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"PatientItem", "", "patient", "error/NonExistentClass", "onClick", "Lkotlin/Function0;", "(Lerror/NonExistentClass;Lkotlin/jvm/functions/Function0;)V", "PatientsListScreen", "viewModel", "onPatientClick", "Lkotlin/Function1;", "", "onAddPatientClick", "(Lerror/NonExistentClass;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "patients_debug"})
public final class PatientsListScreenKt {
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void PatientsListScreen(@org.jetbrains.annotations.NotNull
    PatientsListViewModel viewModel, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onPatientClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddPatientClick) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void PatientItem(@org.jetbrains.annotations.NotNull
    Patient patient, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}