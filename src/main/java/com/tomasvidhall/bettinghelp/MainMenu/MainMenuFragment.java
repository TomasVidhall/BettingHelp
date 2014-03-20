package com.tomasvidhall.bettinghelp.MainMenu;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Gallery;

import com.tomasvidhall.bettinghelp.DisplayMatchesActivity;
import com.tomasvidhall.bettinghelp.activites.R;
import com.tomasvidhall.bettinghelp.adapters.CarouselCompetitionAdapter;
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
        Gallery competitionGallery = (Gallery) rootView.findViewById(R.id.mainmenu_competitionGallery);
        teamGallery.setAdapter(new CarouselTeamAdapter(getActivity(),android.R.layout.simple_list_item_1,DatabaseManager.getInstance().getAllTeams()));
        teamGallery.setSelection(Integer.MAX_VALUE / 2);

        //competitionGallery.setAdapter(new CarouselCompetitionAdapter(getActivity(),android.R.layout.simple_list_item_1, DatabaseManager.getInstance().getAllCompetitions()));
        //competitionGallery.setSelection(Integer.MAX_VALUE/2);
        return rootView;
    }

    private void switchToDisplayMatches() {
        Intent intent = new Intent(getActivity(), DisplayMatchesActivity.class);
        startActivity(intent);
    }


}
