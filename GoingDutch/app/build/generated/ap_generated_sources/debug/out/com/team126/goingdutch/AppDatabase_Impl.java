package com.team126.goingdutch;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile MemberDao _memberDao;

  private volatile BillDao _billDao;

  private volatile GroupDao _groupDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MemberEntity` (`Id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `GroupName` TEXT, `MemberName` TEXT, `MemberAvatar` INTEGER NOT NULL, FOREIGN KEY(`GroupName`) REFERENCES `GroupEntity`(`GroupName`) ON UPDATE CASCADE ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `GroupNameIndexMember` ON `MemberEntity` (`GroupName`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `BillEntity` (`Id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `MemberId` INTEGER NOT NULL, `Item` TEXT, `PaidBy` TEXT, `Cost` TEXT, `GroupName` TEXT, FOREIGN KEY(`MemberId`) REFERENCES `MemberEntity`(`Id`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`GroupName`) REFERENCES `GroupEntity`(`GroupName`) ON UPDATE CASCADE ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `MemberIdIndex` ON `BillEntity` (`MemberId`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `GroupNameIndexBill` ON `BillEntity` (`GroupName`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `GroupEntity` (`GroupName` TEXT NOT NULL, `GroupCurrency` TEXT, PRIMARY KEY(`GroupName`))");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_GroupEntity_GroupName` ON `GroupEntity` (`GroupName`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'acb4c6bb06178e23e93d8db28c35d1c1')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `MemberEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `BillEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `GroupEntity`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsMemberEntity = new HashMap<String, TableInfo.Column>(4);
        _columnsMemberEntity.put("Id", new TableInfo.Column("Id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntity.put("GroupName", new TableInfo.Column("GroupName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntity.put("MemberName", new TableInfo.Column("MemberName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMemberEntity.put("MemberAvatar", new TableInfo.Column("MemberAvatar", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMemberEntity = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysMemberEntity.add(new TableInfo.ForeignKey("GroupEntity", "CASCADE", "CASCADE",Arrays.asList("GroupName"), Arrays.asList("GroupName")));
        final HashSet<TableInfo.Index> _indicesMemberEntity = new HashSet<TableInfo.Index>(1);
        _indicesMemberEntity.add(new TableInfo.Index("GroupNameIndexMember", false, Arrays.asList("GroupName"), Arrays.asList("ASC")));
        final TableInfo _infoMemberEntity = new TableInfo("MemberEntity", _columnsMemberEntity, _foreignKeysMemberEntity, _indicesMemberEntity);
        final TableInfo _existingMemberEntity = TableInfo.read(_db, "MemberEntity");
        if (! _infoMemberEntity.equals(_existingMemberEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "MemberEntity(com.team126.goingdutch.MemberEntity).\n"
                  + " Expected:\n" + _infoMemberEntity + "\n"
                  + " Found:\n" + _existingMemberEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsBillEntity = new HashMap<String, TableInfo.Column>(6);
        _columnsBillEntity.put("Id", new TableInfo.Column("Id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBillEntity.put("MemberId", new TableInfo.Column("MemberId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBillEntity.put("Item", new TableInfo.Column("Item", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBillEntity.put("PaidBy", new TableInfo.Column("PaidBy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBillEntity.put("Cost", new TableInfo.Column("Cost", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBillEntity.put("GroupName", new TableInfo.Column("GroupName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBillEntity = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysBillEntity.add(new TableInfo.ForeignKey("MemberEntity", "CASCADE", "CASCADE",Arrays.asList("MemberId"), Arrays.asList("Id")));
        _foreignKeysBillEntity.add(new TableInfo.ForeignKey("GroupEntity", "CASCADE", "CASCADE",Arrays.asList("GroupName"), Arrays.asList("GroupName")));
        final HashSet<TableInfo.Index> _indicesBillEntity = new HashSet<TableInfo.Index>(2);
        _indicesBillEntity.add(new TableInfo.Index("MemberIdIndex", false, Arrays.asList("MemberId"), Arrays.asList("ASC")));
        _indicesBillEntity.add(new TableInfo.Index("GroupNameIndexBill", false, Arrays.asList("GroupName"), Arrays.asList("ASC")));
        final TableInfo _infoBillEntity = new TableInfo("BillEntity", _columnsBillEntity, _foreignKeysBillEntity, _indicesBillEntity);
        final TableInfo _existingBillEntity = TableInfo.read(_db, "BillEntity");
        if (! _infoBillEntity.equals(_existingBillEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "BillEntity(com.team126.goingdutch.BillEntity).\n"
                  + " Expected:\n" + _infoBillEntity + "\n"
                  + " Found:\n" + _existingBillEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsGroupEntity = new HashMap<String, TableInfo.Column>(2);
        _columnsGroupEntity.put("GroupName", new TableInfo.Column("GroupName", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupEntity.put("GroupCurrency", new TableInfo.Column("GroupCurrency", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGroupEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGroupEntity = new HashSet<TableInfo.Index>(1);
        _indicesGroupEntity.add(new TableInfo.Index("index_GroupEntity_GroupName", true, Arrays.asList("GroupName"), Arrays.asList("ASC")));
        final TableInfo _infoGroupEntity = new TableInfo("GroupEntity", _columnsGroupEntity, _foreignKeysGroupEntity, _indicesGroupEntity);
        final TableInfo _existingGroupEntity = TableInfo.read(_db, "GroupEntity");
        if (! _infoGroupEntity.equals(_existingGroupEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "GroupEntity(com.team126.goingdutch.GroupEntity).\n"
                  + " Expected:\n" + _infoGroupEntity + "\n"
                  + " Found:\n" + _existingGroupEntity);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "acb4c6bb06178e23e93d8db28c35d1c1", "990c69b0d58285f34cd362b35576b93b");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "MemberEntity","BillEntity","GroupEntity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `MemberEntity`");
      _db.execSQL("DELETE FROM `BillEntity`");
      _db.execSQL("DELETE FROM `GroupEntity`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(MemberDao.class, MemberDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BillDao.class, BillDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GroupDao.class, GroupDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public MemberDao memberDao() {
    if (_memberDao != null) {
      return _memberDao;
    } else {
      synchronized(this) {
        if(_memberDao == null) {
          _memberDao = new MemberDao_Impl(this);
        }
        return _memberDao;
      }
    }
  }

  @Override
  public BillDao billDao() {
    if (_billDao != null) {
      return _billDao;
    } else {
      synchronized(this) {
        if(_billDao == null) {
          _billDao = new BillDao_Impl(this);
        }
        return _billDao;
      }
    }
  }

  @Override
  public GroupDao groupDao() {
    if (_groupDao != null) {
      return _groupDao;
    } else {
      synchronized(this) {
        if(_groupDao == null) {
          _groupDao = new GroupDao_Impl(this);
        }
        return _groupDao;
      }
    }
  }
}
