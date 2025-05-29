package com.example.nawajeth.features.dentalchart.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol;
import com.example.nawajeth.features.dentalchart.domain.model.ToothCondition;
import com.example.nawajeth.features.dentalchart.domain.repository.DentalChartRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001d\b\u0087\b\u0018\u00002\u00020\u0001B]\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H\u00c6\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0019J\u0010\u0010$\u001a\u0004\u0018\u00010\rH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001cJ\u000b\u0010%\u001a\u0004\u0018\u00010\u000fH\u00c6\u0003Jf\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00c6\u0001\u00a2\u0006\u0002\u0010\'J\u0013\u0010(\u001a\u00020\u00032\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010*\u001a\u00020\rH\u00d6\u0001J\t\u0010+\u001a\u00020\u000fH\u00d6\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012\u00a8\u0006,"}, d2 = {"Lcom/example/nawajeth/features/dentalchart/presentation/viewmodel/DentalChartUiState;", "", "isLoading", "", "patientId", "", "availableSymbols", "", "Lcom/example/nawajeth/features/dentalchart/domain/model/DentalSymbol;", "toothConditions", "Lcom/example/nawajeth/features/dentalchart/domain/model/ToothCondition;", "selectedSymbolId", "selectedToothNumber", "", "error", "", "(ZJLjava/util/List;Ljava/util/List;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;)V", "getAvailableSymbols", "()Ljava/util/List;", "getError", "()Ljava/lang/String;", "()Z", "getPatientId", "()J", "getSelectedSymbolId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getSelectedToothNumber", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getToothConditions", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(ZJLjava/util/List;Ljava/util/List;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;)Lcom/example/nawajeth/features/dentalchart/presentation/viewmodel/DentalChartUiState;", "equals", "other", "hashCode", "toString", "dentalchart_debug"})
public final class DentalChartUiState {
    private final boolean isLoading = false;
    private final long patientId = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol> availableSymbols = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.nawajeth.features.dentalchart.domain.model.ToothCondition> toothConditions = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long selectedSymbolId = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer selectedToothNumber = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String error = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.nawajeth.features.dentalchart.presentation.viewmodel.DentalChartUiState copy(boolean isLoading, long patientId, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol> availableSymbols, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.nawajeth.features.dentalchart.domain.model.ToothCondition> toothConditions, @org.jetbrains.annotations.Nullable
    java.lang.Long selectedSymbolId, @org.jetbrains.annotations.Nullable
    java.lang.Integer selectedToothNumber, @org.jetbrains.annotations.Nullable
    java.lang.String error) {
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
    
    public DentalChartUiState() {
        super();
    }
    
    public DentalChartUiState(boolean isLoading, long patientId, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol> availableSymbols, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.nawajeth.features.dentalchart.domain.model.ToothCondition> toothConditions, @org.jetbrains.annotations.Nullable
    java.lang.Long selectedSymbolId, @org.jetbrains.annotations.Nullable
    java.lang.Integer selectedToothNumber, @org.jetbrains.annotations.Nullable
    java.lang.String error) {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final long getPatientId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol> getAvailableSymbols() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.nawajeth.features.dentalchart.domain.model.ToothCondition> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.nawajeth.features.dentalchart.domain.model.ToothCondition> getToothConditions() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getSelectedSymbolId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getSelectedToothNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getError() {
        return null;
    }
}