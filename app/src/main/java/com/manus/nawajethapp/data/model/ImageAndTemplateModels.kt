package com.manus.nawajethapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "xray_images",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["id"],
            childColumns = ["patientId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class XrayImage(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val patientId: Long,
    val imagePath: String, // Path to the image file on device storage
    val annotatedImagePath: String? = null, // Path to the modified image with annotations
    val description: String? = null,
    val uploadDate: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "photographic_images",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["id"],
            childColumns = ["patientId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PhotographicImage(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val patientId: Long,
    val imagePath: String, // Path to the image file on device storage
    val caption: String? = null,
    val uploadDate: Long = System.currentTimeMillis()
)

@Entity(tableName = "medical_work_templates")
data class MedicalWorkTemplate(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val workName: String,
    val defaultPrice: Double, // Using Double for price, consider BigDecimal for precision if needed
    val customSymbolPath: String? = null // Path to a custom symbol image (PNG/SVG)
)

