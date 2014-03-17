package com.tomasvidhall.bettinghelp.database;

import android.app.ActionBar;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tomasvidhall.bettinghelp.models.Competition;
import com.tomasvidhall.bettinghelp.models.Match;
import com.tomasvidhall.bettinghelp.models.Team;

import java.sql.SQLException;

/**
 * Created by Tomas on 2014-03-16.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "footballStats.db";
    private static final int DATABASE_VERSION = 7;


    private Dao<Match,Integer> matchDao= null;
    private Dao<Team,Integer> teamDao = null;
    private Dao<Competition,Integer> competitionDao = null;


   public DatabaseHelper(Context c){
       super(c,DATABASE_NAME,null,DATABASE_VERSION);
       context = c;
   }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");

            //Create tables
            TableUtils.createTable(connectionSource,Team.class);
            TableUtils.createTable(connectionSource, Competition.class);
            TableUtils.createTable(connectionSource, Match.class);

            //Create DAOs for objects
            DaoManager.createDao(connectionSource,Match.class);
            DaoManager.createDao(connectionSource,Competition.class);
            DaoManager.createDao(connectionSource,Team.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Match.class, true);
            TableUtils.dropTable(connectionSource, Competition.class,true);
            TableUtils.dropTable(connectionSource, Team.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    public Dao<Team, Integer> getTeamDao() throws SQLException{
        if(teamDao == null){
            teamDao = getDao(Team.class);
        }
        return teamDao;
    }

    public Dao<Match, Integer> getMatchDao() throws SQLException{
        if(matchDao == null){
            matchDao = getDao(Match.class);
        }
        return matchDao;

    }

    public Dao<Competition,Integer> getCompetitionDao() throws SQLException{
        if(competitionDao == null){
            competitionDao = getDao(Competition.class);
        }
        return competitionDao;
    }
    @Override
    public void close() {
        super.close();
        teamDao = null;
        competitionDao = null;
        matchDao = null;
    }
}
