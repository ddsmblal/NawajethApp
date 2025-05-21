package com.manus.nawajethapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medical_symbols")
data class MedicalSymbol(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val symbolName: String,
    val symbolType: String, // "svg" or "png"
    val symbolData: String // Path to local file or SVG string data
)

@Entity(tableName = "work_definitions")
data class WorkDefinition(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val workName: String,
    val defaultPrice: Double,
    val category: String? = null, // e.g., "Filling", "Extraction", "Prosthetics"
    val defaultSymbolId: Long? = null // Optional: Link to a MedicalSymbol
)

