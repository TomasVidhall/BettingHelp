package com.tomasvidhall.bettinghelp.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Tomas on 2014-03-16.
 */
@DatabaseTable
public class Competition {


    @DatabaseField (id = true)
    private String name;

    public Competition(){

    }

    public Competition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
