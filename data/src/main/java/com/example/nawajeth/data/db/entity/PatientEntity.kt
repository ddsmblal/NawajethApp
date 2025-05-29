package com.example.nawajeth.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patients")
data class PatientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val age: Int,
    val gender: String,
    val phone: String,
    val notes: String,
    val lastVisitDate: Long?, // تاريخ آخر زيارة بصيغة timestamp
    val totalDue: Double, // المبلغ المستحق الإجمالي
    val createdAt: Long // تاريخ إنشاء السجل
)
