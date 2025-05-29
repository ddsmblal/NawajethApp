package com.example.nawajeth.features.settings.domain.usecase

import com.example.nawajeth.features.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class ActivateAppUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(activationCode: String): Result<Boolean> {
        if (activationCode.isBlank()) {
            return Result.failure(Exception("رمز التفعيل لا يمكن أن يكون فارغاً"))
        }
        
        return settingsRepository.activateApp(activationCode)
    }
}
