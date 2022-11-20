package com.team126.goingdutch;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BillDao_Impl implements BillDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BillEntity> __insertionAdapterOfBillEntity;

  private final EntityDeletionOrUpdateAdapter<BillEntity> __deletionAdapterOfBillEntity;

  private final EntityDeletionOrUpdateAdapter<BillEntity> __updateAdapterOfBillEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public BillDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBillEntity = new EntityInsertionAdapter<BillEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `BillEntity` (`Id`,`MemberId`,`Item`,`PaidBy`,`Cost`,`GroupName`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BillEntity value) {
        stmt.bindLong(1, value.id);
        stmt.bindLong(2, value.mid);
        if (value.item == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.item);
        }
        if (value.paidBy == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.paidBy);
        }
        if (value.cost == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.cost);
        }
        if (value.gName == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.gName);
        }
      }
    };
    this.__deletionAdapterOfBillEntity = new EntityDeletionOrUpdateAdapter<BillEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `BillEntity` WHERE `Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BillEntity value) {
        stmt.bindLong(1, value.id);
      }
    };
    this.__updateAdapterOfBillEntity = new EntityDeletionOrUpdateAdapter<BillEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `BillEntity` SET `Id` = ?,`MemberId` = ?,`Item` = ?,`PaidBy` = ?,`Cost` = ?,`GroupName` = ? WHERE `Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BillEntity value) {
        stmt.bindLong(1, value.id);
        stmt.bindLong(2, value.mid);
        if (value.item == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.item);
        }
        if (value.paidBy == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.paidBy);
        }
        if (value.cost == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.cost);
        }
        if (value.gName == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.gName);
        }
        stmt.bindLong(7, value.id);
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM billentity WHERE GroupName= ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final BillEntity bill) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfBillEntity.insert(bill);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final BillEntity bill) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfBillEntity.handle(bill);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final BillEntity bill) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfBillEntity.handle(bill);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll(final String gName) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    int _argIndex = 1;
    if (gName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, gName);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public LiveData<List<BillEntity>> getAll(final String gName) {
    final String _sql = "SELECT * FROM billentity WHERE GroupName= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (gName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, gName);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"billentity"}, false, new Callable<List<BillEntity>>() {
      @Override
      public List<BillEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
          final int _cursorIndexOfMid = CursorUtil.getColumnIndexOrThrow(_cursor, "MemberId");
          final int _cursorIndexOfItem = CursorUtil.getColumnIndexOrThrow(_cursor, "Item");
          final int _cursorIndexOfPaidBy = CursorUtil.getColumnIndexOrThrow(_cursor, "PaidBy");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "Cost");
          final int _cursorIndexOfGName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
          final List<BillEntity> _result = new ArrayList<BillEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final BillEntity _item;
            final int _tmpMid;
            _tmpMid = _cursor.getInt(_cursorIndexOfMid);
            final String _tmpItem;
            if (_cursor.isNull(_cursorIndexOfItem)) {
              _tmpItem = null;
            } else {
              _tmpItem = _cursor.getString(_cursorIndexOfItem);
            }
            final String _tmpPaidBy;
            if (_cursor.isNull(_cursorIndexOfPaidBy)) {
              _tmpPaidBy = null;
            } else {
              _tmpPaidBy = _cursor.getString(_cursorIndexOfPaidBy);
            }
            final String _tmpCost;
            if (_cursor.isNull(_cursorIndexOfCost)) {
              _tmpCost = null;
            } else {
              _tmpCost = _cursor.getString(_cursorIndexOfCost);
            }
            final String _tmpGName;
            if (_cursor.isNull(_cursorIndexOfGName)) {
              _tmpGName = null;
            } else {
              _tmpGName = _cursor.getString(_cursorIndexOfGName);
            }
            _item = new BillEntity(_tmpMid,_tmpItem,_tmpCost,_tmpGName,_tmpPaidBy);
            _item.id = _cursor.getInt(_cursorIndexOfId);
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
  public List<BillEntity> getAllMemberBills(final String gName, final int mid) {
    final String _sql = "SELECT * FROM billentity WHERE GroupName= ? AND MemberId= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (gName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, gName);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, mid);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
      final int _cursorIndexOfMid = CursorUtil.getColumnIndexOrThrow(_cursor, "MemberId");
      final int _cursorIndexOfItem = CursorUtil.getColumnIndexOrThrow(_cursor, "Item");
      final int _cursorIndexOfPaidBy = CursorUtil.getColumnIndexOrThrow(_cursor, "PaidBy");
      final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "Cost");
      final int _cursorIndexOfGName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
      final List<BillEntity> _result = new ArrayList<BillEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final BillEntity _item;
        final int _tmpMid;
        _tmpMid = _cursor.getInt(_cursorIndexOfMid);
        final String _tmpItem;
        if (_cursor.isNull(_cursorIndexOfItem)) {
          _tmpItem = null;
        } else {
          _tmpItem = _cursor.getString(_cursorIndexOfItem);
        }
        final String _tmpPaidBy;
        if (_cursor.isNull(_cursorIndexOfPaidBy)) {
          _tmpPaidBy = null;
        } else {
          _tmpPaidBy = _cursor.getString(_cursorIndexOfPaidBy);
        }
        final String _tmpCost;
        if (_cursor.isNull(_cursorIndexOfCost)) {
          _tmpCost = null;
        } else {
          _tmpCost = _cursor.getString(_cursorIndexOfCost);
        }
        final String _tmpGName;
        if (_cursor.isNull(_cursorIndexOfGName)) {
          _tmpGName = null;
        } else {
          _tmpGName = _cursor.getString(_cursorIndexOfGName);
        }
        _item = new BillEntity(_tmpMid,_tmpItem,_tmpCost,_tmpGName,_tmpPaidBy);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
