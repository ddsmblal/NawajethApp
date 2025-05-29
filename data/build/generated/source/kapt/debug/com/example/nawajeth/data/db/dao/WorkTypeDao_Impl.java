package com.example.nawajeth.data.db.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.nawajeth.data.db.entity.WorkTypeEntity;
import java.lang.Class;
import java.lang.Exception;
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
public final class WorkTypeDao_Impl implements WorkTypeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WorkTypeEntity> __insertionAdapterOfWorkTypeEntity;

  private final EntityDeletionOrUpdateAdapter<WorkTypeEntity> __deletionAdapterOfWorkTypeEntity;

  private final EntityDeletionOrUpdateAdapter<WorkTypeEntity> __updateAdapterOfWorkTypeEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteWorkTypeById;

  public WorkTypeDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWorkTypeEntity = new EntityInsertionAdapter<WorkTypeEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `work_types` (`id`,`name`,`defaultCost`,`color`,`iconPath`,`isActive`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WorkTypeEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindDouble(3, value.getDefaultCost());
        if (value.getColor() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getColor());
        }
        if (value.getIconPath() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getIconPath());
        }
        final int _tmp = value.isActive() ? 1 : 0;
        stmt.bindLong(6, _tmp);
        stmt.bindLong(7, value.getCreatedAt());
      }
    };
    this.__deletionAdapterOfWorkTypeEntity = new EntityDeletionOrUpdateAdapter<WorkTypeEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `work_types` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WorkTypeEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfWorkTypeEntity = new EntityDeletionOrUpdateAdapter<WorkTypeEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `work_types` SET `id` = ?,`name` = ?,`defaultCost` = ?,`color` = ?,`iconPath` = ?,`isActive` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WorkTypeEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindDouble(3, value.getDefaultCost());
        if (value.getColor() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getColor());
        }
        if (value.getIconPath() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getIconPath());
        }
        final int _tmp = value.isActive() ? 1 : 0;
        stmt.bindLong(6, _tmp);
        stmt.bindLong(7, value.getCreatedAt());
        stmt.bindLong(8, value.getId());
      }
    };
    this.__preparedStmtOfDeleteWorkTypeById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM work_types WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertWorkType(final WorkTypeEntity workType,
      final Continuation<? super Long> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfWorkTypeEntity.insertAndReturnId(workType);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteWorkType(final WorkTypeEntity workType,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfWorkTypeEntity.handle(workType);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateWorkType(final WorkTypeEntity workType,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfWorkTypeEntity.handle(workType);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteWorkTypeById(final long workTypeId,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteWorkTypeById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, workTypeId);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteWorkTypeById.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<WorkTypeEntity>> getAllActiveWorkTypes() {
    final String _sql = "SELECT * FROM work_types WHERE isActive = 1 ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"work_types"}, new Callable<List<WorkTypeEntity>>() {
      @Override
      public List<WorkTypeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDefaultCost = CursorUtil.getColumnIndexOrThrow(_cursor, "defaultCost");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIconPath = CursorUtil.getColumnIndexOrThrow(_cursor, "iconPath");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<WorkTypeEntity> _result = new ArrayList<WorkTypeEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final WorkTypeEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final double _tmpDefaultCost;
            _tmpDefaultCost = _cursor.getDouble(_cursorIndexOfDefaultCost);
            final String _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getString(_cursorIndexOfColor);
            }
            final String _tmpIconPath;
            if (_cursor.isNull(_cursorIndexOfIconPath)) {
              _tmpIconPath = null;
            } else {
              _tmpIconPath = _cursor.getString(_cursorIndexOfIconPath);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new WorkTypeEntity(_tmpId,_tmpName,_tmpDefaultCost,_tmpColor,_tmpIconPath,_tmpIsActive,_tmpCreatedAt);
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
  public Flow<List<WorkTypeEntity>> getAllWorkTypes() {
    final String _sql = "SELECT * FROM work_types ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"work_types"}, new Callable<List<WorkTypeEntity>>() {
      @Override
      public List<WorkTypeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDefaultCost = CursorUtil.getColumnIndexOrThrow(_cursor, "defaultCost");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIconPath = CursorUtil.getColumnIndexOrThrow(_cursor, "iconPath");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<WorkTypeEntity> _result = new ArrayList<WorkTypeEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final WorkTypeEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final double _tmpDefaultCost;
            _tmpDefaultCost = _cursor.getDouble(_cursorIndexOfDefaultCost);
            final String _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getString(_cursorIndexOfColor);
            }
            final String _tmpIconPath;
            if (_cursor.isNull(_cursorIndexOfIconPath)) {
              _tmpIconPath = null;
            } else {
              _tmpIconPath = _cursor.getString(_cursorIndexOfIconPath);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new WorkTypeEntity(_tmpId,_tmpName,_tmpDefaultCost,_tmpColor,_tmpIconPath,_tmpIsActive,_tmpCreatedAt);
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
  public Object getWorkTypeById(final long workTypeId,
      final Continuation<? super WorkTypeEntity> continuation) {
    final String _sql = "SELECT * FROM work_types WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, workTypeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WorkTypeEntity>() {
      @Override
      public WorkTypeEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDefaultCost = CursorUtil.getColumnIndexOrThrow(_cursor, "defaultCost");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIconPath = CursorUtil.getColumnIndexOrThrow(_cursor, "iconPath");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final WorkTypeEntity _result;
          if(_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final double _tmpDefaultCost;
            _tmpDefaultCost = _cursor.getDouble(_cursorIndexOfDefaultCost);
            final String _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getString(_cursorIndexOfColor);
            }
            final String _tmpIconPath;
            if (_cursor.isNull(_cursorIndexOfIconPath)) {
              _tmpIconPath = null;
            } else {
              _tmpIconPath = _cursor.getString(_cursorIndexOfIconPath);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new WorkTypeEntity(_tmpId,_tmpName,_tmpDefaultCost,_tmpColor,_tmpIconPath,_tmpIsActive,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
