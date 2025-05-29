package com.example.nawajeth.features.dentalworks.data.repository;

import com.example.nawajeth.data.db.dao.DentalWorkDao;
import com.example.nawajeth.data.db.entity.DentalWorkEntity;
import com.example.nawajeth.features.dentalworks.domain.model.DentalWork;
import com.example.nawajeth.features.dentalworks.domain.repository.DentalWorkRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00100\u000fH\u0016J!\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J$\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00100\u000f2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007H\u0016J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f2\u0006\u0010\u0018\u001a\u00020\u0007H\u0016J\u0019\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0007H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0007H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00100\u000f2\u0006\u0010\u001b\u001a\u00020\u0007H\u0016J\u0019\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0003H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0011\u0010\u001f\u001a\u00020\u0003*\u00020\u0003H\u0002\u00a2\u0006\u0002\u0010 J\u0011\u0010!\u001a\u00020\u0003*\u00020\u0003H\u0002\u00a2\u0006\u0002\u0010 R\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lcom/example/nawajeth/features/dentalworks/data/repository/DentalWorkRepositoryImpl;", "Lcom/example/nawajeth/features/dentalworks/domain/repository/DentalWorkRepository;", "dentalWorkDao", "error/NonExistentClass", "(Lerror/NonExistentClass;)V", "Lerror/NonExistentClass;", "addDentalWork", "", "dentalWork", "(Lerror/NonExistentClass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDentalWork", "", "dentalWorkId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllDentalWorks", "Lkotlinx/coroutines/flow/Flow;", "", "getCompletedWorkCount", "", "startDate", "endDate", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCompletedWorks", "getDentalWorkById", "id", "getDentalWorkPaidForPatient", "", "patientId", "getDentalWorkTotalForPatient", "getDentalWorksByPatientId", "updateDentalWork", "toDomain", "(Lerror/NonExistentClass;)Lerror/NonExistentClass;", "toEntity", "dentalworks_debug"})
public final class DentalWorkRepositoryImpl implements com.example.nawajeth.features.dentalworks.domain.repository.DentalWorkRepository {
    private final DentalWorkDao dentalWorkDao = null;
    
    @javax.inject.Inject
    public DentalWorkRepositoryImpl(@org.jetbrains.annotations.NotNull
    DentalWorkDao dentalWorkDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<DentalWork>> getAllDentalWorks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<DentalWork>> getDentalWorksByPatientId(long patientId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<DentalWork> getDentalWorkById(long id) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object addDentalWork(@org.jetbrains.annotations.NotNull
    DentalWork dentalWork, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object updateDentalWork(@org.jetbrains.annotations.NotNull
    DentalWork dentalWork, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object deleteDentalWork(long dentalWorkId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getCompletedWorkCount(long startDate, long endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<DentalWork>> getCompletedWorks(long startDate, long endDate) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getDentalWorkTotalForPatient(long patientId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getDentalWorkPaidForPatient(long patientId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> continuation) {
        return null;
    }
    
    private final DentalWork toDomain(error.NonExistentClass $this$toDomain) {
        return null;
    }
    
    private final DentalWorkEntity toEntity(error.NonExistentClass $this$toEntity) {
        return null;
    }
}