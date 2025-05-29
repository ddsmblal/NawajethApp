package com.example.nawajeth.features.settings.domain.usecase

import com.example.nawajeth.features.settings.domain.model.LicenseStatus
import com.example.nawajeth.features.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class GetLicenseStatusUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): LicenseStatus {
        return settingsRepository.getLicenseStatus()
    }
}
