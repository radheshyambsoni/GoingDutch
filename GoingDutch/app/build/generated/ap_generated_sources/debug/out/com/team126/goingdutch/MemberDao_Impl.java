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
public final class MemberDao_Impl implements MemberDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MemberEntity> __insertionAdapterOfMemberEntity;

  private final EntityDeletionOrUpdateAdapter<MemberEntity> __deletionAdapterOfMemberEntity;

  private final EntityDeletionOrUpdateAdapter<MemberEntity> __updateAdapterOfMemberEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MemberDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMemberEntity = new EntityInsertionAdapter<MemberEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `MemberEntity` (`Id`,`GroupName`,`MemberName`,`MemberAvatar`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MemberEntity value) {
        stmt.bindLong(1, value.id);
        if (value.gName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.gName);
        }
        if (value.name == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.name);
        }
        stmt.bindLong(4, value.mAvatar);
      }
    };
    this.__deletionAdapterOfMemberEntity = new EntityDeletionOrUpdateAdapter<MemberEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `MemberEntity` WHERE `Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MemberEntity value) {
        stmt.bindLong(1, value.id);
      }
    };
    this.__updateAdapterOfMemberEntity = new EntityDeletionOrUpdateAdapter<MemberEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `MemberEntity` SET `Id` = ?,`GroupName` = ?,`MemberName` = ?,`MemberAvatar` = ? WHERE `Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MemberEntity value) {
        stmt.bindLong(1, value.id);
        if (value.gName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.gName);
        }
        if (value.name == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.name);
        }
        stmt.bindLong(4, value.mAvatar);
        stmt.bindLong(5, value.id);
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM memberentity WHERE GroupName= ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final MemberEntity member) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMemberEntity.insert(member);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final MemberEntity member) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMemberEntity.handle(member);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final MemberEntity member) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfMemberEntity.handle(member);
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
  public LiveData<List<MemberEntity>> getAll(final String gName) {
    final String _sql = "SELECT * FROM memberentity WHERE GroupName= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (gName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, gName);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"memberentity"}, false, new Callable<List<MemberEntity>>() {
      @Override
      public List<MemberEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
          final int _cursorIndexOfGName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "MemberName");
          final int _cursorIndexOfMAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "MemberAvatar");
          final List<MemberEntity> _result = new ArrayList<MemberEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MemberEntity _item;
            final String _tmpGName;
            if (_cursor.isNull(_cursorIndexOfGName)) {
              _tmpGName = null;
            } else {
              _tmpGName = _cursor.getString(_cursorIndexOfGName);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            _item = new MemberEntity(_tmpName,_tmpGName);
            _item.id = _cursor.getInt(_cursorIndexOfId);
            final int _tmpMAvatar;
            _tmpMAvatar = _cursor.getInt(_cursorIndexOfMAvatar);
            _item.setMAvatar(_tmpMAvatar);
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
  public List<MemberEntity> getAllNonLive(final String gName) {
    final String _sql = "SELECT * FROM memberentity WHERE GroupName= ?";
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
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
      final int _cursorIndexOfGName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "MemberName");
      final int _cursorIndexOfMAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "MemberAvatar");
      final List<MemberEntity> _result = new ArrayList<MemberEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MemberEntity _item;
        final String _tmpGName;
        if (_cursor.isNull(_cursorIndexOfGName)) {
          _tmpGName = null;
        } else {
          _tmpGName = _cursor.getString(_cursorIndexOfGName);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item = new MemberEntity(_tmpName,_tmpGName);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        final int _tmpMAvatar;
        _tmpMAvatar = _cursor.getInt(_cursorIndexOfMAvatar);
        _item.setMAvatar(_tmpMAvatar);
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
