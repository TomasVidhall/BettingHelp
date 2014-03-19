package com.tomasvidhall.bettinghelp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.TextView;

import com.tomasvidhall.bettinghelp.activites.R;
import com.tomasvidhall.bettinghelp.models.Team;

import java.util.List;

/**
 * Created by Tomas on 2014-03-17.
 */
public class CarouselTeamAdapter extends ArrayAdapter<Team> {

   private List<Team> teams;
    private Context mContext;

    public CarouselTeamAdapter(Context context, int resource, List<Team> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.teams = objects;

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Team getItem(int position) {
        return teams.get(position);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position,convertView,parent);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = (View) inflater.inflate(R.layout.teamcarousel_row, null);
        }
        TextView text = (TextView)convertView.findViewById(R.id.teamcarousel_name);
        text.setText(teams.get(position).getName());
        return convertView;
    }
}
