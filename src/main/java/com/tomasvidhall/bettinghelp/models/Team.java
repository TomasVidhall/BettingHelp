package com.tomasvidhall.bettinghelp.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tomasvidhall.bettinghelp.database.DatabaseManager;

/**
 * Created by Tomas on 2014-03-16.
 */
@DatabaseTable
public class Team {

    @DatabaseField (id = true)
    private String name;

    public Team(){

    }

    public Team(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void save() {

        if(!DatabaseManager.getInstance().getTeam(this.name)){
            DatabaseManager.getInstance().addNewTeam(this);
        }
    }
}
