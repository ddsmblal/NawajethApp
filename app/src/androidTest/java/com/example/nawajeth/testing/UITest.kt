package com.example.nawajeth.testing

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nawajeth.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testNavigationFlow() {
        // التحقق من ظهور الشاشة الرئيسية
        composeTestRule.onNodeWithText("Nawajeth").assertIsDisplayed()
        
        // الانتقال إلى شاشة المرضى
        composeTestRule.onNodeWithText("المرضى").performClick()
        
        // التحقق من ظهور شاشة المرضى
        composeTestRule.onNodeWithText("قائمة المرضى").assertIsDisplayed()
        
        // الضغط على زر إضافة مريض جديد
        composeTestRule.onNodeWithContentDescription("إضافة مريض جديد").performClick()
        
        // التحقق من ظهور شاشة إضافة مريض
        composeTestRule.onNodeWithText("إضافة مريض جديد").assertIsDisplayed()
        
        // إدخال بيانات المريض
        composeTestRule.onNodeWithText("الاسم").performTextInput("أحمد محمد")
        composeTestRule.onNodeWithText("رقم الهاتف").performTextInput("0501234567")
        
        // اختيار الجنس
        composeTestRule.onNodeWithText("ذكر").performClick()
        
        // حفظ المريض
        composeTestRule.onNodeWithText("حفظ").performClick()
        
        // التحقق من العودة إلى شاشة قائمة المرضى
        composeTestRule.onNodeWithText("قائمة المرضى").assertIsDisplayed()
        
        // التحقق من ظهور المريض الجديد في القائمة
        composeTestRule.onNodeWithText("أحمد محمد").assertIsDisplayed()
    }

    @Test
    fun testDentalChartInteraction() {
        // الانتقال إلى شاشة المرضى
        composeTestRule.onNodeWithText("المرضى").performClick()
        
        // اختيار مريض من القائمة
        composeTestRule.onNodeWithText("أحمد محمد").performClick()
        
        // الانتقال إلى مخطط الأسنان
        composeTestRule.onNodeWithText("مخطط الأسنان").performClick()
        
        // التحقق من ظهور شاشة مخطط الأسنان
        composeTestRule.onNodeWithText("مخطط الأسنان").assertIsDisplayed()
        
        // اختيار رمز من قائمة الرموز
        composeTestRule.onAllNodesWithContentDescription("رمز سني")[0].performClick()
        
        // النقر على سن في المخطط
        composeTestRule.onNodeWithTag("dental_chart_canvas").performTouchInput {
            click(center)
        }
        
        // التحقق من ظهور الرمز على السن
        // (هذا اختبار بسيط، في الواقع يجب التحقق من تغير حالة ViewModel)
    }

    @Test
    fun testActivationScreen() {
        // الانتقال إلى شاشة الإعدادات
        composeTestRule.onNodeWithText("الإعدادات").performClick()
        
        // الانتقال إلى شاشة التفعيل
        composeTestRule.onNodeWithText("تفعيل التطبيق").performClick()
        
        // التحقق من ظهور شاشة التفعيل
        composeTestRule.onNodeWithText("تفعيل التطبيق").assertIsDisplayed()
        
        // التحقق من ظهور رقم الجهاز
        composeTestRule.onNodeWithContentDescription("نسخ رقم الجهاز").assertIsDisplayed()
        
        // إدخال رمز تفعيل
        composeTestRule.onNodeWithText("رمز التفعيل").performTextInput("ABCD-1234-EFGH-5678")
        
        // الضغط على زر التفعيل
        composeTestRule.onNodeWithText("تفعيل التطبيق").performClick()
    }
}
