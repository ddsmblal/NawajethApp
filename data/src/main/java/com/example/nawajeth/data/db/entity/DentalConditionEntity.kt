package com.example.nawajeth.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "dental_conditions",
    foreignKeys = [
        ForeignKey(
            entity = PatientEntity::class,
            parentColumns = ["id"],
            childColumns = ["patientId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("patientId")]
)
data class DentalConditionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val patientId: Long,
    val toothNumber: Int?, // رقم السن، قد يكون null إذا كانت الحالة عامة
    val symbolId: Long?, // معرف الرمز المستخدم (مرتبط بجدول الرموز)
    val description: String,
    val notes: String,
    val recordDate: Long, // تاريخ تسجيل الحالة
    val createdAt: Long // تاريخ إنشاء السجل
)
