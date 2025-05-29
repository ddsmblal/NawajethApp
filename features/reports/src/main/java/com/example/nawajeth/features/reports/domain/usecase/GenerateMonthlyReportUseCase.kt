package com.example.nawajeth.features.reports.domain.usecase

import com.example.nawajeth.features.dentalworks.domain.model.DentalWork
import com.example.nawajeth.features.dentalworks.domain.repository.DentalWorkRepository
import com.example.nawajeth.features.financial.domain.repository.PaymentRepository
import com.example.nawajeth.features.patients.domain.repository.PatientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GenerateMonthlyReportUseCase @Inject constructor(
    private val patientRepository: PatientRepository,
    private val dentalWorkRepository: DentalWorkRepository,
    private val paymentRepository: PaymentRepository
) {
    suspend operator fun invoke(startDate: Long, endDate: Long): MonthlyReport {
        // عدد المرضى الجدد
        val newPatientsCount = patientRepository.getNewPatientsCountInPeriod(startDate, endDate)
        
        // الأعمال المنجزة
        val completedWorks = dentalWorkRepository.getCompletedWorks(startDate, endDate).first()
        
        // المدفوعات المستلمة
        val payments = paymentRepository.getPaymentsForPeriod(startDate, endDate).first()
        
        // إجمالي الإيرادات
        val totalIncome = payments.sumOf { it.amount }
        
        // إجمالي قيمة الأعمال
        val totalWorkValue = completedWorks.sumOf { it.cost }
        
        return MonthlyReport(
            startDate = startDate,
            endDate = endDate,
            newPatientsCount = newPatientsCount,
            completedWorks = completedWorks,
            payments = payments,
            totalIncome = totalIncome,
            totalWorkValue = totalWorkValue
        )
    }
}

data class MonthlyReport(
    val startDate: Long,
    val endDate: Long,
    val newPatientsCount: Int,
    val completedWorks: List<DentalWork>,
    val payments: List<com.example.nawajeth.features.financial.domain.model.Payment>,
    val totalIncome: Double,
    val totalWorkValue: Double
)
