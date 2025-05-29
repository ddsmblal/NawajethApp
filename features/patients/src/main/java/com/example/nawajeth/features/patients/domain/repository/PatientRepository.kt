package com.example.nawajeth.features.patients.domain.repository

import com.example.nawajeth.features.patients.domain.model.Patient
import kotlinx.coroutines.flow.Flow

interface PatientRepository {
    fun getAllPatients(): Flow<List<Patient>>
    fun searchPatients(query: String): Flow<List<Patient>>
    fun getPatientById(id: Long): Flow<Patient?>
    suspend fun getPatientCount(): Int
    suspend fun addPatient(patient: Patient): Long
    suspend fun updatePatient(patient: Patient)
    suspend fun deletePatient(patientId: Long)
    suspend fun hasRelatedDentalWorks(patientId: Long): Boolean
    suspend fun hasRelatedPayments(patientId: Long): Boolean
}
