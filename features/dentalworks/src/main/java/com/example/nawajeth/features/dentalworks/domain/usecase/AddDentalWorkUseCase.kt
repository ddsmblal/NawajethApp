package com.example.nawajeth.features.dentalworks.domain.usecase

import com.example.nawajeth.features.dentalworks.domain.model.DentalWork
import com.example.nawajeth.features.dentalworks.domain.repository.DentalWorkRepository
import javax.inject.Inject

class AddDentalWorkUseCase @Inject constructor(
    private val dentalWorkRepository: DentalWorkRepository
) {
    suspend operator fun invoke(dentalWork: DentalWork): Result<Long> {
        return try {
            val id = dentalWorkRepository.addDentalWork(dentalWork)
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
