package com.example.nawajeth.features.dentalworks.domain.usecase;

import com.example.nawajeth.features.dentalworks.domain.model.DentalWork;
import com.example.nawajeth.features.dentalworks.domain.repository.DentalWorkRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\nH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/nawajeth/features/dentalworks/domain/usecase/GetDentalWorksByPatientIdUseCase;", "", "dentalWorkRepository", "Lcom/example/nawajeth/features/dentalworks/domain/repository/DentalWorkRepository;", "(Lcom/example/nawajeth/features/dentalworks/domain/repository/DentalWorkRepository;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "", "error/NonExistentClass", "patientId", "", "dentalworks_debug"})
public final class GetDentalWorksByPatientIdUseCase {
    private final com.example.nawajeth.features.dentalworks.domain.repository.DentalWorkRepository dentalWorkRepository = null;
    
    @javax.inject.Inject
    public GetDentalWorksByPatientIdUseCase(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.dentalworks.domain.repository.DentalWorkRepository dentalWorkRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<DentalWork>> invoke(long patientId) {
        return null;
    }
}