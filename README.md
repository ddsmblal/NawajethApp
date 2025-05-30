# دليل استخدام مشروع Nawajeth - Dental Clinic Manager

## مقدمة

مرحباً بك في مشروع Nawajeth - Dental Clinic Manager، وهو تطبيق إدارة محلي (offline) يعمل على نظام Android، مخصص لإدارة العيادات السنية بشكل شامل ومهني.

هذا الدليل سيساعدك على فتح المشروع في Android Studio وتشغيله بنجاح.

## متطلبات النظام

- Android Studio Hedgehog (2023.1.1) أو أحدث
- JDK 17 أو أحدث
- الحد الأدنى لإصدار Android: API 23 (Android 6.0 Marshmallow)

## فتح المشروع

1. قم بفك ضغط ملف `nawajeth_android_project.zip` في المجلد الذي تريده
2. افتح Android Studio
3. اختر "Open" من القائمة الرئيسية
4. تصفح إلى المجلد الذي قمت بفك الضغط فيه واختر مجلد `nawajeth_android_project`
5. انقر على "Open"
6. انتظر حتى يكتمل استيراد المشروع وتحميل جميع التبعيات (قد يستغرق ذلك بضع دقائق)

## بنية المشروع

المشروع مبني بهيكلية معمارية نظيفة (Clean Architecture) مع تقسيم الوحدات كالتالي:

- **app**: الوحدة الرئيسية التي تحتوي على نقطة الدخول للتطبيق
- **core**: تحتوي على المكونات المشتركة والأدوات المساعدة
- **data**: طبقة البيانات مع قاعدة البيانات Room والمستودعات
- **domain**: طبقة المنطق التجاري مع Use Cases والنماذج
- **features**: الميزات المختلفة للتطبيق (المرضى، مخطط الأسنان، الأعمال السنية، الدفعات، التقارير، الإعدادات)

## تشغيل التطبيق

1. قم بتوصيل جهاز Android حقيقي أو تشغيل محاكي Android
2. انقر على زر "Run" (الزر الأخضر) في شريط الأدوات
3. اختر الجهاز الذي تريد تشغيل التطبيق عليه
4. انتظر حتى يتم بناء وتثبيت التطبيق على الجهاز

## ميزات التطبيق

- إدارة المرضى (إضافة، تعديل، حذف، بحث)
- مخطط تفاعلي للأسنان مع إمكانية الرسم اليدوي
- إدارة الأعمال السنية والدفعات
- تخصيص الرموز والأسعار
- تقارير دورية وشهرية
- نسخ احتياطي واستعادة البيانات
- دعم اللغتين العربية والإنجليزية
- دعم المظهر الفاتح والداكن

## نظام التفعيل

التطبيق يأتي بنسخة تجريبية محدودة بـ 10 مرضى كحد أقصى. لتفعيل النسخة الكاملة:

1. انتقل إلى شاشة الإعدادات ثم "تفعيل التطبيق"
2. انسخ رقم الجهاز الظاهر وأرسله للمطور
3. أدخل رمز التفعيل الذي ستحصل عليه من المطور

## الاختبارات

المشروع يحتوي على مجموعة من الاختبارات الآلية للتحقق من صحة الوظائف الأساسية:

- اختبارات تقييد عدد المرضى في النسخة المجانية
- اختبارات نظام التفعيل
- اختبارات واجهة المستخدم

يمكن تشغيل الاختبارات من خلال النقر بزر الماوس الأيمن على مجلد `androidTest` واختيار "Run Tests"

## ملاحظات إضافية

- التطبيق يعمل بشكل كامل بدون اتصال بالإنترنت
- حجم التطبيق النهائي أقل من 30 ميجابايت
- التطبيق قابل للتوسع مستقبلاً (مثل المزامنة السحابية أو الطباعة)

## الدعم الفني

إذا واجهتك أي مشكلة أو كان لديك أي استفسار، يرجى التواصل مع فريق الدعم الفني.
# NawajethApp
