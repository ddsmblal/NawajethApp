package com.manus.nawajethapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "treatment_sessions",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["id"],
            childColumns = ["patientId"],
            onDelete = ForeignKey.CASCADE // If a patient is deleted, their sessions are also deleted
        )
    ]
)
data class TreatmentSession(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val patientId: Long,
    val sessionName: String, // e.g., "Visit [Date]" or custom name
    val sessionDate: Date = Date(),
    var isClosed: Boolean = false // To mark if the session's financial cycle is closed
    // Add other session-specific fields if needed, like total_due, total_paid for this session
)

