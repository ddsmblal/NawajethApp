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
import com.example.nawajeth.data.db.entity.DentalConditionEntity;
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
public final class DentalConditionDao_Impl implements DentalConditionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DentalConditionEntity> __insertionAdapterOfDentalConditionEntity;

  private final EntityDeletionOrUpdateAdapter<DentalConditionEntity> __deletionAdapterOfDentalConditionEntity;

  private final EntityDeletionOrUpdateAdapter<DentalConditionEntity> __updateAdapterOfDentalConditionEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteDentalConditionById;

  public DentalConditionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDentalConditionEntity = new EntityInsertionAdapter<DentalConditionEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `dental_conditions` (`id`,`patientId`,`toothNumber`,`symbolId`,`description`,`notes`,`recordDate`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DentalConditionEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getPatientId());
        if (value.getToothNumber() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getToothNumber());
        }
        if (value.getSymbolId() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getSymbolId());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDescription());
        }
        if (value.getNotes() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getNotes());
        }
        stmt.bindLong(7, value.getRecordDate());
        stmt.bindLong(8, value.getCreatedAt());
      }
    };
    this.__deletionAdapterOfDentalConditionEntity = new EntityDeletionOrUpdateAdapter<DentalConditionEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `dental_conditions` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DentalConditionEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfDentalConditionEntity = new EntityDeletionOrUpdateAdapter<DentalConditionEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `dental_conditions` SET `id` = ?,`patientId` = ?,`toothNumber` = ?,`symbolId` = ?,`description` = ?,`notes` = ?,`recordDate` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DentalConditionEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getPatientId());
        if (value.getToothNumber() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getToothNumber());
        }
        if (value.getSymbolId() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getSymbolId());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDescription());
        }
        if (value.getNotes() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getNotes());
        }
        stmt.bindLong(7, value.getRecordDate());
        stmt.bindLong(8, value.getCreatedAt());
        stmt.bindLong(9, value.getId());
      }
    };
    this.__preparedStmtOfDeleteDentalConditionById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM dental_conditions WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertDentalCondition(final DentalConditionEntity dentalCondition,
      final Continuation<? super Long> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfDentalConditionEntity.insertAndReturnId(dentalCondition);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteDentalCondition(final DentalConditionEntity dentalCondition,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfDentalConditionEntity.handle(dentalCondition);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateDentalCondition(final DentalConditionEntity dentalCondition,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfDentalConditionEntity.handle(dentalCondition);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteDentalConditionById(final long conditionId,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteDentalConditionById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, conditionId);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteDentalConditionById.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<DentalConditionEntity>> getDentalConditionsByPatientId(final long patientId) {
    final String _sql = "SELECT * FROM dental_conditions WHERE patientId = ? ORDER BY recordDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, patientId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"dental_conditions"}, new Callable<List<DentalConditionEntity>>() {
      @Override
      public List<DentalConditionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
          final int _cursorIndexOfToothNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "toothNumber");
          final int _cursorIndexOfSymbolId = CursorUtil.getColumnIndexOrThrow(_cursor, "symbolId");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRecordDate = CursorUtil.getColumnIndexOrThrow(_cursor, "recordDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<DentalConditionEntity> _result = new ArrayList<DentalConditionEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DentalConditionEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpPatientId;
            _tmpPatientId = _cursor.getLong(_cursorIndexOfPatientId);
            final Integer _tmpToothNumber;
            if (_cursor.isNull(_cursorIndexOfToothNumber)) {
              _tmpToothNumber = null;
            } else {
              _tmpToothNumber = _cursor.getInt(_cursorIndexOfToothNumber);
            }
            final Long _tmpSymbolId;
            if (_cursor.isNull(_cursorIndexOfSymbolId)) {
              _tmpSymbolId = null;
            } else {
              _tmpSymbolId = _cursor.getLong(_cursorIndexOfSymbolId);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpRecordDate;
            _tmpRecordDate = _cursor.getLong(_cursorIndexOfRecordDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new DentalConditionEntity(_tmpId,_tmpPatientId,_tmpToothNumber,_tmpSymbolId,_tmpDescription,_tmpNotes,_tmpRecordDate,_tmpCreatedAt);
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
  public Object getDentalConditionById(final long conditionId,
      final Continuation<? super DentalConditionEntity> continuation) {
    final String _sql = "SELECT * FROM dental_conditions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, conditionId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DentalConditionEntity>() {
      @Override
      public DentalConditionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
          final int _cursorIndexOfToothNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "toothNumber");
          final int _cursorIndexOfSymbolId = CursorUtil.getColumnIndexOrThrow(_cursor, "symbolId");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRecordDate = CursorUtil.getColumnIndexOrThrow(_cursor, "recordDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final DentalConditionEntity _result;
          if(_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpPatientId;
            _tmpPatientId = _cursor.getLong(_cursorIndexOfPatientId);
            final Integer _tmpToothNumber;
            if (_cursor.isNull(_cursorIndexOfToothNumber)) {
              _tmpToothNumber = null;
            } else {
              _tmpToothNumber = _cursor.getInt(_cursorIndexOfToothNumber);
            }
            final Long _tmpSymbolId;
            if (_cursor.isNull(_cursorIndexOfSymbolId)) {
              _tmpSymbolId = null;
            } else {
              _tmpSymbolId = _cursor.getLong(_cursorIndexOfSymbolId);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpRecordDate;
            _tmpRecordDate = _cursor.getLong(_cursorIndexOfRecordDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new DentalConditionEntity(_tmpId,_tmpPatientId,_tmpToothNumber,_tmpSymbolId,_tmpDescription,_tmpNotes,_tmpRecordDate,_tmpCreatedAt);
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
  public Flow<List<DentalConditionEntity>> getDentalConditionsByTooth(final long patientId,
      final int toothNumber) {
    final String _sql = "SELECT * FROM dental_conditions WHERE toothNumber = ? AND patientId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, toothNumber);
    _argIndex = 2;
    _statement.bindLong(_argIndex, patientId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"dental_conditions"}, new Callable<List<DentalConditionEntity>>() {
      @Override
      public List<DentalConditionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
          final int _cursorIndexOfToothNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "toothNumber");
          final int _cursorIndexOfSymbolId = CursorUtil.getColumnIndexOrThrow(_cursor, "symbolId");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfRecordDate = CursorUtil.getColumnIndexOrThrow(_cursor, "recordDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<DentalConditionEntity> _result = new ArrayList<DentalConditionEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DentalConditionEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpPatientId;
            _tmpPatientId = _cursor.getLong(_cursorIndexOfPatientId);
            final Integer _tmpToothNumber;
            if (_cursor.isNull(_cursorIndexOfToothNumber)) {
              _tmpToothNumber = null;
            } else {
              _tmpToothNumber = _cursor.getInt(_cursorIndexOfToothNumber);
            }
            final Long _tmpSymbolId;
            if (_cursor.isNull(_cursorIndexOfSymbolId)) {
              _tmpSymbolId = null;
            } else {
              _tmpSymbolId = _cursor.getLong(_cursorIndexOfSymbolId);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpRecordDate;
            _tmpRecordDate = _cursor.getLong(_cursorIndexOfRecordDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new DentalConditionEntity(_tmpId,_tmpPatientId,_tmpToothNumber,_tmpSymbolId,_tmpDescription,_tmpNotes,_tmpRecordDate,_tmpCreatedAt);
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
