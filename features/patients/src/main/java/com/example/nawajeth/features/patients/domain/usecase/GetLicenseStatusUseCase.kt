package com.example.nawajeth.features.patients.domain.usecase

import com.example.nawajeth.features.patients.domain.repository.PatientRepository
import javax.inject.Inject

class GetLicenseStatusUseCase @Inject constructor(
    private val settingsRepository: com.example.nawajeth.features.settings.domain.repository.SettingsRepository
) {
    suspend operator fun invoke(): com.example.nawajeth.features.settings.domain.model.LicenseStatus {
        return settingsRepository.getLicenseStatus()
    }
}
