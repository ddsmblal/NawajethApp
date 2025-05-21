package com.manus.nawajethapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.math.BigDecimal // For precise currency handling

@Entity(
    tableName = "treatment_tasks",
    foreignKeys = [
        ForeignKey(
            entity = TreatmentSession::class,
            parentColumns = ["id"],
            childColumns = ["sessionId"],
            onDelete = ForeignKey.CASCADE // If a session is deleted, its tasks are also deleted
        ),
        ForeignKey(
            entity = WorkDefinition::class, // Link to WorkDefinition for default name/price
            parentColumns = ["id"],
            childColumns = ["workDefinitionId"],
            onDelete = ForeignKey.SET_NULL // If a work definition is deleted, keep the task but nullify the link
        ),
        ForeignKey(
            entity = MedicalSymbol::class, // Optional link to a specific symbol used for this task instance
            parentColumns = ["id"],
            childColumns = ["medicalSymbolId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class TreatmentTask(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sessionId: Long,
    var taskName: String, // Can be custom or from WorkDefinition
    var amountDue: BigDecimal, // Can be custom or from WorkDefinition
    var amountPaid: BigDecimal = BigDecimal.ZERO,
    var isCompleted: Boolean = false,
    val description: String? = null, // Optional detailed description for the task
    val workDefinitionId: Long? = null, // Link to the predefined work, if any
    val medicalSymbolId: Long? = null, // Optional: specific symbol used/drawn for this task instance
    // customPrice allows overriding the WorkDefinition's defaultPrice for this specific task
    // If workDefinitionId is null, taskName and amountDue are fully custom.
    // If workDefinitionId is not null, taskName and amountDue might initially come from it,
    // but amountDue can be overridden by a value here (e.g. if customPrice was applied at creation)
)

