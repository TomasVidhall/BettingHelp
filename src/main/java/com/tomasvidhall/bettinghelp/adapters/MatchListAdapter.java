package com.tomasvidhall.bettinghelp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tomasvidhall.bettinghelp.models.Match;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 2014-03-16.
 */
public class MatchListAdapter extends ArrayAdapter<Match> {

    private static LayoutInflater inflater = null;
    private Context context;
    ArrayList<String> awayTeams = null;
    ArrayList<String> homeTeams = null;
    ArrayList<String> results = null;
    public MatchListAdapter(Context context, int resource, List<Match> objects) {
        super(context, resource, objects);
        this.context = context;
        homeTeams = new ArrayList<>();
        awayTeams = new ArrayList<>();
        results = new ArrayList<>();
        for(Match m : objects){
            homeTeams.add(m.getHomeTeam().getName());
            awayTeams.add(m.getAwayTeam().getName());
            results.add(m.getResult());
        }
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = (View) inflater.inflate(com.tomasvidhall.bettinghelp.activites.R.layout.matchlist_row, null);
        }
        TextView awayTeam = (TextView) convertView.findViewById(com.tomasvidhall.bettinghelp.activites.R.id.list_awayTeam);
        TextView homeTeam = (TextView) convertView.findViewById(com.tomasvidhall.bettinghelp.activites.R.id.list_homeTeam);
        TextView result = (TextView) convertView.findViewById(com.tomasvidhall.bettinghelp.activites.R.id.list_result);

        awayTeam.setText(awayTeams.get(position));
        homeTeam.setText(homeTeams.get(position));
        result.setText(results.get(position));

        return convertView;
    }
}
