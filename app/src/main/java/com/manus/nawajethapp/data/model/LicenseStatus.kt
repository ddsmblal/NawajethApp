package com.manus.nawajethapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * نموذج بيانات لتخزين حالة النسخة التجريبية والتفعيل
 */
@Entity(tableName = "license_status")
data class LicenseStatus(
    @PrimaryKey val id: Int = 1, // نستخدم معرف ثابت لأننا نحتاج سجل واحد فقط
    val isFullVersion: Boolean = false, // هل النسخة الكاملة مفعلة
    val activationKey: String = "", // مفتاح التفعيل المستخدم (إذا كان موجودًا)
    val activationDate: Long = 0, // تاريخ التفعيل (إذا كان موجودًا)
    val trialPatientsLimit: Int = 10 // الحد الأقصى لعدد المرضى في النسخة التجريبية
)
