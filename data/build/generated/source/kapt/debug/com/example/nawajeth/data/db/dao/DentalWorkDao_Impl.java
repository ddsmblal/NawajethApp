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
import com.example.nawajeth.data.db.entity.DentalWorkEntity;
import java.lang.Class;
import java.lang.Double;
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
public final class DentalWorkDao_Impl implements DentalWorkDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DentalWorkEntity> __insertionAdapterOfDentalWorkEntity;

  private final EntityDeletionOrUpdateAdapter<DentalWorkEntity> __deletionAdapterOfDentalWorkEntity;

  private final EntityDeletionOrUpdateAdapter<DentalWorkEntity> __updateAdapterOfDentalWorkEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteDentalWorkById;

  public DentalWorkDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDentalWorkEntity = new EntityInsertionAdapter<DentalWorkEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `dental_works` (`id`,`patientId`,`toothNumber`,`workTypeId`,`description`,`cost`,`paidAmount`,`remainingAmount`,`status`,`startDate`,`endDate`,`notes`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DentalWorkEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getPatientId());
        if (value.getToothNumber() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getToothNumber());
        }
        stmt.bindLong(4, value.getWorkTypeId());
        if (value.getDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDescription());
        }
        stmt.bindDouble(6, value.getCost());
        stmt.bindDouble(7, value.getPaidAmount());
        stmt.bindDouble(8, value.getRemainingAmount());
        if (value.getStatus() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getStatus());
        }
        stmt.bindLong(10, value.getStartDate());
        if (value.getEndDate() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindLong(11, value.getEndDate());
        }
        if (value.getNotes() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getNotes());
        }
        stmt.bindLong(13, value.getCreatedAt());
      }
    };
    this.__deletionAdapterOfDentalWorkEntity = new EntityDeletionOrUpdateAdapter<DentalWorkEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `dental_works` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DentalWorkEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfDentalWorkEntity = new EntityDeletionOrUpdateAdapter<DentalWorkEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `dental_works` SET `id` = ?,`patientId` = ?,`toothNumber` = ?,`workTypeId` = ?,`description` = ?,`cost` = ?,`paidAmount` = ?,`remainingAmount` = ?,`status` = ?,`startDate` = ?,`endDate` = ?,`notes` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DentalWorkEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getPatientId());
        if (value.getToothNumber() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getToothNumber());
        }
        stmt.bindLong(4, value.getWorkTypeId());
        if (value.getDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDescription());
        }
        stmt.bindDouble(6, value.getCost());
        stmt.bindDouble(7, value.getPaidAmount());
        stmt.bindDouble(8, value.getRemainingAmount());
        if (value.getStatus() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getStatus());
        }
        stmt.bindLong(10, value.getStartDate());
        if (value.getEndDate() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindLong(11, value.getEndDate());
        }
        if (value.getNotes() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getNotes());
        }
        stmt.bindLong(13, value.getCreatedAt());
        stmt.bindLong(14, value.getId());
      }
    };
    this.__preparedStmtOfDeleteDentalWorkById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM dental_works WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertDentalWork(final DentalWorkEntity dentalWork,
      final Continuation<? super Long> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfDentalWorkEntity.insertAndReturnId(dentalWork);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteDentalWork(final DentalWorkEntity dentalWork,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfDentalWorkEntity.handle(dentalWork);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateDentalWork(final DentalWorkEntity dentalWork,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfDentalWorkEntity.handle(dentalWork);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteDentalWorkById(final long workId,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteDentalWorkById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, workId);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteDentalWorkById.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<DentalWorkEntity>> getDentalWorksByPatientId(final long patientId) {
    final String _sql = "SELECT * FROM dental_works WHERE patientId = ? ORDER BY startDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, patientId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"dental_works"}, new Callable<List<DentalWorkEntity>>() {
      @Override
      public List<DentalWorkEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
          final int _cursorIndexOfToothNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "toothNumber");
          final int _cursorIndexOfWorkTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "workTypeId");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfPaidAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "paidAmount");
          final int _cursorIndexOfRemainingAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "remainingAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<DentalWorkEntity> _result = new ArrayList<DentalWorkEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DentalWorkEntity _item;
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
            final long _tmpWorkTypeId;
            _tmpWorkTypeId = _cursor.getLong(_cursorIndexOfWorkTypeId);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final double _tmpCost;
            _tmpCost = _cursor.getDouble(_cursorIndexOfCost);
            final double _tmpPaidAmount;
            _tmpPaidAmount = _cursor.getDouble(_cursorIndexOfPaidAmount);
            final double _tmpRemainingAmount;
            _tmpRemainingAmount = _cursor.getDouble(_cursorIndexOfRemainingAmount);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final Long _tmpEndDate;
            if (_cursor.isNull(_cursorIndexOfEndDate)) {
              _tmpEndDate = null;
            } else {
              _tmpEndDate = _cursor.getLong(_cursorIndexOfEndDate);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new DentalWorkEntity(_tmpId,_tmpPatientId,_tmpToothNumber,_tmpWorkTypeId,_tmpDescription,_tmpCost,_tmpPaidAmount,_tmpRemainingAmount,_tmpStatus,_tmpStartDate,_tmpEndDate,_tmpNotes,_tmpCreatedAt);
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
  public Object getDentalWorkById(final long workId,
      final Continuation<? super DentalWorkEntity> continuation) {
    final String _sql = "SELECT * FROM dental_works WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, workId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DentalWorkEntity>() {
      @Override
      public DentalWorkEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
          final int _cursorIndexOfToothNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "toothNumber");
          final int _cursorIndexOfWorkTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "workTypeId");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfPaidAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "paidAmount");
          final int _cursorIndexOfRemainingAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "remainingAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final DentalWorkEntity _result;
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
            final long _tmpWorkTypeId;
            _tmpWorkTypeId = _cursor.getLong(_cursorIndexOfWorkTypeId);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final double _tmpCost;
            _tmpCost = _cursor.getDouble(_cursorIndexOfCost);
            final double _tmpPaidAmount;
            _tmpPaidAmount = _cursor.getDouble(_cursorIndexOfPaidAmount);
            final double _tmpRemainingAmount;
            _tmpRemainingAmount = _cursor.getDouble(_cursorIndexOfRemainingAmount);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final Long _tmpEndDate;
            if (_cursor.isNull(_cursorIndexOfEndDate)) {
              _tmpEndDate = null;
            } else {
              _tmpEndDate = _cursor.getLong(_cursorIndexOfEndDate);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new DentalWorkEntity(_tmpId,_tmpPatientId,_tmpToothNumber,_tmpWorkTypeId,_tmpDescription,_tmpCost,_tmpPaidAmount,_tmpRemainingAmount,_tmpStatus,_tmpStartDate,_tmpEndDate,_tmpNotes,_tmpCreatedAt);
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
  public Flow<List<DentalWorkEntity>> getDentalWorksByStatus(final String status) {
    final String _sql = "SELECT * FROM dental_works WHERE status = ? ORDER BY startDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (status == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, status);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[]{"dental_works"}, new Callable<List<DentalWorkEntity>>() {
      @Override
      public List<DentalWorkEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
          final int _cursorIndexOfToothNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "toothNumber");
          final int _cursorIndexOfWorkTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "workTypeId");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfPaidAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "paidAmount");
          final int _cursorIndexOfRemainingAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "remainingAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<DentalWorkEntity> _result = new ArrayList<DentalWorkEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DentalWorkEntity _item;
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
            final long _tmpWorkTypeId;
            _tmpWorkTypeId = _cursor.getLong(_cursorIndexOfWorkTypeId);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final double _tmpCost;
            _tmpCost = _cursor.getDouble(_cursorIndexOfCost);
            final double _tmpPaidAmount;
            _tmpPaidAmount = _cursor.getDouble(_cursorIndexOfPaidAmount);
            final double _tmpRemainingAmount;
            _tmpRemainingAmount = _cursor.getDouble(_cursorIndexOfRemainingAmount);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final Long _tmpEndDate;
            if (_cursor.isNull(_cursorIndexOfEndDate)) {
              _tmpEndDate = null;
            } else {
              _tmpEndDate = _cursor.getLong(_cursorIndexOfEndDate);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new DentalWorkEntity(_tmpId,_tmpPatientId,_tmpToothNumber,_tmpWorkTypeId,_tmpDescription,_tmpCost,_tmpPaidAmount,_tmpRemainingAmount,_tmpStatus,_tmpStartDate,_tmpEndDate,_tmpNotes,_tmpCreatedAt);
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
  public Flow<List<DentalWorkEntity>> getDentalWorksByTooth(final long patientId,
      final int toothNumber) {
    final String _sql = "SELECT * FROM dental_works WHERE toothNumber = ? AND patientId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, toothNumber);
    _argIndex = 2;
    _statement.bindLong(_argIndex, patientId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"dental_works"}, new Callable<List<DentalWorkEntity>>() {
      @Override
      public List<DentalWorkEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patientId");
          final int _cursorIndexOfToothNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "toothNumber");
          final int _cursorIndexOfWorkTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "workTypeId");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfPaidAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "paidAmount");
          final int _cursorIndexOfRemainingAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "remainingAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<DentalWorkEntity> _result = new ArrayList<DentalWorkEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DentalWorkEntity _item;
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
            final long _tmpWorkTypeId;
            _tmpWorkTypeId = _cursor.getLong(_cursorIndexOfWorkTypeId);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final double _tmpCost;
            _tmpCost = _cursor.getDouble(_cursorIndexOfCost);
            final double _tmpPaidAmount;
            _tmpPaidAmount = _cursor.getDouble(_cursorIndexOfPaidAmount);
            final double _tmpRemainingAmount;
            _tmpRemainingAmount = _cursor.getDouble(_cursorIndexOfRemainingAmount);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final Long _tmpEndDate;
            if (_cursor.isNull(_cursorIndexOfEndDate)) {
              _tmpEndDate = null;
            } else {
              _tmpEndDate = _cursor.getLong(_cursorIndexOfEndDate);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new DentalWorkEntity(_tmpId,_tmpPatientId,_tmpToothNumber,_tmpWorkTypeId,_tmpDescription,_tmpCost,_tmpPaidAmount,_tmpRemainingAmount,_tmpStatus,_tmpStartDate,_tmpEndDate,_tmpNotes,_tmpCreatedAt);
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
  public Object getTotalRemainingAmountForPatient(final long patientId,
      final Continuation<? super Double> continuation) {
    final String _sql = "SELECT SUM(remainingAmount) FROM dental_works WHERE patientId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, patientId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if(_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
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

  @Override
  public Object getDentalWorkCountForPatient(final long patientId,
      final Continuation<? super Integer> continuation) {
    final String _sql = "SELECT COUNT(*) FROM dental_works WHERE patientId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, patientId);
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
