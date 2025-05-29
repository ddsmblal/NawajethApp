package com.example.nawajeth.data.db.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.nawajeth.data.db.entity.AppSettingsEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppSettingsDao_Impl implements AppSettingsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AppSettingsEntity> __insertionAdapterOfAppSettingsEntity;

  private final EntityDeletionOrUpdateAdapter<AppSettingsEntity> __updateAdapterOfAppSettingsEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateLanguage;

  private final SharedSQLiteStatement __preparedStmtOfUpdateTheme;

  private final SharedSQLiteStatement __preparedStmtOfUpdateBackupFrequency;

  private final SharedSQLiteStatement __preparedStmtOfUpdateLastBackupDate;

  private final SharedSQLiteStatement __preparedStmtOfUpdateActivationStatus;

  private final SharedSQLiteStatement __preparedStmtOfUpdateActivationCode;

  public AppSettingsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAppSettingsEntity = new EntityInsertionAdapter<AppSettingsEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `app_settings` (`id`,`language`,`theme`,`backupFrequency`,`lastBackupDate`,`backupLocation`,`isActivated`,`activationCode`,`deviceId`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AppSettingsEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getLanguage() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLanguage());
        }
        if (value.getTheme() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTheme());
        }
        stmt.bindLong(4, value.getBackupFrequency());
        if (value.getLastBackupDate() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.getLastBackupDate());
        }
        if (value.getBackupLocation() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getBackupLocation());
        }
        final int _tmp = value.isActivated() ? 1 : 0;
        stmt.bindLong(7, _tmp);
        if (value.getActivationCode() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getActivationCode());
        }
        if (value.getDeviceId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getDeviceId());
        }
        stmt.bindLong(10, value.getUpdatedAt());
      }
    };
    this.__updateAdapterOfAppSettingsEntity = new EntityDeletionOrUpdateAdapter<AppSettingsEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `app_settings` SET `id` = ?,`language` = ?,`theme` = ?,`backupFrequency` = ?,`lastBackupDate` = ?,`backupLocation` = ?,`isActivated` = ?,`activationCode` = ?,`deviceId` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AppSettingsEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getLanguage() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLanguage());
        }
        if (value.getTheme() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTheme());
        }
        stmt.bindLong(4, value.getBackupFrequency());
        if (value.getLastBackupDate() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.getLastBackupDate());
        }
        if (value.getBackupLocation() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getBackupLocation());
        }
        final int _tmp = value.isActivated() ? 1 : 0;
        stmt.bindLong(7, _tmp);
        if (value.getActivationCode() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getActivationCode());
        }
        if (value.getDeviceId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getDeviceId());
        }
        stmt.bindLong(10, value.getUpdatedAt());
        stmt.bindLong(11, value.getId());
      }
    };
    this.__preparedStmtOfUpdateLanguage = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE app_settings SET language = ? WHERE id = 1";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateTheme = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE app_settings SET theme = ? WHERE id = 1";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateBackupFrequency = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE app_settings SET backupFrequency = ? WHERE id = 1";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateLastBackupDate = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE app_settings SET lastBackupDate = ? WHERE id = 1";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateActivationStatus = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE app_settings SET isActivated = ? WHERE id = 1";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateActivationCode = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE app_settings SET activationCode = ? WHERE id = 1";
        return _query;
      }
    };
  }

  @Override
  public Object insertAppSettings(final AppSettingsEntity appSettings,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAppSettingsEntity.insert(appSettings);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateAppSettings(final AppSettingsEntity appSettings,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAppSettingsEntity.handle(appSettings);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateLanguage(final String language,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateLanguage.acquire();
        int _argIndex = 1;
        if (language == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, language);
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdateLanguage.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object updateTheme(final String theme, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateTheme.acquire();
        int _argIndex = 1;
        if (theme == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, theme);
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdateTheme.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object updateBackupFrequency(final int frequency,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateBackupFrequency.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, frequency);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdateBackupFrequency.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object updateLastBackupDate(final long date,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateLastBackupDate.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, date);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdateLastBackupDate.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object updateActivationStatus(final boolean isActivated,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateActivationStatus.acquire();
        int _argIndex = 1;
        final int _tmp = isActivated ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdateActivationStatus.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object updateActivationCode(final String code,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateActivationCode.acquire();
        int _argIndex = 1;
        if (code == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, code);
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdateActivationCode.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<AppSettingsEntity> getAppSettings() {
    final String _sql = "SELECT * FROM app_settings WHERE id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"app_settings"}, new Callable<AppSettingsEntity>() {
      @Override
      public AppSettingsEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfTheme = CursorUtil.getColumnIndexOrThrow(_cursor, "theme");
          final int _cursorIndexOfBackupFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "backupFrequency");
          final int _cursorIndexOfLastBackupDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastBackupDate");
          final int _cursorIndexOfBackupLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "backupLocation");
          final int _cursorIndexOfIsActivated = CursorUtil.getColumnIndexOrThrow(_cursor, "isActivated");
          final int _cursorIndexOfActivationCode = CursorUtil.getColumnIndexOrThrow(_cursor, "activationCode");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final AppSettingsEntity _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final String _tmpTheme;
            if (_cursor.isNull(_cursorIndexOfTheme)) {
              _tmpTheme = null;
            } else {
              _tmpTheme = _cursor.getString(_cursorIndexOfTheme);
            }
            final int _tmpBackupFrequency;
            _tmpBackupFrequency = _cursor.getInt(_cursorIndexOfBackupFrequency);
            final Long _tmpLastBackupDate;
            if (_cursor.isNull(_cursorIndexOfLastBackupDate)) {
              _tmpLastBackupDate = null;
            } else {
              _tmpLastBackupDate = _cursor.getLong(_cursorIndexOfLastBackupDate);
            }
            final String _tmpBackupLocation;
            if (_cursor.isNull(_cursorIndexOfBackupLocation)) {
              _tmpBackupLocation = null;
            } else {
              _tmpBackupLocation = _cursor.getString(_cursorIndexOfBackupLocation);
            }
            final boolean _tmpIsActivated;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActivated);
            _tmpIsActivated = _tmp != 0;
            final String _tmpActivationCode;
            if (_cursor.isNull(_cursorIndexOfActivationCode)) {
              _tmpActivationCode = null;
            } else {
              _tmpActivationCode = _cursor.getString(_cursorIndexOfActivationCode);
            }
            final String _tmpDeviceId;
            if (_cursor.isNull(_cursorIndexOfDeviceId)) {
              _tmpDeviceId = null;
            } else {
              _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new AppSettingsEntity(_tmpId,_tmpLanguage,_tmpTheme,_tmpBackupFrequency,_tmpLastBackupDate,_tmpBackupLocation,_tmpIsActivated,_tmpActivationCode,_tmpDeviceId,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
