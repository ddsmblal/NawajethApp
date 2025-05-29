package com.example.nawajeth.features.financial.domain.usecase

import com.example.nawajeth.features.financial.domain.model.Payment
import com.example.nawajeth.features.financial.domain.repository.PaymentRepository
import javax.inject.Inject

class AddPaymentUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository,
    private val dentalWorkRepository: com.example.nawajeth.features.dentalworks.domain.repository.DentalWorkRepository
) {
    suspend operator fun invoke(payment: Payment): Result<Long> {
        return try {
            // إذا كانت الدفعة مرتبطة بعمل سني، نتحقق من أن المبلغ لا يتجاوز المبلغ المتبقي
            payment.dentalWorkId?.let { dentalWorkId ->
                val dentalWork = dentalWorkRepository.getDentalWorkById(dentalWorkId).value
                if (dentalWork != null) {
                    val totalPaid = dentalWorkRepository.getDentalWorkPaidForPatient(dentalWorkId)
                    val remaining = dentalWork.cost - totalPaid
                    
                    if (payment.amount > remaining) {
                        return Result.failure(Exception("مبلغ الدفعة يتجاوز المبلغ المتبقي للعمل السني"))
                    }
                }
            }
            
            val id = paymentRepository.addPayment(payment)
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
