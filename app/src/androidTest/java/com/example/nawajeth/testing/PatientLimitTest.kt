package com.example.nawajeth.testing

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.nawajeth.data.db.NawajethDatabase
import com.example.nawajeth.data.db.dao.PatientDao
import com.example.nawajeth.data.db.entity.PatientEntity
import com.example.nawajeth.features.patients.data.repository.PatientRepositoryImpl
import com.example.nawajeth.features.patients.domain.repository.PatientRepository
import com.example.nawajeth.features.settings.domain.model.LicenseStatus
import com.example.nawajeth.features.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class PatientLimitTest {

    private lateinit var database: NawajethDatabase
    private lateinit var patientDao: PatientDao
    private lateinit var patientRepository: PatientRepository
    private lateinit var settingsRepository: SettingsRepository

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = androidx.room.Room.inMemoryDatabaseBuilder(
            context, NawajethDatabase::class.java
        ).allowMainThreadQueries().build()
        
        patientDao = database.patientDao()
        
        // إنشاء mock لمستودع الإعدادات
        settingsRepository = mock(SettingsRepository::class.java)
        
        // إنشاء مستودع المرضى الحقيقي مع mock للإعدادات
        patientRepository = PatientRepositoryImpl(
            patientDao = patientDao,
            dentalWorkDao = database.dentalWorkDao(),
            paymentDao = database.paymentDao()
        )
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testPatientLimit_whenNotActivated() = runBlocking {
        // تهيئة حالة عدم التفعيل
        val licenseStatus = LicenseStatus(isActivated = false, deviceId = "TEST-DEVICE-ID")
        `when`(settingsRepository.getLicenseStatus()).thenReturn(licenseStatus)
        
        // إضافة 10 مرضى (الحد الأقصى للنسخة المجانية)
        for (i in 1..10) {
            val patient = PatientEntity(
                id = i.toLong(),
                name = "Patient $i",
                phone = "123456789$i",
                gender = if (i % 2 == 0) "ذكر" else "أنثى",
                birthDate = System.currentTimeMillis(),
                notes = "ملاحظات للمريض $i",
                createdAt = System.currentTimeMillis()
            )
            patientDao.insertPatient(patient)
        }
        
        // التحقق من عدد المرضى
        val patients = patientDao.getAllPatients().first()
        assertEquals(10, patients.size)
        
        // محاولة إضافة مريض إضافي يجب أن تفشل
        val result = runCatching {
            val newPatient = com.example.nawajeth.features.patients.domain.model.Patient(
                id = 0,
                name = "Patient 11",
                phone = "12345678911",
                gender = "ذكر",
                birthDate = System.currentTimeMillis(),
                notes = "ملاحظات للمريض 11",
                createdAt = System.currentTimeMillis()
            )
            
            // استخدام Use Case مباشرة
            val addPatientUseCase = com.example.nawajeth.features.patients.domain.usecase.AddPatientUseCase(
                patientRepository = patientRepository,
                getLicenseStatusUseCase = com.example.nawajeth.features.patients.domain.usecase.GetLicenseStatusUseCase(
                    settingsRepository = settingsRepository
                )
            )
            
            addPatientUseCase(newPatient)
        }
        
        // التحقق من أن الإضافة فشلت بسبب تجاوز الحد
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull()?.message?.contains("تم الوصول للحد الأقصى") == true)
        
        // التحقق من أن عدد المرضى لا يزال 10
        val patientsAfterAttempt = patientDao.getAllPatients().first()
        assertEquals(10, patientsAfterAttempt.size)
    }

    @Test
    fun testPatientLimit_whenActivated() = runBlocking {
        // تهيئة حالة التفعيل
        val licenseStatus = LicenseStatus(isActivated = true, deviceId = "TEST-DEVICE-ID", activationDate = System.currentTimeMillis())
        `when`(settingsRepository.getLicenseStatus()).thenReturn(licenseStatus)
        
        // إضافة 10 مرضى
        for (i in 1..10) {
            val patient = PatientEntity(
                id = i.toLong(),
                name = "Patient $i",
                phone = "123456789$i",
                gender = if (i % 2 == 0) "ذكر" else "أنثى",
                birthDate = System.currentTimeMillis(),
                notes = "ملاحظات للمريض $i",
                createdAt = System.currentTimeMillis()
            )
            patientDao.insertPatient(patient)
        }
        
        // التحقق من عدد المرضى
        val patients = patientDao.getAllPatients().first()
        assertEquals(10, patients.size)
        
        // محاولة إضافة مريض إضافي يجب أن تنجح لأن التطبيق مفعل
        val result = runCatching {
            val newPatient = com.example.nawajeth.features.patients.domain.model.Patient(
                id = 0,
                name = "Patient 11",
                phone = "12345678911",
                gender = "ذكر",
                birthDate = System.currentTimeMillis(),
                notes = "ملاحظات للمريض 11",
                createdAt = System.currentTimeMillis()
            )
            
            // استخدام Use Case مباشرة
            val addPatientUseCase = com.example.nawajeth.features.patients.domain.usecase.AddPatientUseCase(
                patientRepository = patientRepository,
                getLicenseStatusUseCase = com.example.nawajeth.features.patients.domain.usecase.GetLicenseStatusUseCase(
                    settingsRepository = settingsRepository
                )
            )
            
            addPatientUseCase(newPatient)
        }
        
        // التحقق من أن الإضافة نجحت
        assertTrue(result.isSuccess)
        
        // التحقق من أن عدد المرضى أصبح 11
        val patientsAfterAttempt = patientDao.getAllPatients().first()
        assertEquals(11, patientsAfterAttempt.size)
    }
}
