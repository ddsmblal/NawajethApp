package com.example.nawajeth.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "payments",
    foreignKeys = [
        ForeignKey(
            entity = PatientEntity::class,
            parentColumns = ["id"],
            childColumns = ["patientId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DentalWorkEntity::class,
            parentColumns = ["id"],
            childColumns = ["dentalWorkId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("patientId"), Index("dentalWorkId")]
)
data class PaymentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val patientId: Long,
    val dentalWorkId: Long?, // قد يكون null إذا كان الدفع عاماً وليس مرتبطاً بعمل محدد
    val amount: Double, // المبلغ المدفوع
    val paymentDate: Long, // تاريخ الدفع
    val paymentMethod: String, // طريقة الدفع (نقداً، بطاقة، إلخ)
    val notes: String,
    val createdAt: Long // تاريخ إنشاء السجل
)
