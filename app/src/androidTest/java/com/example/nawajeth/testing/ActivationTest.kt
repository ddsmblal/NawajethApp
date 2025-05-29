package com.example.nawajeth.testing

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.nawajeth.features.settings.data.repository.SettingsRepositoryImpl
import com.example.nawajeth.features.settings.domain.usecase.ActivateAppUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivationTest {

    @Test
    fun testActivationCodeGeneration() {
        // اختبار خوارزمية توليد رمز التفعيل
        val deviceId = "ABCD-1234-EFGH-5678"
        val expectedCode = "8765-HGFE-4321-DCBA".reversed().map { char ->
            when {
                char.isDigit() -> ((char.digitToInt() + 5) % 10).digitToChar()
                char.isLetter() -> {
                    val base = if (char.isUpperCase()) 'A' else 'a'
                    ((char.code - base.code + 13) % 26 + base.code).toChar()
                }
                else -> char
            }
        }.joinToString("").chunked(4).joinToString("-").uppercase()
        
        // استخدام الدالة الخاصة لتوليد رمز التفعيل
        val generatedCode = generateActivationCode(deviceId)
        
        // التحقق من أن الرمز المتوقع يطابق الرمز المولد
        assertEquals(expectedCode, generatedCode)
    }
    
    @Test
    fun testActivationProcess() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        
        // إنشاء مستودع الإعدادات
        val settingsRepository = SettingsRepositoryImpl(
            appSettingsDao = com.example.nawajeth.data.db.NawajethDatabase.getInstance(context).appSettingsDao(),
            context = context
        )
        
        // الحصول على معرف الجهاز
        val deviceId = settingsRepository.getDeviceId()
        
        // توليد رمز التفعيل المناسب
        val activationCode = generateActivationCode(deviceId)
        
        // إنشاء Use Case للتفعيل
        val activateAppUseCase = ActivateAppUseCase(settingsRepository)
        
        // محاولة التفعيل برمز صحيح
        val result = activateAppUseCase(activationCode)
        
        // التحقق من نجاح التفعيل
        assertTrue(result.isSuccess)
        
        // التحقق من أن التطبيق أصبح مفعلاً
        val licenseStatus = settingsRepository.getLicenseStatus()
        assertTrue(licenseStatus.isActivated)
    }
    
    @Test
    fun testInvalidActivationCode() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        
        // إنشاء مستودع الإعدادات
        val settingsRepository = SettingsRepositoryImpl(
            appSettingsDao = com.example.nawajeth.data.db.NawajethDatabase.getInstance(context).appSettingsDao(),
            context = context
        )
        
        // إنشاء Use Case للتفعيل
        val activateAppUseCase = ActivateAppUseCase(settingsRepository)
        
        // محاولة التفعيل برمز خاطئ
        val result = activateAppUseCase("INVALID-CODE-1234-5678")
        
        // التحقق من فشل التفعيل
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull()?.message?.contains("رمز التفعيل غير صحيح") == true)
    }
    
    // دالة مساعدة لتوليد رمز التفعيل (نسخة من الدالة الموجودة في SettingsRepositoryImpl)
    private fun generateActivationCode(deviceId: String): String {
        return deviceId
            .replace("-", "")
            .reversed()
            .toCharArray()
            .map { char ->
                when {
                    char.isDigit() -> ((char.digitToInt() + 5) % 10).digitToChar()
                    char.isLetter() -> {
                        val base = if (char.isUpperCase()) 'A' else 'a'
                        ((char.code - base.code + 13) % 26 + base.code).toChar()
                    }
                    else -> char
                }
            }
            .joinToString("")
            .chunked(4)
            .joinToString("-")
            .uppercase()
    }
}
