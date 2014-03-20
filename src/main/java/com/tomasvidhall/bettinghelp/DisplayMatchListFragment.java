package com.tomasvidhall.bettinghelp;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tomasvidhall.bettinghelp.activites.R;
import com.tomasvidhall.bettinghelp.adapters.MatchListAdapter;
import com.tomasvidhall.bettinghelp.database.DatabaseManager;


/**
 *
 */
public class DisplayMatchListFragment extends Fragment {


    public DisplayMatchListFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView = inflater.inflate(R.layout.fragment_display_match_list, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.displaymatchesfragment_list);
        MatchListAdapter adapter = new MatchListAdapter(getActivity(),R.layout.matchlist_row, DatabaseManager.getInstance().getAllMatchesWhereTeamWon("Arsenal"));
        listView.setAdapter(adapter);
        return rootView;
    }


}
