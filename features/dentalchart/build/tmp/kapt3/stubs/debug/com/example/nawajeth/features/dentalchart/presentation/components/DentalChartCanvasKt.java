package com.example.nawajeth.features.dentalchart.presentation.components;

import androidx.compose.foundation.layout.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.drawscope.Stroke;
import com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol;
import com.example.nawajeth.features.dentalchart.domain.model.ToothCondition;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\u001ae\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\fH\u0007\u00a2\u0006\u0002\u0010\u000f\u001a7\u0010\u0010\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0019\u001a\u0014\u0010\u001a\u001a\u00020\u0001*\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u001a\u0014\u0010\u001c\u001a\u00020\u0001*\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u001a9\u0010\u001d\u001a\u00020\u0001*\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b!\u0010\"\u001a9\u0010#\u001a\u00020\u0001*\u00020\u001b2\u0006\u0010$\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&H\u0002\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\'\u0010(\u001a\u0014\u0010)\u001a\u00020\u0001*\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u0082\u0002\u000b\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b\u0019\u00a8\u0006*"}, d2 = {"DentalChartCanvas", "", "modifier", "Landroidx/compose/ui/Modifier;", "toothConditions", "", "Lcom/example/nawajeth/features/dentalchart/domain/model/ToothCondition;", "availableSymbols", "Lcom/example/nawajeth/features/dentalchart/domain/model/DentalSymbol;", "selectedSymbolId", "", "onToothClick", "Lkotlin/Function1;", "", "onAddCondition", "(Landroidx/compose/ui/Modifier;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getToothIndexAtPosition", "position", "Landroidx/compose/ui/geometry/Offset;", "scale", "", "offset", "size", "Landroidx/compose/ui/geometry/Size;", "getToothIndexAtPosition-lq-_M4o", "(JFJJ)Ljava/lang/Integer;", "drawDentalChart", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "drawLowerJaw", "drawSymbol", "symbol", "color", "Landroidx/compose/ui/graphics/Color;", "drawSymbol-EthTpfg", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Lcom/example/nawajeth/features/dentalchart/domain/model/DentalSymbol;JJF)V", "drawTooth", "toothNumber", "isUpper", "", "drawTooth-QNmb5TA", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;IJJZ)V", "drawUpperJaw", "dentalchart_debug"})
public final class DentalChartCanvasKt {
    
    @androidx.compose.runtime.Composable
    public static final void DentalChartCanvas(@org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.nawajeth.features.dentalchart.domain.model.ToothCondition> toothConditions, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol> availableSymbols, @org.jetbrains.annotations.Nullable
    java.lang.Long selectedSymbolId, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onToothClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.nawajeth.features.dentalchart.domain.model.ToothCondition, kotlin.Unit> onAddCondition) {
    }
    
    private static final void drawDentalChart(androidx.compose.ui.graphics.drawscope.DrawScope $this$drawDentalChart, float scale) {
    }
    
    private static final void drawUpperJaw(androidx.compose.ui.graphics.drawscope.DrawScope $this$drawUpperJaw, float scale) {
    }
    
    private static final void drawLowerJaw(androidx.compose.ui.graphics.drawscope.DrawScope $this$drawLowerJaw, float scale) {
    }
}