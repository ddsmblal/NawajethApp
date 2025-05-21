package com.manus.nawajethapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manus.nawajethapp.data.local.AppDatabase
import com.manus.nawajethapp.data.model.LicenseStatus
import com.manus.nawajethapp.data.repository.LicenseRepository
import kotlinx.coroutines.launch

/**
 * ViewModel لإدارة حالة الترخيص والنسخة التجريبية
 */
class LicenseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LicenseRepository
    val licenseStatus: LiveData<LicenseStatus?>

    private val _activationResult = MutableLiveData<Boolean>()
    val activationResult: LiveData<Boolean> = _activationResult

    private val _canAddPatient = MutableLiveData<Boolean>()
    val canAddPatient: LiveData<Boolean> = _canAddPatient

    private val _patientsCount = MutableLiveData<Int>()
    val patientsCount: LiveData<Int> = _patientsCount

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    init {
        val licenseStatusDao = AppDatabase.getDatabase(application).licenseStatusDao()
        repository = LicenseRepository(licenseStatusDao)
        licenseStatus = repository.licenseStatus

        // تهيئة حالة الترخيص عند بدء التطبيق إذا لم تكن موجودة
        viewModelScope.launch {
            repository.initializeLicenseStatusIfNeeded()
            updatePatientsCount()
            checkCanAddPatient()
        }
    }

    /**
     * تحديث عدد المرضى الحالي
     */
    fun updatePatientsCount() {
        viewModelScope.launch {
            _patientsCount.value = repository.getCurrentPatientsCount()
        }
    }

    /**
     * التحقق مما إذا كان يمكن إضافة مريض جديد
     */
    fun checkCanAddPatient() {
        viewModelScope.launch {
            _canAddPatient.value = repository.canAddNewPatient()
        }
    }

    /**
     * تفعيل النسخة الكاملة باستخدام مفتاح التفعيل
     */
    fun activateFullVersion(activationKey: String) {
        viewModelScope.launch {
            val result = repository.activateFullVersion(activationKey)
            _activationResult.value = result
            
            if (result) {
                _toastMessage.value = "تم تفعيل النسخة الكاملة بنجاح!"
                checkCanAddPatient() // تحديث حالة إمكانية إضافة مرضى جدد
            } else {
                _toastMessage.value = "فشل التفعيل. يرجى التحقق من مفتاح التفعيل."
            }
        }
    }

    /**
     * الحصول على عدد المرضى المتبقي في النسخة التجريبية
     */
    fun getRemainingPatientsCount(): Int {
        val currentCount = _patientsCount.value ?: 0
        val limit = licenseStatus.value?.trialPatientsLimit ?: 10
        return (limit - currentCount).coerceAtLeast(0) // لا يمكن أن يكون أقل من صفر
    }

    /**
     * مسح رسالة التنبيه
     */
    fun clearToastMessage() {
        _toastMessage.value = ""
    }
}
