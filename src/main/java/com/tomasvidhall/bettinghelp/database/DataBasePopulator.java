package com.tomasvidhall.bettinghelp.database;

import android.content.Context;
import android.content.res.AssetManager;

import com.tomasvidhall.bettinghelp.models.Match;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 2014-03-17.
 */
public class DataBasePopulator {

    private static String[] paths = {"csv/PremierLeague/PL1314.csv","csv/PremierLeague/PL1213.csv","csv/PremierLeague/PL1112.csv","csv/PremierLeague/PL1011.csv","csv/PremierLeague/PL0910.csv","csv/PremierLeague/PL0809.csv"};

    public static void populateDatabaseWithMatches(Context c){
        List<Match> matches = readCsvMatchFiles(c);

        for(Match m: matches){
            DatabaseManager.getInstance().addNewMatch(m);
        }
    }

    private static List<Match> readCsvMatchFiles(Context c) {
        List<Match> matches = new ArrayList<>();
        AssetManager am = c.getAssets();
        InputStream path = null;
        try {
            for (int i = 0;i < paths.length ; i++){
            path = am.open(paths[i]);
            matches.addAll(readCsvMatchFile(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return matches;
    }

    private static List<Match> readCsvMatchFile(InputStream i) {
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
}
