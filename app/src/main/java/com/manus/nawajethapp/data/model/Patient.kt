package com.manus.nawajethapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "patients")
data class Patient(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val fullName: String,
    val registrationDate: Date = Date() // Default to current date
    // Add other patient-specific fields as identified in the detailed summary
    // For example, contact information, notes, etc. if they become necessary
)

