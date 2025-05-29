package com.example.nawajeth.features.financial.domain.repository

import com.example.nawajeth.features.financial.domain.model.Payment
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    fun getAllPayments(): Flow<List<Payment>>
    fun getPaymentsByPatientId(patientId: Long): Flow<List<Payment>>
    fun getPaymentsByDentalWorkId(dentalWorkId: Long): Flow<List<Payment>>
    fun getPaymentById(id: Long): Flow<Payment?>
    suspend fun addPayment(payment: Payment): Long
    suspend fun updatePayment(payment: Payment)
    suspend fun deletePayment(paymentId: Long)
    suspend fun getTotalPaymentsForPeriod(startDate: Long, endDate: Long): Double
    fun getPaymentsForPeriod(startDate: Long, endDate: Long): Flow<List<Payment>>
}
