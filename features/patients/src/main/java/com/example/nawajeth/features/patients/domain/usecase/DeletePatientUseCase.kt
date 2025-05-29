package com.example.nawajeth.features.patients.domain.usecase

import com.example.nawajeth.features.patients.domain.repository.PatientRepository
import javax.inject.Inject

class DeletePatientUseCase @Inject constructor(
    private val patientRepository: PatientRepository
) {
    suspend operator fun invoke(patientId: Long): Result<Unit> {
        return try {
            // التحقق من وجود أعمال سنية أو دفعات مرتبطة بالمريض
            val hasRelatedWorks = patientRepository.hasRelatedDentalWorks(patientId)
            val hasRelatedPayments = patientRepository.hasRelatedPayments(patientId)
            
            if (hasRelatedWorks || hasRelatedPayments) {
                return Result.failure(Exception("لا يمكن حذف المريض لوجود أعمال سنية أو دفعات مرتبطة به. يرجى حذف الأعمال والدفعات أولاً."))
            }
            
            patientRepository.deletePatient(patientId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
