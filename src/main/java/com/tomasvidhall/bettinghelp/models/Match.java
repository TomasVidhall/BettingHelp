package com.tomasvidhall.bettinghelp.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tomasvidhall.bettinghelp.database.DatabaseManager;

import java.util.Date;

/**
 * Created by Tomas on 2014-03-16.
 */
@DatabaseTable
public class Match {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String result;
    @DatabaseField(foreignAutoRefresh = true, foreign = true, canBeNull = false, uniqueCombo = true)
    private Team homeTeam;
    @DatabaseField(foreignAutoRefresh = true, foreign = true, canBeNull = false, uniqueCombo = true)
    private Team awayTeam;
    @DatabaseField(foreignAutoRefresh = true, foreign = true)
    private Competition competition;
    @DatabaseField
    private String winner;
    @DatabaseField(uniqueCombo = true)
    private Date date;

    public Match() {

    }

    public Match(String result, String awayTeam, String homeTeam, String competition, String date, String winner) {
        this.result = result;
        this.awayTeam = new Team(awayTeam);
        this.homeTeam = new Team(homeTeam);
        this.competition = new Competition(competition);
        this.date = new Date(date);
        this.winner = winner;
        this.awayTeam.save();
        this.homeTeam.save();
    }

    public int getId() {
        return id;
    }

    public String getResult() {
        return result;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Team getOtherTeam(String name) {
        Team team = DatabaseManager.getInstance().getTeam(name);
        return getOtherTeam(team);
    }

    private Team getOtherTeam(Team team) {
        Team otherTeam = null;
        if (this.getHomeTeam().equals(team)) {
            return this.getAwayTeam();
        } else if (this.getAwayTeam().equals(team)) {
            return this.getHomeTeam();
        } else {
            return null;
        }
    }

    public Competition getCompetition() {
        return competition;
    }

    public Date getDate() {
        return date;
    }

    public String getWinner() {
        return winner;
    }
}
