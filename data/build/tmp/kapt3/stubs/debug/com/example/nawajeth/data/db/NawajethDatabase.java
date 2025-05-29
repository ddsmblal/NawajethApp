package com.example.nawajeth.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.nawajeth.data.db.dao.*;
import com.example.nawajeth.data.db.entity.*;
import com.example.nawajeth.data.db.util.Converters;

@androidx.room.TypeConverters(value = {com.example.nawajeth.data.db.util.Converters.class})
@androidx.room.Database(entities = {com.example.nawajeth.data.db.entity.PatientEntity.class, com.example.nawajeth.data.db.entity.DentalWorkEntity.class, com.example.nawajeth.data.db.entity.PaymentEntity.class, com.example.nawajeth.data.db.entity.WorkTypeEntity.class, com.example.nawajeth.data.db.entity.DentalSymbolEntity.class, com.example.nawajeth.data.db.entity.DentalConditionEntity.class, com.example.nawajeth.data.db.entity.ActivityLogEntity.class, com.example.nawajeth.data.db.entity.AppSettingsEntity.class}, version = 1, exportSchema = true)
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&\u00a8\u0006\u0013"}, d2 = {"Lcom/example/nawajeth/data/db/NawajethDatabase;", "Landroidx/room/RoomDatabase;", "()V", "activityLogDao", "Lcom/example/nawajeth/data/db/dao/ActivityLogDao;", "appSettingsDao", "Lcom/example/nawajeth/data/db/dao/AppSettingsDao;", "dentalConditionDao", "Lcom/example/nawajeth/data/db/dao/DentalConditionDao;", "dentalSymbolDao", "Lcom/example/nawajeth/data/db/dao/DentalSymbolDao;", "dentalWorkDao", "Lcom/example/nawajeth/data/db/dao/DentalWorkDao;", "patientDao", "Lcom/example/nawajeth/data/db/dao/PatientDao;", "paymentDao", "Lcom/example/nawajeth/data/db/dao/PaymentDao;", "workTypeDao", "Lcom/example/nawajeth/data/db/dao/WorkTypeDao;", "data_debug"})
public abstract class NawajethDatabase extends androidx.room.RoomDatabase {
    
    public NawajethDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.nawajeth.data.db.dao.PatientDao patientDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.nawajeth.data.db.dao.DentalWorkDao dentalWorkDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.nawajeth.data.db.dao.PaymentDao paymentDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.nawajeth.data.db.dao.WorkTypeDao workTypeDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.nawajeth.data.db.dao.DentalSymbolDao dentalSymbolDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.nawajeth.data.db.dao.DentalConditionDao dentalConditionDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.nawajeth.data.db.dao.ActivityLogDao activityLogDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.nawajeth.data.db.dao.AppSettingsDao appSettingsDao();
}