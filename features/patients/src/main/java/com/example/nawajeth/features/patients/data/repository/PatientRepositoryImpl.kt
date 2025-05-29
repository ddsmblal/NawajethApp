package com.example.nawajeth.features.patients.data.repository

import com.example.nawajeth.features.patients.data.mapper.toEntity
import com.example.nawajeth.features.patients.data.mapper.toModel
import com.example.nawajeth.features.patients.domain.model.Patient
import com.example.nawajeth.features.patients.domain.repository.PatientRepository
import com.example.nawajeth.data.db.dao.PatientDao
import com.example.nawajeth.data.db.dao.DentalWorkDao
import com.example.nawajeth.data.db.dao.PaymentDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PatientRepositoryImpl @Inject constructor(
    private val patientDao: PatientDao,
    private val dentalWorkDao: DentalWorkDao,
    private val paymentDao: PaymentDao
) : PatientRepository {

    override fun getAllPatients(): Flow<List<Patient>> {
        return patientDao.getAllPatients().map { entities ->
            entities.map { it.toModel() }
        }
    }

    override fun searchPatients(query: String): Flow<List<Patient>> {
        return patientDao.searchPatients("%$query%").map { entities ->
            entities.map { it.toModel() }
        }
    }

    override fun getPatientById(id: Long): Flow<Patient?> {
        return patientDao.getPatientById(id).map { entity ->
            entity?.toModel()
        }
    }

    override suspend fun getPatientCount(): Int {
        return patientDao.getPatientCount()
    }

    override suspend fun addPatient(patient: Patient): Long {
        return patientDao.insertPatient(patient.toEntity())
    }

    override suspend fun updatePatient(patient: Patient) {
        patientDao.updatePatient(patient.toEntity())
    }

    override suspend fun deletePatient(patientId: Long) {
        patientDao.deletePatient(patientId)
    }

    override suspend fun hasRelatedDentalWorks(patientId: Long): Boolean {
        return dentalWorkDao.getWorkCountForPatient(patientId) > 0
    }

    override suspend fun hasRelatedPayments(patientId: Long): Boolean {
        return paymentDao.getPaymentCountForPatient(patientId) > 0
    }
}
