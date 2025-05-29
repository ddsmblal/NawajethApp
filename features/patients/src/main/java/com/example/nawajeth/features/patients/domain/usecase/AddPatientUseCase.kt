package com.example.nawajeth.features.patients.domain.usecase

import com.example.nawajeth.features.patients.domain.model.Patient
import com.example.nawajeth.features.patients.domain.repository.PatientRepository
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(
    private val patientRepository: PatientRepository,
    private val getLicenseStatusUseCase: GetLicenseStatusUseCase
) {
    suspend operator fun invoke(patient: Patient): Result<Long> {
        // التحقق من حالة الترخيص وعدد المرضى
        val licenseStatus = getLicenseStatusUseCase()
        if (!licenseStatus.isActivated) {
            val patientCount = patientRepository.getPatientCount()
            if (patientCount >= 10) {
                return Result.failure(Exception("تم الوصول للحد الأقصى من المرضى في النسخة التجريبية (10 مرضى). يرجى تفعيل النسخة الكاملة."))
            }
        }
        
        return try {
            val id = patientRepository.addPatient(patient)
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
