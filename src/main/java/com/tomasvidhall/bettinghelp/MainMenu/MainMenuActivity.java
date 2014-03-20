package com.tomasvidhall.bettinghelp.MainMenu;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tomasvidhall.bettinghelp.activites.R;
import com.tomasvidhall.bettinghelp.database.DataBasePopulator;
import com.tomasvidhall.bettinghelp.database.DatabaseManager;
import com.tomasvidhall.bettinghelp.models.Match;
import com.tomasvidhall.bettinghelp.models.Team;

import java.util.List;

public class MainMenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseManager.init(this);
        setContentView(R.layout.activity_main_menu);

       //DataBasePopulator.populateDatabaseWithMatches(this);


        if (savedInstanceState == null) {

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
