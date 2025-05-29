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
import com.example.nawajeth.data.db.entity.DentalSymbolEntity;
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
public final class DentalSymbolDao_Impl implements DentalSymbolDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DentalSymbolEntity> __insertionAdapterOfDentalSymbolEntity;

  private final EntityDeletionOrUpdateAdapter<DentalSymbolEntity> __deletionAdapterOfDentalSymbolEntity;

  private final EntityDeletionOrUpdateAdapter<DentalSymbolEntity> __updateAdapterOfDentalSymbolEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteDentalSymbolById;

  public DentalSymbolDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDentalSymbolEntity = new EntityInsertionAdapter<DentalSymbolEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `dental_symbols` (`id`,`name`,`description`,`symbolPath`,`workTypeId`,`color`,`isActive`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DentalSymbolEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getSymbolPath() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSymbolPath());
        }
        if (value.getWorkTypeId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.getWorkTypeId());
        }
        if (value.getColor() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getColor());
        }
        final int _tmp = value.isActive() ? 1 : 0;
        stmt.bindLong(7, _tmp);
        stmt.bindLong(8, value.getCreatedAt());
      }
    };
    this.__deletionAdapterOfDentalSymbolEntity = new EntityDeletionOrUpdateAdapter<DentalSymbolEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `dental_symbols` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DentalSymbolEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfDentalSymbolEntity = new EntityDeletionOrUpdateAdapter<DentalSymbolEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `dental_symbols` SET `id` = ?,`name` = ?,`description` = ?,`symbolPath` = ?,`workTypeId` = ?,`color` = ?,`isActive` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DentalSymbolEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getSymbolPath() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSymbolPath());
        }
        if (value.getWorkTypeId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.getWorkTypeId());
        }
        if (value.getColor() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getColor());
        }
        final int _tmp = value.isActive() ? 1 : 0;
        stmt.bindLong(7, _tmp);
        stmt.bindLong(8, value.getCreatedAt());
        stmt.bindLong(9, value.getId());
      }
    };
    this.__preparedStmtOfDeleteDentalSymbolById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM dental_symbols WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertDentalSymbol(final DentalSymbolEntity dentalSymbol,
      final Continuation<? super Long> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfDentalSymbolEntity.insertAndReturnId(dentalSymbol);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteDentalSymbol(final DentalSymbolEntity dentalSymbol,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfDentalSymbolEntity.handle(dentalSymbol);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateDentalSymbol(final DentalSymbolEntity dentalSymbol,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfDentalSymbolEntity.handle(dentalSymbol);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteDentalSymbolById(final long symbolId,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteDentalSymbolById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, symbolId);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteDentalSymbolById.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<DentalSymbolEntity>> getAllActiveDentalSymbols() {
    final String _sql = "SELECT * FROM dental_symbols WHERE isActive = 1 ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"dental_symbols"}, new Callable<List<DentalSymbolEntity>>() {
      @Override
      public List<DentalSymbolEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSymbolPath = CursorUtil.getColumnIndexOrThrow(_cursor, "symbolPath");
          final int _cursorIndexOfWorkTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "workTypeId");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<DentalSymbolEntity> _result = new ArrayList<DentalSymbolEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DentalSymbolEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpSymbolPath;
            if (_cursor.isNull(_cursorIndexOfSymbolPath)) {
              _tmpSymbolPath = null;
            } else {
              _tmpSymbolPath = _cursor.getString(_cursorIndexOfSymbolPath);
            }
            final Long _tmpWorkTypeId;
            if (_cursor.isNull(_cursorIndexOfWorkTypeId)) {
              _tmpWorkTypeId = null;
            } else {
              _tmpWorkTypeId = _cursor.getLong(_cursorIndexOfWorkTypeId);
            }
            final String _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getString(_cursorIndexOfColor);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new DentalSymbolEntity(_tmpId,_tmpName,_tmpDescription,_tmpSymbolPath,_tmpWorkTypeId,_tmpColor,_tmpIsActive,_tmpCreatedAt);
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
  public Flow<List<DentalSymbolEntity>> getAllDentalSymbols() {
    final String _sql = "SELECT * FROM dental_symbols ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"dental_symbols"}, new Callable<List<DentalSymbolEntity>>() {
      @Override
      public List<DentalSymbolEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSymbolPath = CursorUtil.getColumnIndexOrThrow(_cursor, "symbolPath");
          final int _cursorIndexOfWorkTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "workTypeId");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<DentalSymbolEntity> _result = new ArrayList<DentalSymbolEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DentalSymbolEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpSymbolPath;
            if (_cursor.isNull(_cursorIndexOfSymbolPath)) {
              _tmpSymbolPath = null;
            } else {
              _tmpSymbolPath = _cursor.getString(_cursorIndexOfSymbolPath);
            }
            final Long _tmpWorkTypeId;
            if (_cursor.isNull(_cursorIndexOfWorkTypeId)) {
              _tmpWorkTypeId = null;
            } else {
              _tmpWorkTypeId = _cursor.getLong(_cursorIndexOfWorkTypeId);
            }
            final String _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getString(_cursorIndexOfColor);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new DentalSymbolEntity(_tmpId,_tmpName,_tmpDescription,_tmpSymbolPath,_tmpWorkTypeId,_tmpColor,_tmpIsActive,_tmpCreatedAt);
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
  public Object getDentalSymbolById(final long symbolId,
      final Continuation<? super DentalSymbolEntity> continuation) {
    final String _sql = "SELECT * FROM dental_symbols WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, symbolId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DentalSymbolEntity>() {
      @Override
      public DentalSymbolEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSymbolPath = CursorUtil.getColumnIndexOrThrow(_cursor, "symbolPath");
          final int _cursorIndexOfWorkTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "workTypeId");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final DentalSymbolEntity _result;
          if(_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpSymbolPath;
            if (_cursor.isNull(_cursorIndexOfSymbolPath)) {
              _tmpSymbolPath = null;
            } else {
              _tmpSymbolPath = _cursor.getString(_cursorIndexOfSymbolPath);
            }
            final Long _tmpWorkTypeId;
            if (_cursor.isNull(_cursorIndexOfWorkTypeId)) {
              _tmpWorkTypeId = null;
            } else {
              _tmpWorkTypeId = _cursor.getLong(_cursorIndexOfWorkTypeId);
            }
            final String _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getString(_cursorIndexOfColor);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new DentalSymbolEntity(_tmpId,_tmpName,_tmpDescription,_tmpSymbolPath,_tmpWorkTypeId,_tmpColor,_tmpIsActive,_tmpCreatedAt);
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

  @Override
  public Flow<List<DentalSymbolEntity>> getDentalSymbolsByWorkType(final long workTypeId) {
    final String _sql = "SELECT * FROM dental_symbols WHERE workTypeId = ? AND isActive = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, workTypeId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"dental_symbols"}, new Callable<List<DentalSymbolEntity>>() {
      @Override
      public List<DentalSymbolEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSymbolPath = CursorUtil.getColumnIndexOrThrow(_cursor, "symbolPath");
          final int _cursorIndexOfWorkTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "workTypeId");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<DentalSymbolEntity> _result = new ArrayList<DentalSymbolEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DentalSymbolEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpSymbolPath;
            if (_cursor.isNull(_cursorIndexOfSymbolPath)) {
              _tmpSymbolPath = null;
            } else {
              _tmpSymbolPath = _cursor.getString(_cursorIndexOfSymbolPath);
            }
            final Long _tmpWorkTypeId;
            if (_cursor.isNull(_cursorIndexOfWorkTypeId)) {
              _tmpWorkTypeId = null;
            } else {
              _tmpWorkTypeId = _cursor.getLong(_cursorIndexOfWorkTypeId);
            }
            final String _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getString(_cursorIndexOfColor);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new DentalSymbolEntity(_tmpId,_tmpName,_tmpDescription,_tmpSymbolPath,_tmpWorkTypeId,_tmpColor,_tmpIsActive,_tmpCreatedAt);
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
