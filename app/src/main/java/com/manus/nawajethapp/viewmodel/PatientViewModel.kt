package com.manus.nawajethapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.manus.nawajethapp.data.local.AppDatabase
import com.manus.nawajethapp.data.model.Patient
import com.manus.nawajethapp.data.repository.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PatientRepository
    val allPatients: LiveData<List<Patient>>

    init {
        val patientDao = AppDatabase.getDatabase(application).patientDao()
        repository = PatientRepository(patientDao)
        allPatients = repository.allPatients
    }

    fun insert(patient: Patient) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(patient)
    }

    fun update(patient: Patient) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(patient)
    }

    fun delete(patient: Patient) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(patient)
    }

    fun getPatientById(patientId: Long): LiveData<Patient> {
        return repository.getPatientById(patientId)
    }

    fun searchPatients(query: String): LiveData<List<Patient>> {
        return repository.searchPatients(query)
    }
}

