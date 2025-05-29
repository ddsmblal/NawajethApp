package com.example.nawajeth.features.dentalworks.domain.repository;

import com.example.nawajeth.features.dentalworks.domain.model.DentalWork;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH&J!\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J$\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H&J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\u0015\u001a\u00020\u0003H&J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0018\u001a\u00020\u0003H&J\u0019\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001c"}, d2 = {"Lcom/example/nawajeth/features/dentalworks/domain/repository/DentalWorkRepository;", "", "addDentalWork", "", "dentalWork", "error/NonExistentClass", "(Lerror/NonExistentClass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDentalWork", "", "dentalWorkId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllDentalWorks", "Lkotlinx/coroutines/flow/Flow;", "", "getCompletedWorkCount", "", "startDate", "endDate", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCompletedWorks", "getDentalWorkById", "id", "getDentalWorkPaidForPatient", "", "patientId", "getDentalWorkTotalForPatient", "getDentalWorksByPatientId", "updateDentalWork", "dentalworks_debug"})
public abstract interface DentalWorkRepository {
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<DentalWork>> getAllDentalWorks();
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<DentalWork>> getDentalWorksByPatientId(long patientId);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<DentalWork> getDentalWorkById(long id);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object addDentalWork(@org.jetbrains.annotations.NotNull
    DentalWork dentalWork, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateDentalWork(@org.jetbrains.annotations.NotNull
    DentalWork dentalWork, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteDentalWork(long dentalWorkId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCompletedWorkCount(long startDate, long endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<DentalWork>> getCompletedWorks(long startDate, long endDate);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getDentalWorkTotalForPatient(long patientId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getDentalWorkPaidForPatient(long patientId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> continuation);
}