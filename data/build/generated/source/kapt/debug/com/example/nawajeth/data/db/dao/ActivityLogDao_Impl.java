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
import com.example.nawajeth.data.db.entity.ActivityLogEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ActivityLogDao_Impl implements ActivityLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ActivityLogEntity> __insertionAdapterOfActivityLogEntity;

  private final EntityDeletionOrUpdateAdapter<ActivityLogEntity> __deletionAdapterOfActivityLogEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteActivityLogsBefore;

  public ActivityLogDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfActivityLogEntity = new EntityInsertionAdapter<ActivityLogEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `activity_logs` (`id`,`patientId`,`activityType`,`description`,`timestamp`,`userId`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ActivityLogEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getPatientId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getPatientId());
        }
        if (value.getActivityType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getActivityType());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        stmt.bindLong(5, value.getTimestamp());
        if (value.getUserId() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getUserId());
        }
      }
    };
    this.__deletionAdapterOfActivityLogEntity = new EntityDeletionOrUpdateAdapter<ActivityLogEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `activity_logs` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ActivityLogEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfDeleteActivityLogsBefore = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM activity_logs WHERE timestamp < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertActivityLog(final ActivityLogEntity activityLog,
      final Continuation<? super Long> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfActivityLogEntity.insertAndReturnId(activityLog);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteActivityLog(final ActivityLogEntity activityLog,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfActivityLogEntity.handle(activityLog);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteActivityLogsBefore(final long timestamp,
      final Continuation<? super Integer> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteActivityLogsBefore.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        __db.beginTransaction();
        try {
          final java.lang.Integer _result = _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteActivityLogsBefore.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<ActivityLogEntity>> getAllActivityLogs() {
    final String _sql = "SELECT * FROM activity_logs ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"activity_logs"}, new Callable<List<ActivityLogEntity>>() {
      @Override
      public List<ActivityLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
          final int _cursorIndexOfActivityType = CursorUtil.getColumnIndexOrThrow(_cursor, "activityType");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final List<ActivityLogEntity> _result = new ArrayList<ActivityLogEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ActivityLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Long _tmpPatientId;
            if (_cursor.isNull(_cursorIndexOfPatientId)) {
              _tmpPatientId = null;
            } else {
              _tmpPatientId = _cursor.getLong(_cursorIndexOfPatientId);
            }
            final String _tmpActivityType;
            if (_cursor.isNull(_cursorIndexOfActivityType)) {
              _tmpActivityType = null;
            } else {
              _tmpActivityType = _cursor.getString(_cursorIndexOfActivityType);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            _item = new ActivityLogEntity(_tmpId,_tmpPatientId,_tmpActivityType,_tmpDescription,_tmpTimestamp,_tmpUserId);
            _result.add(_item);
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

  @Override
  public Flow<List<ActivityLogEntity>> getActivityLogsByPatientId(final long patientId) {
    final String _sql = "SELECT * FROM activity_logs WHERE patientId = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, patientId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"activity_logs"}, new Callable<List<ActivityLogEntity>>() {
      @Override
      public List<ActivityLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
          final int _cursorIndexOfActivityType = CursorUtil.getColumnIndexOrThrow(_cursor, "activityType");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final List<ActivityLogEntity> _result = new ArrayList<ActivityLogEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ActivityLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Long _tmpPatientId;
            if (_cursor.isNull(_cursorIndexOfPatientId)) {
              _tmpPatientId = null;
            } else {
              _tmpPatientId = _cursor.getLong(_cursorIndexOfPatientId);
            }
            final String _tmpActivityType;
            if (_cursor.isNull(_cursorIndexOfActivityType)) {
              _tmpActivityType = null;
            } else {
              _tmpActivityType = _cursor.getString(_cursorIndexOfActivityType);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            _item = new ActivityLogEntity(_tmpId,_tmpPatientId,_tmpActivityType,_tmpDescription,_tmpTimestamp,_tmpUserId);
            _result.add(_item);
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

  @Override
  public Flow<List<ActivityLogEntity>> getActivityLogsByType(final String activityType) {
    final String _sql = "SELECT * FROM activity_logs WHERE activityType = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (activityType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, activityType);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[]{"activity_logs"}, new Callable<List<ActivityLogEntity>>() {
      @Override
      public List<ActivityLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
          final int _cursorIndexOfActivityType = CursorUtil.getColumnIndexOrThrow(_cursor, "activityType");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final List<ActivityLogEntity> _result = new ArrayList<ActivityLogEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ActivityLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Long _tmpPatientId;
            if (_cursor.isNull(_cursorIndexOfPatientId)) {
              _tmpPatientId = null;
            } else {
              _tmpPatientId = _cursor.getLong(_cursorIndexOfPatientId);
            }
            final String _tmpActivityType;
            if (_cursor.isNull(_cursorIndexOfActivityType)) {
              _tmpActivityType = null;
            } else {
              _tmpActivityType = _cursor.getString(_cursorIndexOfActivityType);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            _item = new ActivityLogEntity(_tmpId,_tmpPatientId,_tmpActivityType,_tmpDescription,_tmpTimestamp,_tmpUserId);
            _result.add(_item);
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

  @Override
  public Flow<List<ActivityLogEntity>> getRecentActivityLogs(final int limit) {
    final String _sql = "SELECT * FROM activity_logs ORDER BY timestamp DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"activity_logs"}, new Callable<List<ActivityLogEntity>>() {
      @Override
      public List<ActivityLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
          final int _cursorIndexOfActivityType = CursorUtil.getColumnIndexOrThrow(_cursor, "activityType");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final List<ActivityLogEntity> _result = new ArrayList<ActivityLogEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ActivityLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Long _tmpPatientId;
            if (_cursor.isNull(_cursorIndexOfPatientId)) {
              _tmpPatientId = null;
            } else {
              _tmpPatientId = _cursor.getLong(_cursorIndexOfPatientId);
            }
            final String _tmpActivityType;
            if (_cursor.isNull(_cursorIndexOfActivityType)) {
              _tmpActivityType = null;
            } else {
              _tmpActivityType = _cursor.getString(_cursorIndexOfActivityType);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            _item = new ActivityLogEntity(_tmpId,_tmpPatientId,_tmpActivityType,_tmpDescription,_tmpTimestamp,_tmpUserId);
            _result.add(_item);
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
