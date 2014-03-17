package com.tomasvidhall.bettinghelp.database;

import android.content.Context;
import android.content.res.AssetManager;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.stmt.Where;
import com.tomasvidhall.bettinghelp.models.Competition;
import com.tomasvidhall.bettinghelp.models.Match;
import com.tomasvidhall.bettinghelp.models.Team;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 2014-03-16.
 */
public class DatabaseManager {

    static private DatabaseManager instance;

    private DatabaseHelper helper = null;

    public DatabaseManager(Context ctx) {
        helper = new DatabaseHelper(ctx);
    }

    static public void init(Context ctx) {
        if (null == instance) {
            instance = new DatabaseManager(ctx);
        }
    }

    static public DatabaseManager getInstance() {
        return instance;
    }

    public DatabaseHelper getHelper(Context c) {
        if (helper == null) {
            helper = OpenHelperManager.getHelper(c, DatabaseHelper.class);
        }
        return helper;
    }

    public void releaseHelper(DatabaseHelper helper) {
        if (helper != null) {
            OpenHelperManager.releaseHelper();
            helper = null;
        }
    }

    // All the Dao functions of all MDL objects are in this class, for example:


    public boolean addNewTeam(Team team) {
        try {
            helper.getTeamDao().create(team);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addNewTeam(String name) {
        Team t = new Team(name);
        return addNewTeam(t);
    }

    public boolean addNewCompetition(Competition c) {
        try {
            helper.getCompetitionDao().create(c);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addNewCompetition(String name) {
        Competition c = new Competition(name);
        return addNewCompetition(c);
    }

    public boolean addNewMatch(Match m) {
        try {
            helper.getMatchDao().create(m);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addNewMatch(String result, String awayTeam, String homeTeam, String competition, String date, String winner) {
        Match m = new Match(result, awayTeam, homeTeam, competition, date, winner);
        return addNewMatch(m);
    }

    public List<Match> getAllMatches() {
        List<Match> matches = null;
        try {
            matches = helper.getMatchDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public List<Team> getAllTeams() {
        List<Team> teams = null;
        try {
            teams = helper.getTeamDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    public List<Competition> getAllCompetitions() {
        List<Competition> competitions = null;
        try {
            competitions = helper.getCompetitionDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return competitions;
    }



    public List<Match> readCsvMatchFiles(Context c) {
        List<Match> matches = null;
        AssetManager am = c.getAssets();
        InputStream path = null;
        try {
            path = am.open("csv/PremierLeague/PL1314.csv");
            matches = readCsvMatchFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return matches;
    }

    private List<Match> readCsvMatchFile(InputStream i) {
        Match m = null;
        List<Match> matches = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(i));
            //SKIP HEADERS
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                String competition = split[0];
                String date = split[1];
                String homeTeam = split[2];
                String awayTeam = split[3];
                String result = split[4] + " - " + split[5];
                String winner = split[6];

                m = new Match(result, awayTeam, homeTeam, competition, date, winner);
                matches.add(m);
            }
            br.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public boolean getTeam(String name) {
        List<Team> ts = null;
        try {
            ts = helper.getTeamDao().queryForEq("name", name);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (ts.size() > 0) {
            return true;
        } else {
            return false;

        }
    }

    public List<Match> getAllMatchesFromTeam(Team t){
       return getAllMatchesFromTeam(t.getName());
    }

    public List<Match> getAllMatchesFromTeam(String team) {
        List<Match> matches = null;
        try {
            matches = helper.getMatchDao().queryBuilder().where().eq("homeTeam_id",team).or().eq("awayTeam_id",team).query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public List<Match> getAllMatchesWhereTeamWon(String team){
        List<Match> matches = null;
        try{
            Where where = helper.getMatchDao().queryBuilder().where();
            matches = where.or(where.and(where.eq("homeTeam_id",team),where.eq("winner","H")),(where.and(where.eq("awayTeam_id",team),where.eq("winner","A")))).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public List<Match> getAllMatchesWhereTeamWon(Team t){
        return getAllMatchesFromTeam(t.getName());
    }
}
