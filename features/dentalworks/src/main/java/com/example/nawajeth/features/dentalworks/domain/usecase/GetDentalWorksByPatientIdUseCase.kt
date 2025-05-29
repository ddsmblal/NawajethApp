package com.example.nawajeth.features.dentalworks.domain.usecase

import com.example.nawajeth.features.dentalworks.domain.model.DentalWork
import com.example.nawajeth.features.dentalworks.domain.repository.DentalWorkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDentalWorksByPatientIdUseCase @Inject constructor(
    private val dentalWorkRepository: DentalWorkRepository
) {
    operator fun invoke(patientId: Long): Flow<List<DentalWork>> {
        return dentalWorkRepository.getDentalWorksByPatientId(patientId)
    }
}
