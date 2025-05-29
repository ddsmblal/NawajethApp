package com.example.nawajeth.features.patients.domain.usecase

import com.example.nawajeth.features.patients.domain.model.Patient
import com.example.nawajeth.features.patients.domain.repository.PatientRepository
import javax.inject.Inject

class UpdatePatientUseCase @Inject constructor(
    private val patientRepository: PatientRepository
) {
    suspend operator fun invoke(patient: Patient): Result<Unit> {
        return try {
            patientRepository.updatePatient(patient)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
