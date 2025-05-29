package com.example.nawajeth.features.dentalchart.domain.repository;

import com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol;
import com.example.nawajeth.features.dentalchart.domain.model.ToothCondition;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00150\u0014H&J\u0018\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00142\u0006\u0010\u0017\u001a\u00020\u0003H&J\u0018\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00142\u0006\u0010\u0017\u001a\u00020\u0003H&J\u001c\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00150\u00142\u0006\u0010\r\u001a\u00020\u0003H&J\u0019\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u001b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001c"}, d2 = {"Lcom/example/nawajeth/features/dentalchart/domain/repository/DentalChartRepository;", "", "addSymbol", "", "symbol", "Lcom/example/nawajeth/features/dentalchart/domain/model/DentalSymbol;", "(Lcom/example/nawajeth/features/dentalchart/domain/model/DentalSymbol;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addToothCondition", "condition", "Lcom/example/nawajeth/features/dentalchart/domain/model/ToothCondition;", "(Lcom/example/nawajeth/features/dentalchart/domain/model/ToothCondition;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllToothConditionsForPatient", "", "patientId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSymbol", "symbolId", "deleteToothCondition", "conditionId", "getAllSymbols", "Lkotlinx/coroutines/flow/Flow;", "", "getSymbolById", "id", "getToothConditionById", "getToothConditionsByPatientId", "updateSymbol", "updateToothCondition", "dentalchart_debug"})
public abstract interface DentalChartRepository {
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol>> getAllSymbols();
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol> getSymbolById(long id);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object addSymbol(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol symbol, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateSymbol(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol symbol, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteSymbol(long symbolId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.nawajeth.features.dentalchart.domain.model.ToothCondition>> getToothConditionsByPatientId(long patientId);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.example.nawajeth.features.dentalchart.domain.model.ToothCondition> getToothConditionById(long id);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object addToothCondition(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.dentalchart.domain.model.ToothCondition condition, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateToothCondition(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.dentalchart.domain.model.ToothCondition condition, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteToothCondition(long conditionId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllToothConditionsForPatient(long patientId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}