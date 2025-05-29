package com.example.nawajeth.data.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.nawajeth.data.db.dao.ActivityLogDao;
import com.example.nawajeth.data.db.dao.ActivityLogDao_Impl;
import com.example.nawajeth.data.db.dao.AppSettingsDao;
import com.example.nawajeth.data.db.dao.AppSettingsDao_Impl;
import com.example.nawajeth.data.db.dao.DentalConditionDao;
import com.example.nawajeth.data.db.dao.DentalConditionDao_Impl;
import com.example.nawajeth.data.db.dao.DentalSymbolDao;
import com.example.nawajeth.data.db.dao.DentalSymbolDao_Impl;
import com.example.nawajeth.data.db.dao.DentalWorkDao;
import com.example.nawajeth.data.db.dao.DentalWorkDao_Impl;
import com.example.nawajeth.data.db.dao.PatientDao;
import com.example.nawajeth.data.db.dao.PatientDao_Impl;
import com.example.nawajeth.data.db.dao.PaymentDao;
import com.example.nawajeth.data.db.dao.PaymentDao_Impl;
import com.example.nawajeth.data.db.dao.WorkTypeDao;
import com.example.nawajeth.data.db.dao.WorkTypeDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class NawajethDatabase_Impl extends NawajethDatabase {
  private volatile PatientDao _patientDao;

  private volatile DentalWorkDao _dentalWorkDao;

  private volatile PaymentDao _paymentDao;

  private volatile WorkTypeDao _workTypeDao;

  private volatile DentalSymbolDao _dentalSymbolDao;

  private volatile DentalConditionDao _dentalConditionDao;

  private volatile ActivityLogDao _activityLogDao;

  private volatile AppSettingsDao _appSettingsDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `patients` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `age` INTEGER NOT NULL, `gender` TEXT NOT NULL, `phone` TEXT NOT NULL, `notes` TEXT NOT NULL, `lastVisitDate` INTEGER, `totalDue` REAL NOT NULL, `createdAt` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `dental_works` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `patientId` INTEGER NOT NULL, `toothNumber` INTEGER, `workTypeId` INTEGER NOT NULL, `description` TEXT NOT NULL, `cost` REAL NOT NULL, `paidAmount` REAL NOT NULL, `remainingAmount` REAL NOT NULL, `status` TEXT NOT NULL, `startDate` INTEGER NOT NULL, `endDate` INTEGER, `notes` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`patientId`) REFERENCES `patients`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_dental_works_patientId` ON `dental_works` (`patientId`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `payments` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `patientId` INTEGER NOT NULL, `dentalWorkId` INTEGER, `amount` REAL NOT NULL, `paymentDate` INTEGER NOT NULL, `paymentMethod` TEXT NOT NULL, `notes` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`patientId`) REFERENCES `patients`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`dentalWorkId`) REFERENCES `dental_works`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_payments_patientId` ON `payments` (`patientId`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_payments_dentalWorkId` ON `payments` (`dentalWorkId`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `work_types` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `defaultCost` REAL NOT NULL, `color` TEXT NOT NULL, `iconPath` TEXT, `isActive` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `dental_symbols` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `symbolPath` TEXT NOT NULL, `workTypeId` INTEGER, `color` TEXT NOT NULL, `isActive` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`workTypeId`) REFERENCES `work_types`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_dental_symbols_workTypeId` ON `dental_symbols` (`workTypeId`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `dental_conditions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `patientId` INTEGER NOT NULL, `toothNumber` INTEGER, `symbolId` INTEGER, `description` TEXT NOT NULL, `notes` TEXT NOT NULL, `recordDate` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`patientId`) REFERENCES `patients`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_dental_conditions_patientId` ON `dental_conditions` (`patientId`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `activity_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `patientId` INTEGER, `activityType` TEXT NOT NULL, `description` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `userId` TEXT, FOREIGN KEY(`patientId`) REFERENCES `patients`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_activity_logs_patientId` ON `activity_logs` (`patientId`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `app_settings` (`id` INTEGER NOT NULL, `language` TEXT NOT NULL, `theme` TEXT NOT NULL, `backupFrequency` INTEGER NOT NULL, `lastBackupDate` INTEGER, `backupLocation` TEXT, `isActivated` INTEGER NOT NULL, `activationCode` TEXT, `deviceId` TEXT, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '26b60b96fb047a1625d08bfd62c412d0')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `patients`");
        _db.execSQL("DROP TABLE IF EXISTS `dental_works`");
        _db.execSQL("DROP TABLE IF EXISTS `payments`");
        _db.execSQL("DROP TABLE IF EXISTS `work_types`");
        _db.execSQL("DROP TABLE IF EXISTS `dental_symbols`");
        _db.execSQL("DROP TABLE IF EXISTS `dental_conditions`");
        _db.execSQL("DROP TABLE IF EXISTS `activity_logs`");
        _db.execSQL("DROP TABLE IF EXISTS `app_settings`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsPatients = new HashMap<String, TableInfo.Column>(9);
        _columnsPatients.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatients.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatients.put("age", new TableInfo.Column("age", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatients.put("gender", new TableInfo.Column("gender", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatients.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatients.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatients.put("lastVisitDate", new TableInfo.Column("lastVisitDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatients.put("totalDue", new TableInfo.Column("totalDue", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatients.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPatients = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPatients = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPatients = new TableInfo("patients", _columnsPatients, _foreignKeysPatients, _indicesPatients);
        final TableInfo _existingPatients = TableInfo.read(_db, "patients");
        if (! _infoPatients.equals(_existingPatients)) {
          return new RoomOpenHelper.ValidationResult(false, "patients(com.example.nawajeth.data.db.entity.PatientEntity).\n"
                  + " Expected:\n" + _infoPatients + "\n"
                  + " Found:\n" + _existingPatients);
        }
        final HashMap<String, TableInfo.Column> _columnsDentalWorks = new HashMap<String, TableInfo.Column>(13);
        _columnsDentalWorks.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalWorks.put("patientId", new TableInfo.Column("patientId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalWorks.put("toothNumber", new TableInfo.Column("toothNumber", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalWorks.put("workTypeId", new TableInfo.Column("workTypeId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalWorks.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalWorks.put("cost", new TableInfo.Column("cost", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalWorks.put("paidAmount", new TableInfo.Column("paidAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalWorks.put("remainingAmount", new TableInfo.Column("remainingAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalWorks.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalWorks.put("startDate", new TableInfo.Column("startDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalWorks.put("endDate", new TableInfo.Column("endDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalWorks.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalWorks.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDentalWorks = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysDentalWorks.add(new TableInfo.ForeignKey("patients", "CASCADE", "NO ACTION",Arrays.asList("patientId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesDentalWorks = new HashSet<TableInfo.Index>(1);
        _indicesDentalWorks.add(new TableInfo.Index("index_dental_works_patientId", false, Arrays.asList("patientId"), Arrays.asList("ASC")));
        final TableInfo _infoDentalWorks = new TableInfo("dental_works", _columnsDentalWorks, _foreignKeysDentalWorks, _indicesDentalWorks);
        final TableInfo _existingDentalWorks = TableInfo.read(_db, "dental_works");
        if (! _infoDentalWorks.equals(_existingDentalWorks)) {
          return new RoomOpenHelper.ValidationResult(false, "dental_works(com.example.nawajeth.data.db.entity.DentalWorkEntity).\n"
                  + " Expected:\n" + _infoDentalWorks + "\n"
                  + " Found:\n" + _existingDentalWorks);
        }
        final HashMap<String, TableInfo.Column> _columnsPayments = new HashMap<String, TableInfo.Column>(8);
        _columnsPayments.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPayments.put("patientId", new TableInfo.Column("patientId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPayments.put("dentalWorkId", new TableInfo.Column("dentalWorkId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPayments.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPayments.put("paymentDate", new TableInfo.Column("paymentDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPayments.put("paymentMethod", new TableInfo.Column("paymentMethod", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPayments.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPayments.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPayments = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysPayments.add(new TableInfo.ForeignKey("patients", "CASCADE", "NO ACTION",Arrays.asList("patientId"), Arrays.asList("id")));
        _foreignKeysPayments.add(new TableInfo.ForeignKey("dental_works", "SET NULL", "NO ACTION",Arrays.asList("dentalWorkId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesPayments = new HashSet<TableInfo.Index>(2);
        _indicesPayments.add(new TableInfo.Index("index_payments_patientId", false, Arrays.asList("patientId"), Arrays.asList("ASC")));
        _indicesPayments.add(new TableInfo.Index("index_payments_dentalWorkId", false, Arrays.asList("dentalWorkId"), Arrays.asList("ASC")));
        final TableInfo _infoPayments = new TableInfo("payments", _columnsPayments, _foreignKeysPayments, _indicesPayments);
        final TableInfo _existingPayments = TableInfo.read(_db, "payments");
        if (! _infoPayments.equals(_existingPayments)) {
          return new RoomOpenHelper.ValidationResult(false, "payments(com.example.nawajeth.data.db.entity.PaymentEntity).\n"
                  + " Expected:\n" + _infoPayments + "\n"
                  + " Found:\n" + _existingPayments);
        }
        final HashMap<String, TableInfo.Column> _columnsWorkTypes = new HashMap<String, TableInfo.Column>(7);
        _columnsWorkTypes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkTypes.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkTypes.put("defaultCost", new TableInfo.Column("defaultCost", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkTypes.put("color", new TableInfo.Column("color", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkTypes.put("iconPath", new TableInfo.Column("iconPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkTypes.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkTypes.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWorkTypes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWorkTypes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWorkTypes = new TableInfo("work_types", _columnsWorkTypes, _foreignKeysWorkTypes, _indicesWorkTypes);
        final TableInfo _existingWorkTypes = TableInfo.read(_db, "work_types");
        if (! _infoWorkTypes.equals(_existingWorkTypes)) {
          return new RoomOpenHelper.ValidationResult(false, "work_types(com.example.nawajeth.data.db.entity.WorkTypeEntity).\n"
                  + " Expected:\n" + _infoWorkTypes + "\n"
                  + " Found:\n" + _existingWorkTypes);
        }
        final HashMap<String, TableInfo.Column> _columnsDentalSymbols = new HashMap<String, TableInfo.Column>(8);
        _columnsDentalSymbols.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalSymbols.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalSymbols.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalSymbols.put("symbolPath", new TableInfo.Column("symbolPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalSymbols.put("workTypeId", new TableInfo.Column("workTypeId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalSymbols.put("color", new TableInfo.Column("color", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalSymbols.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalSymbols.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDentalSymbols = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysDentalSymbols.add(new TableInfo.ForeignKey("work_types", "SET NULL", "NO ACTION",Arrays.asList("workTypeId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesDentalSymbols = new HashSet<TableInfo.Index>(1);
        _indicesDentalSymbols.add(new TableInfo.Index("index_dental_symbols_workTypeId", false, Arrays.asList("workTypeId"), Arrays.asList("ASC")));
        final TableInfo _infoDentalSymbols = new TableInfo("dental_symbols", _columnsDentalSymbols, _foreignKeysDentalSymbols, _indicesDentalSymbols);
        final TableInfo _existingDentalSymbols = TableInfo.read(_db, "dental_symbols");
        if (! _infoDentalSymbols.equals(_existingDentalSymbols)) {
          return new RoomOpenHelper.ValidationResult(false, "dental_symbols(com.example.nawajeth.data.db.entity.DentalSymbolEntity).\n"
                  + " Expected:\n" + _infoDentalSymbols + "\n"
                  + " Found:\n" + _existingDentalSymbols);
        }
        final HashMap<String, TableInfo.Column> _columnsDentalConditions = new HashMap<String, TableInfo.Column>(8);
        _columnsDentalConditions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalConditions.put("patientId", new TableInfo.Column("patientId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalConditions.put("toothNumber", new TableInfo.Column("toothNumber", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalConditions.put("symbolId", new TableInfo.Column("symbolId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalConditions.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalConditions.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalConditions.put("recordDate", new TableInfo.Column("recordDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDentalConditions.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDentalConditions = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysDentalConditions.add(new TableInfo.ForeignKey("patients", "CASCADE", "NO ACTION",Arrays.asList("patientId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesDentalConditions = new HashSet<TableInfo.Index>(1);
        _indicesDentalConditions.add(new TableInfo.Index("index_dental_conditions_patientId", false, Arrays.asList("patientId"), Arrays.asList("ASC")));
        final TableInfo _infoDentalConditions = new TableInfo("dental_conditions", _columnsDentalConditions, _foreignKeysDentalConditions, _indicesDentalConditions);
        final TableInfo _existingDentalConditions = TableInfo.read(_db, "dental_conditions");
        if (! _infoDentalConditions.equals(_existingDentalConditions)) {
          return new RoomOpenHelper.ValidationResult(false, "dental_conditions(com.example.nawajeth.data.db.entity.DentalConditionEntity).\n"
                  + " Expected:\n" + _infoDentalConditions + "\n"
                  + " Found:\n" + _existingDentalConditions);
        }
        final HashMap<String, TableInfo.Column> _columnsActivityLogs = new HashMap<String, TableInfo.Column>(6);
        _columnsActivityLogs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActivityLogs.put("patientId", new TableInfo.Column("patientId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActivityLogs.put("activityType", new TableInfo.Column("activityType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActivityLogs.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActivityLogs.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActivityLogs.put("userId", new TableInfo.Column("userId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysActivityLogs = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysActivityLogs.add(new TableInfo.ForeignKey("patients", "CASCADE", "NO ACTION",Arrays.asList("patientId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesActivityLogs = new HashSet<TableInfo.Index>(1);
        _indicesActivityLogs.add(new TableInfo.Index("index_activity_logs_patientId", false, Arrays.asList("patientId"), Arrays.asList("ASC")));
        final TableInfo _infoActivityLogs = new TableInfo("activity_logs", _columnsActivityLogs, _foreignKeysActivityLogs, _indicesActivityLogs);
        final TableInfo _existingActivityLogs = TableInfo.read(_db, "activity_logs");
        if (! _infoActivityLogs.equals(_existingActivityLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "activity_logs(com.example.nawajeth.data.db.entity.ActivityLogEntity).\n"
                  + " Expected:\n" + _infoActivityLogs + "\n"
                  + " Found:\n" + _existingActivityLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsAppSettings = new HashMap<String, TableInfo.Column>(10);
        _columnsAppSettings.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppSettings.put("language", new TableInfo.Column("language", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppSettings.put("theme", new TableInfo.Column("theme", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppSettings.put("backupFrequency", new TableInfo.Column("backupFrequency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppSettings.put("lastBackupDate", new TableInfo.Column("lastBackupDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppSettings.put("backupLocation", new TableInfo.Column("backupLocation", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppSettings.put("isActivated", new TableInfo.Column("isActivated", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppSettings.put("activationCode", new TableInfo.Column("activationCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppSettings.put("deviceId", new TableInfo.Column("deviceId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppSettings.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppSettings = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppSettings = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppSettings = new TableInfo("app_settings", _columnsAppSettings, _foreignKeysAppSettings, _indicesAppSettings);
        final TableInfo _existingAppSettings = TableInfo.read(_db, "app_settings");
        if (! _infoAppSettings.equals(_existingAppSettings)) {
          return new RoomOpenHelper.ValidationResult(false, "app_settings(com.example.nawajeth.data.db.entity.AppSettingsEntity).\n"
                  + " Expected:\n" + _infoAppSettings + "\n"
                  + " Found:\n" + _existingAppSettings);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "26b60b96fb047a1625d08bfd62c412d0", "024a0c0ae3aef751eb13ed2af794d921");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "patients","dental_works","payments","work_types","dental_symbols","dental_conditions","activity_logs","app_settings");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `patients`");
      _db.execSQL("DELETE FROM `dental_works`");
      _db.execSQL("DELETE FROM `payments`");
      _db.execSQL("DELETE FROM `work_types`");
      _db.execSQL("DELETE FROM `dental_symbols`");
      _db.execSQL("DELETE FROM `dental_conditions`");
      _db.execSQL("DELETE FROM `activity_logs`");
      _db.execSQL("DELETE FROM `app_settings`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(PatientDao.class, PatientDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DentalWorkDao.class, DentalWorkDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PaymentDao.class, PaymentDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WorkTypeDao.class, WorkTypeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DentalSymbolDao.class, DentalSymbolDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DentalConditionDao.class, DentalConditionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ActivityLogDao.class, ActivityLogDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AppSettingsDao.class, AppSettingsDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public PatientDao patientDao() {
    if (_patientDao != null) {
      return _patientDao;
    } else {
      synchronized(this) {
        if(_patientDao == null) {
          _patientDao = new PatientDao_Impl(this);
        }
        return _patientDao;
      }
    }
  }

  @Override
  public DentalWorkDao dentalWorkDao() {
    if (_dentalWorkDao != null) {
      return _dentalWorkDao;
    } else {
      synchronized(this) {
        if(_dentalWorkDao == null) {
          _dentalWorkDao = new DentalWorkDao_Impl(this);
        }
        return _dentalWorkDao;
      }
    }
  }

  @Override
  public PaymentDao paymentDao() {
    if (_paymentDao != null) {
      return _paymentDao;
    } else {
      synchronized(this) {
        if(_paymentDao == null) {
          _paymentDao = new PaymentDao_Impl(this);
        }
        return _paymentDao;
      }
    }
  }

  @Override
  public WorkTypeDao workTypeDao() {
    if (_workTypeDao != null) {
      return _workTypeDao;
    } else {
      synchronized(this) {
        if(_workTypeDao == null) {
          _workTypeDao = new WorkTypeDao_Impl(this);
        }
        return _workTypeDao;
      }
    }
  }

  @Override
  public DentalSymbolDao dentalSymbolDao() {
    if (_dentalSymbolDao != null) {
      return _dentalSymbolDao;
    } else {
      synchronized(this) {
        if(_dentalSymbolDao == null) {
          _dentalSymbolDao = new DentalSymbolDao_Impl(this);
        }
        return _dentalSymbolDao;
      }
    }
  }

  @Override
  public DentalConditionDao dentalConditionDao() {
    if (_dentalConditionDao != null) {
      return _dentalConditionDao;
    } else {
      synchronized(this) {
        if(_dentalConditionDao == null) {
          _dentalConditionDao = new DentalConditionDao_Impl(this);
        }
        return _dentalConditionDao;
      }
    }
  }

  @Override
  public ActivityLogDao activityLogDao() {
    if (_activityLogDao != null) {
      return _activityLogDao;
    } else {
      synchronized(this) {
        if(_activityLogDao == null) {
          _activityLogDao = new ActivityLogDao_Impl(this);
        }
        return _activityLogDao;
      }
    }
  }

  @Override
  public AppSettingsDao appSettingsDao() {
    if (_appSettingsDao != null) {
      return _appSettingsDao;
    } else {
      synchronized(this) {
        if(_appSettingsDao == null) {
          _appSettingsDao = new AppSettingsDao_Impl(this);
        }
        return _appSettingsDao;
      }
    }
  }
}
