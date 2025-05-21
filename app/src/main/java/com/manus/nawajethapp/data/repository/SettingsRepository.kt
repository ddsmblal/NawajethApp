package com.manus.nawajethapp.data.repository

import androidx.lifecycle.LiveData
import com.manus.nawajethapp.data.local.MedicalSymbolDao
import com.manus.nawajethapp.data.local.WorkDefinitionDao
import com.manus.nawajethapp.data.model.MedicalSymbol
import com.manus.nawajethapp.data.model.WorkDefinition

class SettingsRepository(
    private val medicalSymbolDao: MedicalSymbolDao,
    private val workDefinitionDao: WorkDefinitionDao
) {

    // MedicalSymbol operations
    fun getAllMedicalSymbols(): LiveData<List<MedicalSymbol>> = medicalSymbolDao.getAllSymbols()
    fun getMedicalSymbolById(id: Long): LiveData<MedicalSymbol> = medicalSymbolDao.getSymbolById(id)
    suspend fun insertMedicalSymbol(symbol: MedicalSymbol) = medicalSymbolDao.insertSymbol(symbol)
    suspend fun updateMedicalSymbol(symbol: MedicalSymbol) = medicalSymbolDao.updateSymbol(symbol)
    suspend fun deleteMedicalSymbol(symbol: MedicalSymbol) = medicalSymbolDao.deleteSymbol(symbol)

    // WorkDefinition operations
    fun getAllWorkDefinitions(): LiveData<List<WorkDefinition>> = workDefinitionDao.getAllWorkDefinitions()
    fun getWorkDefinitionById(id: Long): LiveData<WorkDefinition> = workDefinitionDao.getWorkDefinitionById(id)
    suspend fun insertWorkDefinition(workDefinition: WorkDefinition) = workDefinitionDao.insertWorkDefinition(workDefinition)
    suspend fun updateWorkDefinition(workDefinition: WorkDefinition) = workDefinitionDao.updateWorkDefinition(workDefinition)
    suspend fun deleteWorkDefinition(workDefinition: WorkDefinition) = workDefinitionDao.deleteWorkDefinition(workDefinition)
}

