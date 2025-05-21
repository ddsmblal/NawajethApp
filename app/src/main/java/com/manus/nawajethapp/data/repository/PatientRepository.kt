package com.manus.nawajethapp.data.repository

import androidx.lifecycle.LiveData
import com.manus.nawajethapp.data.local.PatientDao
import com.manus.nawajethapp.data.model.Patient

class PatientRepository(private val patientDao: PatientDao) {

    val allPatients: LiveData<List<Patient>> = patientDao.getAllPatients()

    suspend fun insert(patient: Patient): Long {
        return patientDao.insertPatient(patient)
    }

    suspend fun update(patient: Patient) {
        patientDao.updatePatient(patient)
    }

    suspend fun delete(patient: Patient) {
        patientDao.deletePatient(patient)
    }

    fun getPatientById(patientId: Long): LiveData<Patient> {
        return patientDao.getPatientById(patientId)
    }

    fun searchPatients(query: String): LiveData<List<Patient>> {
        return patientDao.searchPatients("%" + query + "%") // Add wildcards for LIKE query
    }
}

