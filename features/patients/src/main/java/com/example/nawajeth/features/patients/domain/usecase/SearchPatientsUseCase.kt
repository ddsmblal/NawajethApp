package com.example.nawajeth.features.patients.domain.usecase

import com.example.nawajeth.features.patients.domain.model.Patient
import com.example.nawajeth.features.patients.domain.repository.PatientRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPatientsUseCase @Inject constructor(
    private val patientRepository: PatientRepository
) {
    operator fun invoke(query: String): Flow<List<Patient>> {
        return patientRepository.searchPatients(query)
    }
}
