package com.example.nawajeth.features.patients.data.repository;

import com.example.nawajeth.features.patients.data.mapper.toEntity;
import com.example.nawajeth.features.patients.data.mapper.toModel;
import com.example.nawajeth.features.patients.domain.model.Patient;
import com.example.nawajeth.features.patients.domain.repository.PatientRepository;
import com.example.nawajeth.data.db.dao.PatientDao;
import com.example.nawajeth.data.db.dao.DentalWorkDao;
import com.example.nawajeth.data.db.dao.PaymentDao;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00120\u0011H\u0016J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00112\u0006\u0010\u0014\u001a\u00020\tH\u0016J\u0011\u0010\u0015\u001a\u00020\u0016H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\tH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0019\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\tH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00120\u00112\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0019\u0010\u001e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u0003H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bR\u0010\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\u0005\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001f"}, d2 = {"Lcom/example/nawajeth/features/patients/data/repository/PatientRepositoryImpl;", "Lcom/example/nawajeth/features/patients/domain/repository/PatientRepository;", "patientDao", "error/NonExistentClass", "dentalWorkDao", "paymentDao", "(Lerror/NonExistentClass;Lerror/NonExistentClass;Lerror/NonExistentClass;)V", "Lerror/NonExistentClass;", "addPatient", "", "patient", "(Lerror/NonExistentClass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePatient", "", "patientId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPatients", "Lkotlinx/coroutines/flow/Flow;", "", "getPatientById", "id", "getPatientCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasRelatedDentalWorks", "", "hasRelatedPayments", "searchPatients", "query", "", "updatePatient", "patients_debug"})
public final class PatientRepositoryImpl implements com.example.nawajeth.features.patients.domain.repository.PatientRepository {
    private final PatientDao patientDao = null;
    private final DentalWorkDao dentalWorkDao = null;
    private final PaymentDao paymentDao = null;
    
    @javax.inject.Inject
    public PatientRepositoryImpl(@org.jetbrains.annotations.NotNull
    PatientDao patientDao, @org.jetbrains.annotations.NotNull
    DentalWorkDao dentalWorkDao, @org.jetbrains.annotations.NotNull
    PaymentDao paymentDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<Patient>> getAllPatients() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<Patient>> searchPatients(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<Patient> getPatientById(long id) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getPatientCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object addPatient(@org.jetbrains.annotations.NotNull
    Patient patient, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object updatePatient(@org.jetbrains.annotations.NotNull
    Patient patient, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object deletePatient(long patientId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object hasRelatedDentalWorks(long patientId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object hasRelatedPayments(long patientId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
}