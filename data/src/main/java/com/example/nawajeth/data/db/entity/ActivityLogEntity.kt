package com.example.nawajeth.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "activity_logs",
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
data class ActivityLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val patientId: Long?,  // قد يكون null إذا كان النشاط عاماً وليس مرتبطاً بمريض محدد
    val activityType: String,  // نوع النشاط (إضافة مريض، تعديل عمل، دفع، إلخ)
    val description: String,  // وصف النشاط
    val timestamp: Long,  // وقت حدوث النشاط
    val userId: String?  // معرف المستخدم الذي قام بالنشاط (للاستخدام المستقبلي)
)
