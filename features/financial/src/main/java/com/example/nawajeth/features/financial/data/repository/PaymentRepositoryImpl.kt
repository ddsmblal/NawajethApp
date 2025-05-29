package com.example.nawajeth.features.financial.data.repository

import com.example.nawajeth.data.db.dao.PaymentDao
import com.example.nawajeth.data.db.entity.PaymentEntity
import com.example.nawajeth.features.financial.domain.model.Payment
import com.example.nawajeth.features.financial.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val paymentDao: PaymentDao
) : PaymentRepository {
    
    override fun getAllPayments(): Flow<List<Payment>> {
        return paymentDao.getAllPayments().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override fun getPaymentsByPatientId(patientId: Long): Flow<List<Payment>> {
        return paymentDao.getPaymentsByPatientId(patientId).map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override fun getPaymentsByDentalWorkId(dentalWorkId: Long): Flow<List<Payment>> {
        return paymentDao.getPaymentsByDentalWorkId(dentalWorkId).map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override fun getPaymentById(id: Long): Flow<Payment?> {
        return paymentDao.getPaymentById(id).map { entity ->
            entity?.toDomain()
        }
    }
    
    override suspend fun addPayment(payment: Payment): Long {
        return paymentDao.insertPayment(payment.toEntity())
    }
    
    override suspend fun updatePayment(payment: Payment) {
        paymentDao.updatePayment(payment.toEntity())
    }
    
    override suspend fun deletePayment(paymentId: Long) {
        paymentDao.deletePayment(paymentId)
    }
    
    override suspend fun getTotalPaymentsForPeriod(startDate: Long, endDate: Long): Double {
        return paymentDao.getTotalPaymentsForPeriod(startDate, endDate)
    }
    
    override fun getPaymentsForPeriod(startDate: Long, endDate: Long): Flow<List<Payment>> {
        return paymentDao.getPaymentsForPeriod(startDate, endDate).map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    private fun PaymentEntity.toDomain(): Payment {
        return Payment(
            id = id,
            patientId = patientId,
            dentalWorkId = dentalWorkId,
            amount = amount,
            date = date,
            notes = notes
        )
    }
    
    private fun Payment.toEntity(): PaymentEntity {
        return PaymentEntity(
            id = id,
            patientId = patientId,
            dentalWorkId = dentalWorkId,
            amount = amount,
            date = date,
            notes = notes
        )
    }
}
