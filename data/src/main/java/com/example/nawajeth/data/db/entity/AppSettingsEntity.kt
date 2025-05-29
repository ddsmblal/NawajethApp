package com.example.nawajeth.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_settings")
data class AppSettingsEntity(
    @PrimaryKey
    val id: Int = 1, // دائماً 1 لأنه سجل واحد فقط
    val language: String, // ar أو en
    val theme: String, // light أو dark
    val backupFrequency: Int, // عدد الأيام بين النسخ الاحتياطي التلقائي (0 لإيقاف التلقائي)
    val lastBackupDate: Long?, // تاريخ آخر نسخة احتياطية
    val backupLocation: String?, // مسار النسخ الاحتياطي
    val isActivated: Boolean, // هل التطبيق مفعل أم لا
    val activationCode: String?, // رمز التفعيل
    val deviceId: String?, // الرقم الفريد للجهاز
    val updatedAt: Long // تاريخ آخر تحديث
)
