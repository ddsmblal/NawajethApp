package com.example.nawajeth.features.dentalworks.domain.repository

import com.example.nawajeth.features.dentalworks.domain.model.DentalWork
import kotlinx.coroutines.flow.Flow

interface DentalWorkRepository {
    fun getAllDentalWorks(): Flow<List<DentalWork>>
    fun getDentalWorksByPatientId(patientId: Long): Flow<List<DentalWork>>
    fun getDentalWorkById(id: Long): Flow<DentalWork?>
    suspend fun addDentalWork(dentalWork: DentalWork): Long
    suspend fun updateDentalWork(dentalWork: DentalWork)
    suspend fun deleteDentalWork(dentalWorkId: Long)
    suspend fun getCompletedWorkCount(startDate: Long, endDate: Long): Int
    fun getCompletedWorks(startDate: Long, endDate: Long): Flow<List<DentalWork>>
    suspend fun getDentalWorkTotalForPatient(patientId: Long): Double
    suspend fun getDentalWorkPaidForPatient(patientId: Long): Double
}
