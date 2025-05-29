package com.example.nawajeth.features.reports.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nawajeth.features.dentalworks.domain.model.DentalWork
import com.example.nawajeth.features.financial.domain.model.Payment
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MonthlyReportContent(
    startDate: Long,
    endDate: Long,
    newPatientsCount: Int,
    completedWorks: List<DentalWork>,
    payments: List<Payment>,
    modifier: Modifier = Modifier
) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val currencyFormat = NumberFormat.getCurrencyInstance().apply {
        currency = Currency.getInstance("SAR")
    }
    
    val totalIncome = payments.sumOf { it.amount }
    val totalWorkValue = completedWorks.sumOf { it.cost }
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // عنوان التقرير
        Text(
            text = "التقرير الشهري",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // فترة التقرير
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "الفترة: من ${dateFormat.format(Date(startDate))} إلى ${dateFormat.format(Date(endDate))}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // ملخص الإحصائيات
        ReportStatisticsCard(
            newPatientsCount = newPatientsCount,
            completedWorksCount = completedWorks.size,
            totalIncome = totalIncome,
            totalWorkValue = totalWorkValue
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // قسم الأعمال المنجزة
        Text(
            text = "الأعمال المنجزة",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        if (completedWorks.isEmpty()) {
            Text(
                text = "لا توجد أعمال منجزة خلال هذه الفترة",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        } else {
            CompletedWorksList(completedWorks = completedWorks)
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // قسم المدفوعات
        Text(
            text = "المدفوعات المستلمة",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        if (payments.isEmpty()) {
            Text(
                text = "لا توجد مدفوعات مستلمة خلال هذه الفترة",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        } else {
            PaymentsList(payments = payments)
        }
    }
}

@Composable
fun ReportStatisticsCard(
    newPatientsCount: Int,
    completedWorksCount: Int,
    totalIncome: Double,
    totalWorkValue: Double,
    modifier: Modifier = Modifier
) {
    val currencyFormat = NumberFormat.getCurrencyInstance().apply {
        currency = Currency.getInstance("SAR")
    }
    
    androidx.compose.material3.Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            StatisticRow(
                title = "عدد المرضى الجدد:",
                value = newPatientsCount.toString()
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            StatisticRow(
                title = "عدد الأعمال المنجزة:",
                value = completedWorksCount.toString()
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            StatisticRow(
                title = "إجمالي الإيرادات:",
                value = currencyFormat.format(totalIncome)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            StatisticRow(
                title = "إجمالي قيمة الأعمال:",
                value = currencyFormat.format(totalWorkValue)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            StatisticRow(
                title = "المبالغ المتبقية:",
                value = currencyFormat.format(totalWorkValue - totalIncome),
                valueColor = if (totalWorkValue > totalIncome) Color.Red else Color.Green
            )
        }
    }
}

@Composable
fun StatisticRow(
    title: String,
    value: String,
    valueColor: Color = Color.Unspecified,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
        
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = valueColor
        )
    }
}

@Composable
fun CompletedWorksList(
    completedWorks: List<DentalWork>,
    modifier: Modifier = Modifier
) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val currencyFormat = NumberFormat.getCurrencyInstance().apply {
        currency = Currency.getInstance("SAR")
    }
    
    Column(modifier = modifier.fillMaxWidth()) {
        // عناوين الأعمدة
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "التاريخ",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            
            Text(
                text = "المريض",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            
            Text(
                text = "العمل",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1.5f)
            )
            
            Text(
                text = "المبلغ",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // قائمة الأعمال
        completedWorks.forEach { work ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = dateFormat.format(Date(work.completionDate ?: work.createdAt)),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
                
                Text(
                    text = work.patientName,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
                
                Text(
                    text = work.description,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1.5f)
                )
                
                Text(
                    text = currencyFormat.format(work.cost),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
            }
            
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun PaymentsList(
    payments: List<Payment>,
    modifier: Modifier = Modifier
) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val currencyFormat = NumberFormat.getCurrencyInstance().apply {
        currency = Currency.getInstance("SAR")
    }
    
    Column(modifier = modifier.fillMaxWidth()) {
        // عناوين الأعمدة
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "التاريخ",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            
            Text(
                text = "المريض",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            
            Text(
                text = "طريقة الدفع",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            
            Text(
                text = "المبلغ",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // قائمة المدفوعات
        payments.forEach { payment ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = dateFormat.format(Date(payment.date)),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
                
                Text(
                    text = payment.patientName,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
                
                Text(
                    text = payment.paymentMethod,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
                
                Text(
                    text = currencyFormat.format(payment.amount),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
            }
            
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
