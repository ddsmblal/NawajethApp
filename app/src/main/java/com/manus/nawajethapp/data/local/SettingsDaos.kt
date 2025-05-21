package com.manus.nawajethapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.manus.nawajethapp.data.model.MedicalSymbol
import com.manus.nawajethapp.data.model.WorkDefinition

@Dao
interface MedicalSymbolDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSymbol(symbol: MedicalSymbol): Long

    @Update
    suspend fun updateSymbol(symbol: MedicalSymbol)

    @Delete
    suspend fun deleteSymbol(symbol: MedicalSymbol)

    @Query("SELECT * FROM medical_symbols ORDER BY symbolName ASC")
    fun getAllSymbols(): LiveData<List<MedicalSymbol>>

    @Query("SELECT * FROM medical_symbols WHERE id = :symbolId")
    fun getSymbolById(symbolId: Long): LiveData<MedicalSymbol>
}

@Dao
interface WorkDefinitionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkDefinition(workDefinition: WorkDefinition): Long

    @Update
    suspend fun updateWorkDefinition(workDefinition: WorkDefinition)

    @Delete
    suspend fun deleteWorkDefinition(workDefinition: WorkDefinition)

    @Query("SELECT * FROM work_definitions ORDER BY workName ASC")
    fun getAllWorkDefinitions(): LiveData<List<WorkDefinition>>

    @Query("SELECT * FROM work_definitions WHERE id = :workId")
    fun getWorkDefinitionById(workId: Long): LiveData<WorkDefinition>
}

