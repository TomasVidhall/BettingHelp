package com.tomasvidhall.bettinghelp.fragments;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Spinner;

import com.tomasvidhall.bettinghelp.activites.DisplayMatchesActivity;
import com.tomasvidhall.bettinghelp.activites.R;
import com.tomasvidhall.bettinghelp.adapters.CarouselTeamAdapter;
import com.tomasvidhall.bettinghelp.database.DatabaseManager;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class MainMenuFragment extends Fragment {


    public MainMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);
        Button matchesButton = (Button) rootView.findViewById(R.id.mainmenu_matchesButton);
        matchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToDisplayMatches();
            }
        });

        Gallery teamGallery = (Gallery) rootView.findViewById(R.id.mainmenu_teamGallery);
        teamGallery.setAdapter(new CarouselTeamAdapter(getActivity(),android.R.layout.simple_list_item_1,DatabaseManager.getInstance().getAllTeams()));
        return rootView;
    }

    private void switchToDisplayMatches() {
        Intent intent = new Intent(getActivity(), DisplayMatchesActivity.class);
        startActivity(intent);
    }


}
