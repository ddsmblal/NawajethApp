package com.example.nawajeth.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nawajeth.data.db.dao.*
import com.example.nawajeth.data.db.entity.*
import com.example.nawajeth.data.db.util.Converters

@Database(
    entities = [
        PatientEntity::class,
        DentalWorkEntity::class,
        PaymentEntity::class,
        WorkTypeEntity::class,
        DentalSymbolEntity::class,
        DentalConditionEntity::class,
        ActivityLogEntity::class,
        AppSettingsEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class NawajethDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao
    abstract fun dentalWorkDao(): DentalWorkDao
    abstract fun paymentDao(): PaymentDao
    abstract fun workTypeDao(): WorkTypeDao
    abstract fun dentalSymbolDao(): DentalSymbolDao
    abstract fun dentalConditionDao(): DentalConditionDao
    abstract fun activityLogDao(): ActivityLogDao
    abstract fun appSettingsDao(): AppSettingsDao
}
