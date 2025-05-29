package com.example.nawajeth.data.db.dao

import androidx.room.*
import com.example.nawajeth.data.db.entity.PaymentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {
    @Query("SELECT * FROM payments WHERE patientId = :patientId ORDER BY paymentDate DESC")
    fun getPaymentsByPatientId(patientId: Long): Flow<List<PaymentEntity>>
    
    @Query("SELECT * FROM payments WHERE dentalWorkId = :dentalWorkId ORDER BY paymentDate DESC")
    fun getPaymentsByDentalWorkId(dentalWorkId: Long): Flow<List<PaymentEntity>>
    
    @Query("SELECT * FROM payments WHERE id = :paymentId")
    suspend fun getPaymentById(paymentId: Long): PaymentEntity?
    
    @Query("SELECT SUM(amount) FROM payments WHERE patientId = :patientId")
    suspend fun getTotalPaymentsForPatient(patientId: Long): Double?
    
    @Query("SELECT SUM(amount) FROM payments WHERE dentalWorkId = :dentalWorkId")
    suspend fun getTotalPaymentsForDentalWork(dentalWorkId: Long): Double?
    
    @Query("SELECT * FROM payments ORDER BY paymentDate DESC LIMIT :limit")
    fun getRecentPayments(limit: Int): Flow<List<PaymentEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayment(payment: PaymentEntity): Long
    
    @Update
    suspend fun updatePayment(payment: PaymentEntity)
    
    @Delete
    suspend fun deletePayment(payment: PaymentEntity)
    
    @Query("DELETE FROM payments WHERE id = :paymentId")
    suspend fun deletePaymentById(paymentId: Long)
}
