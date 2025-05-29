package com.example.nawajeth.features.settings.domain.usecase

import com.example.nawajeth.features.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class GetDeviceIdUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): String {
        return settingsRepository.getDeviceId()
    }
}
