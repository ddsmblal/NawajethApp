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
import com.example.nawajeth.data.db.entity.PatientEntity;
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
public final class PatientDao_Impl implements PatientDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PatientEntity> __insertionAdapterOfPatientEntity;

  private final EntityDeletionOrUpdateAdapter<PatientEntity> __deletionAdapterOfPatientEntity;

  private final EntityDeletionOrUpdateAdapter<PatientEntity> __updateAdapterOfPatientEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeletePatientById;

  public PatientDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPatientEntity = new EntityInsertionAdapter<PatientEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `patients` (`id`,`name`,`age`,`gender`,`phone`,`notes`,`lastVisitDate`,`totalDue`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PatientEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getAge());
        if (value.getGender() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getGender());
        }
        if (value.getPhone() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPhone());
        }
        if (value.getNotes() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getNotes());
        }
        if (value.getLastVisitDate() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, value.getLastVisitDate());
        }
        stmt.bindDouble(8, value.getTotalDue());
        stmt.bindLong(9, value.getCreatedAt());
      }
    };
    this.__deletionAdapterOfPatientEntity = new EntityDeletionOrUpdateAdapter<PatientEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `patients` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PatientEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfPatientEntity = new EntityDeletionOrUpdateAdapter<PatientEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `patients` SET `id` = ?,`name` = ?,`age` = ?,`gender` = ?,`phone` = ?,`notes` = ?,`lastVisitDate` = ?,`totalDue` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PatientEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getAge());
        if (value.getGender() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getGender());
        }
        if (value.getPhone() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPhone());
        }
        if (value.getNotes() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getNotes());
        }
        if (value.getLastVisitDate() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, value.getLastVisitDate());
        }
        stmt.bindDouble(8, value.getTotalDue());
        stmt.bindLong(9, value.getCreatedAt());
        stmt.bindLong(10, value.getId());
      }
    };
    this.__preparedStmtOfDeletePatientById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM patients WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertPatient(final PatientEntity patient,
      final Continuation<? super Long> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfPatientEntity.insertAndReturnId(patient);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deletePatient(final PatientEntity patient,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPatientEntity.handle(patient);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updatePatient(final PatientEntity patient,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPatientEntity.handle(patient);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deletePatientById(final long patientId,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePatientById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, patientId);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeletePatientById.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<PatientEntity>> getAllPatients() {
    final String _sql = "SELECT * FROM patients ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"patients"}, new Callable<List<PatientEntity>>() {
      @Override
      public List<PatientEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfLastVisitDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastVisitDate");
          final int _cursorIndexOfTotalDue = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDue");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<PatientEntity> _result = new ArrayList<PatientEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PatientEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final String _tmpGender;
            if (_cursor.isNull(_cursorIndexOfGender)) {
              _tmpGender = null;
            } else {
              _tmpGender = _cursor.getString(_cursorIndexOfGender);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpLastVisitDate;
            if (_cursor.isNull(_cursorIndexOfLastVisitDate)) {
              _tmpLastVisitDate = null;
            } else {
              _tmpLastVisitDate = _cursor.getLong(_cursorIndexOfLastVisitDate);
            }
            final double _tmpTotalDue;
            _tmpTotalDue = _cursor.getDouble(_cursorIndexOfTotalDue);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new PatientEntity(_tmpId,_tmpName,_tmpAge,_tmpGender,_tmpPhone,_tmpNotes,_tmpLastVisitDate,_tmpTotalDue,_tmpCreatedAt);
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
  public Object getPatientById(final long patientId,
      final Continuation<? super PatientEntity> continuation) {
    final String _sql = "SELECT * FROM patients WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, patientId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PatientEntity>() {
      @Override
      public PatientEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfLastVisitDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastVisitDate");
          final int _cursorIndexOfTotalDue = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDue");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final PatientEntity _result;
          if(_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final String _tmpGender;
            if (_cursor.isNull(_cursorIndexOfGender)) {
              _tmpGender = null;
            } else {
              _tmpGender = _cursor.getString(_cursorIndexOfGender);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpLastVisitDate;
            if (_cursor.isNull(_cursorIndexOfLastVisitDate)) {
              _tmpLastVisitDate = null;
            } else {
              _tmpLastVisitDate = _cursor.getLong(_cursorIndexOfLastVisitDate);
            }
            final double _tmpTotalDue;
            _tmpTotalDue = _cursor.getDouble(_cursorIndexOfTotalDue);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new PatientEntity(_tmpId,_tmpName,_tmpAge,_tmpGender,_tmpPhone,_tmpNotes,_tmpLastVisitDate,_tmpTotalDue,_tmpCreatedAt);
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
  public Flow<List<PatientEntity>> searchPatients(final String searchQuery) {
    final String _sql = "SELECT * FROM patients WHERE name LIKE '%' || ? || '%' OR phone LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (searchQuery == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, searchQuery);
    }
    _argIndex = 2;
    if (searchQuery == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, searchQuery);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[]{"patients"}, new Callable<List<PatientEntity>>() {
      @Override
      public List<PatientEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfLastVisitDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastVisitDate");
          final int _cursorIndexOfTotalDue = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDue");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<PatientEntity> _result = new ArrayList<PatientEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PatientEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final String _tmpGender;
            if (_cursor.isNull(_cursorIndexOfGender)) {
              _tmpGender = null;
            } else {
              _tmpGender = _cursor.getString(_cursorIndexOfGender);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpLastVisitDate;
            if (_cursor.isNull(_cursorIndexOfLastVisitDate)) {
              _tmpLastVisitDate = null;
            } else {
              _tmpLastVisitDate = _cursor.getLong(_cursorIndexOfLastVisitDate);
            }
            final double _tmpTotalDue;
            _tmpTotalDue = _cursor.getDouble(_cursorIndexOfTotalDue);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new PatientEntity(_tmpId,_tmpName,_tmpAge,_tmpGender,_tmpPhone,_tmpNotes,_tmpLastVisitDate,_tmpTotalDue,_tmpCreatedAt);
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
  public Flow<List<PatientEntity>> getRecentPatients(final int limit) {
    final String _sql = "SELECT * FROM patients ORDER BY lastVisitDate DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"patients"}, new Callable<List<PatientEntity>>() {
      @Override
      public List<PatientEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfLastVisitDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastVisitDate");
          final int _cursorIndexOfTotalDue = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDue");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<PatientEntity> _result = new ArrayList<PatientEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PatientEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final String _tmpGender;
            if (_cursor.isNull(_cursorIndexOfGender)) {
              _tmpGender = null;
            } else {
              _tmpGender = _cursor.getString(_cursorIndexOfGender);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpLastVisitDate;
            if (_cursor.isNull(_cursorIndexOfLastVisitDate)) {
              _tmpLastVisitDate = null;
            } else {
              _tmpLastVisitDate = _cursor.getLong(_cursorIndexOfLastVisitDate);
            }
            final double _tmpTotalDue;
            _tmpTotalDue = _cursor.getDouble(_cursorIndexOfTotalDue);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new PatientEntity(_tmpId,_tmpName,_tmpAge,_tmpGender,_tmpPhone,_tmpNotes,_tmpLastVisitDate,_tmpTotalDue,_tmpCreatedAt);
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
  public Flow<List<PatientEntity>> getPatientsByDueAmount() {
    final String _sql = "SELECT * FROM patients ORDER BY totalDue DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"patients"}, new Callable<List<PatientEntity>>() {
      @Override
      public List<PatientEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfLastVisitDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastVisitDate");
          final int _cursorIndexOfTotalDue = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDue");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<PatientEntity> _result = new ArrayList<PatientEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PatientEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final String _tmpGender;
            if (_cursor.isNull(_cursorIndexOfGender)) {
              _tmpGender = null;
            } else {
              _tmpGender = _cursor.getString(_cursorIndexOfGender);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpLastVisitDate;
            if (_cursor.isNull(_cursorIndexOfLastVisitDate)) {
              _tmpLastVisitDate = null;
            } else {
              _tmpLastVisitDate = _cursor.getLong(_cursorIndexOfLastVisitDate);
            }
            final double _tmpTotalDue;
            _tmpTotalDue = _cursor.getDouble(_cursorIndexOfTotalDue);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new PatientEntity(_tmpId,_tmpName,_tmpAge,_tmpGender,_tmpPhone,_tmpNotes,_tmpLastVisitDate,_tmpTotalDue,_tmpCreatedAt);
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
  public Object getPatientCount(final Continuation<? super Integer> continuation) {
    final String _sql = "SELECT COUNT(*) FROM patients";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
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
