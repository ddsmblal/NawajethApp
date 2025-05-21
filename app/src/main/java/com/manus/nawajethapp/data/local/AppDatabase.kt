package com.manus.nawajethapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.manus.nawajethapp.data.model.LicenseStatus
import com.manus.nawajethapp.data.model.MedicalWorkTemplate
import com.manus.nawajethapp.data.model.Patient
import com.manus.nawajethapp.data.model.PhotographicImage
import com.manus.nawajethapp.data.model.TreatmentSession
import com.manus.nawajethapp.data.model.TreatmentTask
import com.manus.nawajethapp.data.model.XrayImage
import com.manus.nawajethapp.util.Converters

@Database(
    entities = [
        Patient::class,
        TreatmentSession::class,
        TreatmentTask::class,
        XrayImage::class,
        PhotographicImage::class,
        MedicalWorkTemplate::class,
        LicenseStatus::class // إضافة كيان حالة الترخيص
    ],
    version = 2, // زيادة إصدار قاعدة البيانات بسبب التغييرات الهيكلية
    exportSchema = false
)
@TypeConverters(Converters::class)
public abstract class AppDatabase : RoomDatabase() {

    abstract fun patientDao(): PatientDao
    abstract fun treatmentSessionDao(): TreatmentSessionDao
    abstract fun treatmentTaskDao(): TreatmentTaskDao
    abstract fun xrayImageDao(): XrayImageDao
    abstract fun photographicImageDao(): PhotographicImageDao
    abstract fun medicalWorkTemplateDao(): MedicalWorkTemplateDao
    abstract fun licenseStatusDao(): LicenseStatusDao // إضافة DAO لحالة الترخيص

    companion object {
        // اسم قاعدة البيانات
        const val DATABASE_NAME = "nawajeth_database"
        
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                // إضافة استراتيجية الترحيل التدميرية للتبسيط في مرحلة التطوير
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
