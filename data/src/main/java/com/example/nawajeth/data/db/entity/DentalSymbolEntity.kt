package com.example.nawajeth.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "dental_symbols",
    foreignKeys = [
        ForeignKey(
            entity = WorkTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["workTypeId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("workTypeId")]
)
data class DentalSymbolEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val symbolPath: String, // مسار الرمز (صورة أو SVG)
    val workTypeId: Long?, // معرف نوع العمل المرتبط (قد يكون null)
    val color: String, // اللون المستخدم في واجهة المستخدم
    val isActive: Boolean, // هل هذا الرمز نشط أم لا
    val createdAt: Long // تاريخ إنشاء السجل
)
