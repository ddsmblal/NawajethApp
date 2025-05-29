package com.example.nawajeth.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "work_types")
data class WorkTypeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val defaultCost: Double, // التكلفة الافتراضية
    val color: String, // اللون المستخدم في واجهة المستخدم
    val iconPath: String?, // مسار الرمز المستخدم (إن وجد)
    val isActive: Boolean, // هل هذا النوع نشط أم لا
    val createdAt: Long // تاريخ إنشاء السجل
)
