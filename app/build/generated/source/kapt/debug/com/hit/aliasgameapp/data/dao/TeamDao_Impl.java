package com.hit.aliasgameapp.data.dao;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.hit.aliasgameapp.data.model.Team;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class TeamDao_Impl implements TeamDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Team> __insertAdapterOfTeam;

  private final EntityDeleteOrUpdateAdapter<Team> __deleteAdapterOfTeam;

  private final EntityDeleteOrUpdateAdapter<Team> __updateAdapterOfTeam;

  public TeamDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfTeam = new EntityInsertAdapter<Team>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `teams` (`id`,`name`,`color`,`members`,`notes`,`imagePath`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Team entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        if (entity.getColor() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getColor());
        }
        if (entity.getMembers() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getMembers());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getNotes());
        }
        if (entity.getImagePath() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getImagePath());
        }
      }
    };
    this.__deleteAdapterOfTeam = new EntityDeleteOrUpdateAdapter<Team>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `teams` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Team entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfTeam = new EntityDeleteOrUpdateAdapter<Team>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `teams` SET `id` = ?,`name` = ?,`color` = ?,`members` = ?,`notes` = ?,`imagePath` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Team entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        if (entity.getColor() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getColor());
        }
        if (entity.getMembers() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getMembers());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getNotes());
        }
        if (entity.getImagePath() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getImagePath());
        }
        statement.bindLong(7, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final Team team, final Continuation<? super Unit> $completion) {
    if (team == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfTeam.insert(_connection, team);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object delete(final Team team, final Continuation<? super Unit> $completion) {
    if (team == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfTeam.handle(_connection, team);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final Team team, final Continuation<? super Unit> $completion) {
    if (team == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfTeam.handle(_connection, team);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public LiveData<List<Team>> getAllTeams() {
    final String _sql = "SELECT * FROM teams ORDER BY id DESC";
    return __db.getInvalidationTracker().createLiveData(new String[] {"teams"}, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfColor = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "color");
        final int _columnIndexOfMembers = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "members");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfImagePath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "imagePath");
        final List<Team> _result = new ArrayList<Team>();
        while (_stmt.step()) {
          final Team _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final String _tmpColor;
          if (_stmt.isNull(_columnIndexOfColor)) {
            _tmpColor = null;
          } else {
            _tmpColor = _stmt.getText(_columnIndexOfColor);
          }
          final String _tmpMembers;
          if (_stmt.isNull(_columnIndexOfMembers)) {
            _tmpMembers = null;
          } else {
            _tmpMembers = _stmt.getText(_columnIndexOfMembers);
          }
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpImagePath;
          if (_stmt.isNull(_columnIndexOfImagePath)) {
            _tmpImagePath = null;
          } else {
            _tmpImagePath = _stmt.getText(_columnIndexOfImagePath);
          }
          _item = new Team(_tmpId,_tmpName,_tmpColor,_tmpMembers,_tmpNotes,_tmpImagePath);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Team getTeamById(final int id) {
    final String _sql = "SELECT * FROM teams WHERE id = ?";
    return DBUtil.performBlocking(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfColor = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "color");
        final int _columnIndexOfMembers = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "members");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfImagePath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "imagePath");
        final Team _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final String _tmpColor;
          if (_stmt.isNull(_columnIndexOfColor)) {
            _tmpColor = null;
          } else {
            _tmpColor = _stmt.getText(_columnIndexOfColor);
          }
          final String _tmpMembers;
          if (_stmt.isNull(_columnIndexOfMembers)) {
            _tmpMembers = null;
          } else {
            _tmpMembers = _stmt.getText(_columnIndexOfMembers);
          }
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpImagePath;
          if (_stmt.isNull(_columnIndexOfImagePath)) {
            _tmpImagePath = null;
          } else {
            _tmpImagePath = _stmt.getText(_columnIndexOfImagePath);
          }
          _result = new Team(_tmpId,_tmpName,_tmpColor,_tmpMembers,_tmpNotes,_tmpImagePath);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
