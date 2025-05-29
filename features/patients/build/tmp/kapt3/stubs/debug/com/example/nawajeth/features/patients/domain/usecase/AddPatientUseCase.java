package com.example.nawajeth.features.patients.domain.usecase;

import com.example.nawajeth.features.patients.domain.model.Patient;
import com.example.nawajeth.features.patients.domain.repository.PatientRepository;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J*\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086B\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lcom/example/nawajeth/features/patients/domain/usecase/AddPatientUseCase;", "", "patientRepository", "Lcom/example/nawajeth/features/patients/domain/repository/PatientRepository;", "getLicenseStatusUseCase", "Lcom/example/nawajeth/features/patients/domain/usecase/GetLicenseStatusUseCase;", "(Lcom/example/nawajeth/features/patients/domain/repository/PatientRepository;Lcom/example/nawajeth/features/patients/domain/usecase/GetLicenseStatusUseCase;)V", "invoke", "Lkotlin/Result;", "", "patient", "error/NonExistentClass", "invoke-gIAlu-s", "(Lerror/NonExistentClass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "patients_debug"})
public final class AddPatientUseCase {
    private final com.example.nawajeth.features.patients.domain.repository.PatientRepository patientRepository = null;
    private final com.example.nawajeth.features.patients.domain.usecase.GetLicenseStatusUseCase getLicenseStatusUseCase = null;
    
    @javax.inject.Inject
    public AddPatientUseCase(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.patients.domain.repository.PatientRepository patientRepository, @org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.patients.domain.usecase.GetLicenseStatusUseCase getLicenseStatusUseCase) {
        super();
    }
}