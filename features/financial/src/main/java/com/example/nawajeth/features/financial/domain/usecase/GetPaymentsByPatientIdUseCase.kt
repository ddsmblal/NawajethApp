package com.example.nawajeth.features.financial.domain.usecase

import com.example.nawajeth.features.financial.domain.model.Payment
import com.example.nawajeth.features.financial.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPaymentsByPatientIdUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {
    operator fun invoke(patientId: Long): Flow<List<Payment>> {
        return paymentRepository.getPaymentsByPatientId(patientId)
    }
}
