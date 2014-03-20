package com.tomasvidhall.bettinghelp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tomasvidhall.bettinghelp.activites.R;
import com.tomasvidhall.bettinghelp.models.Competition;
import com.tomasvidhall.bettinghelp.models.Team;

import java.util.List;

/**
 * Created by Administratör on 2014-03-19.
 */
public class CarouselCompetitionAdapter extends ArrayAdapter<Competition> {
    private List<Competition> competitions;
    private Context mContext;

    public CarouselCompetitionAdapter(Context context, int resource, List<Competition> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.competitions = objects;

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Competition getItem(int position) {
        if(position < competitions.size()){
            return competitions.get(position);
        }
        else{
            return competitions.get(position % competitions.size());
        }
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position,convertView,parent);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = (View) inflater.inflate(R.layout.teamcarousel_row, null);
        }
        TextView text = (TextView)convertView.findViewById(R.id.teamcarousel_name);
        text.setText(getItem(position).getName());
        return convertView;
    }
}


