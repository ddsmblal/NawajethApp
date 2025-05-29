package com.example.nawajeth.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "dental_works",
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
data class DentalWorkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val patientId: Long,
    val toothNumber: Int?, // رقم السن، قد يكون null إذا كان العمل عاماً
    val workTypeId: Long, // معرف نوع العمل (مرتبط بجدول أنواع الأعمال)
    val description: String,
    val cost: Double, // تكلفة العمل
    val paidAmount: Double, // المبلغ المدفوع
    val remainingAmount: Double, // المبلغ المتبقي
    val status: String, // حالة العمل (مكتمل، قيد التنفيذ، مخطط له)
    val startDate: Long, // تاريخ بدء العمل
    val endDate: Long?, // تاريخ انتهاء العمل، قد يكون null إذا لم يكتمل بعد
    val notes: String,
    val createdAt: Long // تاريخ إنشاء السجل
)
