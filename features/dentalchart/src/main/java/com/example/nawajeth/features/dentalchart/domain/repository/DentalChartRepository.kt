package com.example.nawajeth.features.dentalchart.domain.repository

import com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol
import com.example.nawajeth.features.dentalchart.domain.model.ToothCondition
import kotlinx.coroutines.flow.Flow

interface DentalChartRepository {
    // عمليات الرموز السنية
    fun getAllSymbols(): Flow<List<DentalSymbol>>
    fun getSymbolById(id: Long): Flow<DentalSymbol?>
    suspend fun addSymbol(symbol: DentalSymbol): Long
    suspend fun updateSymbol(symbol: DentalSymbol)
    suspend fun deleteSymbol(symbolId: Long)
    
    // عمليات حالات الأسنان
    fun getToothConditionsByPatientId(patientId: Long): Flow<List<ToothCondition>>
    fun getToothConditionById(id: Long): Flow<ToothCondition?>
    suspend fun addToothCondition(condition: ToothCondition): Long
    suspend fun updateToothCondition(condition: ToothCondition)
    suspend fun deleteToothCondition(conditionId: Long)
    suspend fun deleteAllToothConditionsForPatient(patientId: Long)
}
