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
public final class GroupDao_Impl implements GroupDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<GroupEntity> __insertionAdapterOfGroupEntity;

  private final EntityDeletionOrUpdateAdapter<GroupEntity> __deletionAdapterOfGroupEntity;

  private final EntityDeletionOrUpdateAdapter<GroupEntity> __updateAdapterOfGroupEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public GroupDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGroupEntity = new EntityInsertionAdapter<GroupEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `GroupEntity` (`GroupName`,`GroupCurrency`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GroupEntity value) {
        if (value.gName == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.gName);
        }
        if (value.gCurrency == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.gCurrency);
        }
      }
    };
    this.__deletionAdapterOfGroupEntity = new EntityDeletionOrUpdateAdapter<GroupEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `GroupEntity` WHERE `GroupName` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GroupEntity value) {
        if (value.gName == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.gName);
        }
      }
    };
    this.__updateAdapterOfGroupEntity = new EntityDeletionOrUpdateAdapter<GroupEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `GroupEntity` SET `GroupName` = ?,`GroupCurrency` = ? WHERE `GroupName` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GroupEntity value) {
        if (value.gName == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.gName);
        }
        if (value.gCurrency == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.gCurrency);
        }
        if (value.gName == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.gName);
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM groupentity";
        return _query;
      }
    };
  }

  @Override
  public void insert(final GroupEntity member) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfGroupEntity.insert(member);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final GroupEntity member) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfGroupEntity.handle(member);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final GroupEntity member) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfGroupEntity.handle(member);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
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
  public LiveData<List<GroupEntity>> getAll() {
    final String _sql = "SELECT * FROM groupentity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"groupentity"}, false, new Callable<List<GroupEntity>>() {
      @Override
      public List<GroupEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfGName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
          final int _cursorIndexOfGCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupCurrency");
          final List<GroupEntity> _result = new ArrayList<GroupEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final GroupEntity _item;
            final String _tmpGName;
            if (_cursor.isNull(_cursorIndexOfGName)) {
              _tmpGName = null;
            } else {
              _tmpGName = _cursor.getString(_cursorIndexOfGName);
            }
            _item = new GroupEntity(_tmpGName);
            if (_cursor.isNull(_cursorIndexOfGCurrency)) {
              _item.gCurrency = null;
            } else {
              _item.gCurrency = _cursor.getString(_cursorIndexOfGCurrency);
            }
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
  public List<GroupEntity> getAllNonLive() {
    final String _sql = "SELECT * FROM groupentity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
      final int _cursorIndexOfGCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupCurrency");
      final List<GroupEntity> _result = new ArrayList<GroupEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final GroupEntity _item;
        final String _tmpGName;
        if (_cursor.isNull(_cursorIndexOfGName)) {
          _tmpGName = null;
        } else {
          _tmpGName = _cursor.getString(_cursorIndexOfGName);
        }
        _item = new GroupEntity(_tmpGName);
        if (_cursor.isNull(_cursorIndexOfGCurrency)) {
          _item.gCurrency = null;
        } else {
          _item.gCurrency = _cursor.getString(_cursorIndexOfGCurrency);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<String> getGroupCurrency(final String gName) {
    final String _sql = "SELECT GroupCurrency FROM groupentity WHERE GroupName= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (gName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, gName);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"groupentity"}, false, new Callable<String>() {
      @Override
      public String call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final String _result;
          if(_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getString(0);
            }
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

  @Override
  public String getGroupCurrencyNonLive(final String gName) {
    final String _sql = "SELECT GroupCurrency FROM groupentity WHERE GroupName= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (gName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, gName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        if (_cursor.isNull(0)) {
          _result = null;
        } else {
          _result = _cursor.getString(0);
        }
      } else {
        _result = null;
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
