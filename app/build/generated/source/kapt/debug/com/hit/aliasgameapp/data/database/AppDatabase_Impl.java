package com.hit.aliasgameapp.data.database;

import androidx.annotation.NonNull;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.hit.aliasgameapp.data.dao.TeamDao;
import com.hit.aliasgameapp.data.dao.TeamDao_Impl;
import com.hit.aliasgameapp.data.dao.WordDao;
import com.hit.aliasgameapp.data.dao.WordDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile TeamDao _teamDao;

  private volatile WordDao _wordDao;

  @Override
  @NonNull
  protected RoomOpenDelegate createOpenDelegate() {
    final RoomOpenDelegate _openDelegate = new RoomOpenDelegate(3, "c60a7a5c35b6301d7973c6eaaa918fd9", "25d684397867a2574f04f523146986f5") {
      @Override
      public void createAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `words_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `word` TEXT NOT NULL)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `teams` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `color` TEXT NOT NULL, `members` TEXT NOT NULL, `notes` TEXT NOT NULL, `imagePath` TEXT)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c60a7a5c35b6301d7973c6eaaa918fd9')");
      }

      @Override
      public void dropAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `words_table`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `teams`");
      }

      @Override
      public void onCreate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      public void onOpen(@NonNull final SQLiteConnection connection) {
        internalInitInvalidationTracker(connection);
      }

      @Override
      public void onPreMigrate(@NonNull final SQLiteConnection connection) {
        DBUtil.dropFtsSyncTriggers(connection);
      }

      @Override
      public void onPostMigrate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      @NonNull
      public RoomOpenDelegate.ValidationResult onValidateSchema(
          @NonNull final SQLiteConnection connection) {
        final Map<String, TableInfo.Column> _columnsWordsTable = new HashMap<String, TableInfo.Column>(2);
        _columnsWordsTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWordsTable.put("word", new TableInfo.Column("word", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysWordsTable = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesWordsTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWordsTable = new TableInfo("words_table", _columnsWordsTable, _foreignKeysWordsTable, _indicesWordsTable);
        final TableInfo _existingWordsTable = TableInfo.read(connection, "words_table");
        if (!_infoWordsTable.equals(_existingWordsTable)) {
          return new RoomOpenDelegate.ValidationResult(false, "words_table(com.hit.aliasgameapp.data.model.WordEntity).\n"
                  + " Expected:\n" + _infoWordsTable + "\n"
                  + " Found:\n" + _existingWordsTable);
        }
        final Map<String, TableInfo.Column> _columnsTeams = new HashMap<String, TableInfo.Column>(6);
        _columnsTeams.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeams.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeams.put("color", new TableInfo.Column("color", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeams.put("members", new TableInfo.Column("members", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeams.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeams.put("imagePath", new TableInfo.Column("imagePath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysTeams = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesTeams = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTeams = new TableInfo("teams", _columnsTeams, _foreignKeysTeams, _indicesTeams);
        final TableInfo _existingTeams = TableInfo.read(connection, "teams");
        if (!_infoTeams.equals(_existingTeams)) {
          return new RoomOpenDelegate.ValidationResult(false, "teams(com.hit.aliasgameapp.data.model.Team).\n"
                  + " Expected:\n" + _infoTeams + "\n"
                  + " Found:\n" + _existingTeams);
        }
        return new RoomOpenDelegate.ValidationResult(true, null);
      }
    };
    return _openDelegate;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final Map<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final Map<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "words_table", "teams");
  }

  @Override
  public void clearAllTables() {
    super.performClear(false, "words_table", "teams");
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final Map<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(TeamDao.class, TeamDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WordDao.class, WordDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final Set<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public TeamDao teamDao() {
    if (_teamDao != null) {
      return _teamDao;
    } else {
      synchronized(this) {
        if(_teamDao == null) {
          _teamDao = new TeamDao_Impl(this);
        }
        return _teamDao;
      }
    }
  }

  @Override
  public WordDao wordDao() {
    if (_wordDao != null) {
      return _wordDao;
    } else {
      synchronized(this) {
        if(_wordDao == null) {
          _wordDao = new WordDao_Impl(this);
        }
        return _wordDao;
      }
    }
  }
}
