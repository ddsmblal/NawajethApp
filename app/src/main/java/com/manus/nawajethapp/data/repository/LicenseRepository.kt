package com.manus.nawajethapp.data.repository

import androidx.lifecycle.LiveData
import com.manus.nawajethapp.data.local.LicenseStatusDao
import com.manus.nawajethapp.data.model.LicenseStatus

/**
 * مستودع بيانات لإدارة حالة الترخيص والنسخة التجريبية
 */
class LicenseRepository(private val licenseStatusDao: LicenseStatusDao) {

    // الحصول على حالة الترخيص الحالية
    val licenseStatus: LiveData<LicenseStatus?> = licenseStatusDao.getLicenseStatus()

    // إدخال أو تحديث حالة الترخيص
    suspend fun insertOrUpdateLicenseStatus(licenseStatus: LicenseStatus) {
        licenseStatusDao.updateLicenseStatus(licenseStatus)
    }

    // التحقق مما إذا كانت النسخة الكاملة مفعلة
    suspend fun isFullVersionActivated(): Boolean {
        return licenseStatusDao.isFullVersionActivated()
    }

    // الحصول على عدد المرضى الحالي
    suspend fun getCurrentPatientsCount(): Int {
        return licenseStatusDao.getPatientsCount()
    }

    // التحقق مما إذا كان يمكن إضافة مريض جديد (بناءً على حالة النسخة وعدد المرضى)
    suspend fun canAddNewPatient(): Boolean {
        // إذا كانت النسخة الكاملة مفعلة، يمكن إضافة عدد غير محدود من المرضى
        if (isFullVersionActivated()) {
            return true
        }
        
        // في النسخة التجريبية، نتحقق من عدد المرضى الحالي
        val currentCount = getCurrentPatientsCount()
        val licenseStatusObj = licenseStatus.value ?: LicenseStatus() // استخدام القيم الافتراضية إذا لم يتم العثور على سجل
        
        return currentCount < licenseStatusObj.trialPatientsLimit
    }

    // تفعيل النسخة الكاملة باستخدام مفتاح التفعيل
    suspend fun activateFullVersion(activationKey: String): Boolean {
        // في هذا التنفيذ البسيط، نقبل أي مفتاح تفعيل غير فارغ
        // في التطبيق الحقيقي، يجب التحقق من صحة المفتاح مقابل خدمة خارجية أو خوارزمية تحقق
        if (activationKey.isNotBlank()) {
            val currentStatus = licenseStatus.value ?: LicenseStatus()
            val updatedStatus = currentStatus.copy(
                isFullVersion = true,
                activationKey = activationKey,
                activationDate = System.currentTimeMillis()
            )
            insertOrUpdateLicenseStatus(updatedStatus)
            return true
        }
        return false
    }

    // تهيئة حالة الترخيص إذا لم تكن موجودة
    suspend fun initializeLicenseStatusIfNeeded() {
        if (licenseStatus.value == null) {
            insertOrUpdateLicenseStatus(LicenseStatus())
        }
    }
}
