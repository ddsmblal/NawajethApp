package com.manus.nawajethapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.manus.nawajethapp.data.local.AppDatabase
import com.manus.nawajethapp.data.model.MedicalSymbol
import com.manus.nawajethapp.data.model.WorkDefinition
import com.manus.nawajethapp.data.repository.SettingsRepository
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SettingsRepository

    val allMedicalSymbols: LiveData<List<MedicalSymbol>>
    val allWorkDefinitions: LiveData<List<WorkDefinition>>

    init {
        val medicalSymbolDao = AppDatabase.getDatabase(application).medicalSymbolDao()
        val workDefinitionDao = AppDatabase.getDatabase(application).workDefinitionDao()
        repository = SettingsRepository(medicalSymbolDao, workDefinitionDao)
        allMedicalSymbols = repository.getAllMedicalSymbols()
        allWorkDefinitions = repository.getAllWorkDefinitions()
    }

    // MedicalSymbol functions
    fun insertMedicalSymbol(symbol: MedicalSymbol) = viewModelScope.launch {
        repository.insertMedicalSymbol(symbol)
    }

    fun updateMedicalSymbol(symbol: MedicalSymbol) = viewModelScope.launch {
        repository.updateMedicalSymbol(symbol)
    }

    fun deleteMedicalSymbol(symbol: MedicalSymbol) = viewModelScope.launch {
        repository.deleteMedicalSymbol(symbol)
    }

    fun getMedicalSymbolById(id: Long): LiveData<MedicalSymbol> {
        return repository.getMedicalSymbolById(id)
    }

    // WorkDefinition functions
    fun insertWorkDefinition(workDefinition: WorkDefinition) = viewModelScope.launch {
        repository.insertWorkDefinition(workDefinition)
    }

    fun updateWorkDefinition(workDefinition: WorkDefinition) = viewModelScope.launch {
        repository.updateWorkDefinition(workDefinition)
    }

    fun deleteWorkDefinition(workDefinition: WorkDefinition) = viewModelScope.launch {
        repository.deleteWorkDefinition(workDefinition)
    }

    fun getWorkDefinitionById(id: Long): LiveData<WorkDefinition> {
        return repository.getWorkDefinitionById(id)
    }
}

