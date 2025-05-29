package com.example.nawajeth.features.dentalchart.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol;
import com.example.nawajeth.features.dentalchart.domain.model.ToothCondition;
import com.example.nawajeth.features.dentalchart.domain.repository.DentalChartRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\rJ\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0013J\u0015\u0010\u0016\u001a\u00020\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\u0002\u0010\u0018J\u000e\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u001d"}, d2 = {"Lcom/example/nawajeth/features/dentalchart/presentation/viewmodel/DentalChartViewModel;", "Landroidx/lifecycle/ViewModel;", "dentalChartRepository", "Lcom/example/nawajeth/features/dentalchart/domain/repository/DentalChartRepository;", "(Lcom/example/nawajeth/features/dentalchart/domain/repository/DentalChartRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/nawajeth/features/dentalchart/presentation/viewmodel/DentalChartUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addToothCondition", "", "condition", "Lcom/example/nawajeth/features/dentalchart/domain/model/ToothCondition;", "clearError", "deleteToothCondition", "conditionId", "", "loadPatientDentalChart", "patientId", "selectSymbol", "symbolId", "(Ljava/lang/Long;)V", "selectTooth", "toothNumber", "", "updateToothCondition", "dentalchart_debug"})
public final class DentalChartViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.nawajeth.features.dentalchart.domain.repository.DentalChartRepository dentalChartRepository = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.nawajeth.features.dentalchart.presentation.viewmodel.DentalChartUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.nawajeth.features.dentalchart.presentation.viewmodel.DentalChartUiState> uiState = null;
    
    @javax.inject.Inject
    public DentalChartViewModel(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.dentalchart.domain.repository.DentalChartRepository dentalChartRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.nawajeth.features.dentalchart.presentation.viewmodel.DentalChartUiState> getUiState() {
        return null;
    }
    
    public final void loadPatientDentalChart(long patientId) {
    }
    
    public final void selectSymbol(@org.jetbrains.annotations.Nullable
    java.lang.Long symbolId) {
    }
    
    public final void selectTooth(int toothNumber) {
    }
    
    public final void addToothCondition(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.dentalchart.domain.model.ToothCondition condition) {
    }
    
    public final void updateToothCondition(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.dentalchart.domain.model.ToothCondition condition) {
    }
    
    public final void deleteToothCondition(long conditionId) {
    }
    
    public final void clearError() {
    }
}