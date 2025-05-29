package com.example.nawajeth.data.db.dao

import androidx.room.*
import com.example.nawajeth.data.db.entity.DentalSymbolEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DentalSymbolDao {
    @Query("SELECT * FROM dental_symbols WHERE isActive = 1 ORDER BY name ASC")
    fun getAllActiveDentalSymbols(): Flow<List<DentalSymbolEntity>>
    
    @Query("SELECT * FROM dental_symbols ORDER BY name ASC")
    fun getAllDentalSymbols(): Flow<List<DentalSymbolEntity>>
    
    @Query("SELECT * FROM dental_symbols WHERE id = :symbolId")
    suspend fun getDentalSymbolById(symbolId: Long): DentalSymbolEntity?
    
    @Query("SELECT * FROM dental_symbols WHERE workTypeId = :workTypeId AND isActive = 1")
    fun getDentalSymbolsByWorkType(workTypeId: Long): Flow<List<DentalSymbolEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDentalSymbol(dentalSymbol: DentalSymbolEntity): Long
    
    @Update
    suspend fun updateDentalSymbol(dentalSymbol: DentalSymbolEntity)
    
    @Delete
    suspend fun deleteDentalSymbol(dentalSymbol: DentalSymbolEntity)
    
    @Query("DELETE FROM dental_symbols WHERE id = :symbolId")
    suspend fun deleteDentalSymbolById(symbolId: Long)
}
