package com.example.nawajeth.features.dentalchart.presentation.screens;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.Save;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.text.style.TextAlign;
import com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol;
import com.example.nawajeth.features.dentalchart.presentation.viewmodel.DentalChartViewModel;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a&\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001aX\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a,\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\u00122\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u00a8\u0006\u0019"}, d2 = {"DentalChartScreen", "", "viewModel", "Lcom/example/nawajeth/features/dentalchart/presentation/viewmodel/DentalChartViewModel;", "patientId", "", "onNavigateBack", "Lkotlin/Function0;", "SymbolItem", "symbol", "Lcom/example/nawajeth/features/dentalchart/domain/model/DentalSymbol;", "isSelected", "", "onClick", "ToothDetailsDialog", "toothNumber", "", "conditions", "", "selectedSymbol", "onAddCondition", "onRemoveCondition", "Lkotlin/Function1;", "onDismiss", "ToothItem", "dentalchart_debug"})
public final class DentalChartScreenKt {
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void DentalChartScreen(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.dentalchart.presentation.viewmodel.DentalChartViewModel viewModel, long patientId, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void SymbolItem(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol symbol, boolean isSelected, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ToothItem(int toothNumber, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol> conditions, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ToothDetailsDialog(int toothNumber, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol> conditions, @org.jetbrains.annotations.Nullable
    com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol selectedSymbol, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddCondition, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onRemoveCondition, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
}