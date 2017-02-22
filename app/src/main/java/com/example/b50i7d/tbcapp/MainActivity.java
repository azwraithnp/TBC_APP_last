package com.example.b50i7d.tbcapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends ActionBarActivity {
    BottomBar btmbar;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] mPanelTitles;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mPanelTitles = new String[]{"College", "Personal", "Timetable", "Finance", "About Us", "Settings"};
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.leftlist);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mPanelTitles) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(getResources().getColor(R.color.black));
                return view;
            }
        };

        mDrawerList.setAdapter(adapter1);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);


        btmbar = BottomBar.attach(this, savedInstanceState);
        btmbar.setItemsFromMenu(R.menu.menu_main, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.Bottombaritemone) {
                    NewsFeedsActivity f = new NewsFeedsActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
                if (menuItemId == R.id.Bottombaritemtwo) {
                    ChatActivity f = new ChatActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
                if (menuItemId == R.id.Bottombaritemthree) {
                    ClassSchedulesActivity f = new ClassSchedulesActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
                if (menuItemId == R.id.Bottombaritemfour) {
                    NotesFragmet f = new NotesFragmet();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
                if (menuItemId == R.id.Bottombaritemfive) {
                    CalendarActivity f = new CalendarActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
        btmbar.mapColorForTab(0, "#2980b9");
        btmbar.mapColorForTab(1, "#3498db");
        btmbar.mapColorForTab(2, "#48647c");
        btmbar.mapColorForTab(3, "#e3a712");
        btmbar.mapColorForTab(4, "#303F9F");

        BottomBarBadge unread;
        unread = btmbar.makeBadgeForTabAt(3, "#ff0000", 13);
        unread.show();


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId())
        {
            case R.id.help:
                NotesFragmet.SettingsFragment f = new NotesFragmet.SettingsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarmenu, menu);
        return true;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    //TODO: Insert the proper classes
                    NotesFragmet.SettingsFragment f = new NotesFragmet.SettingsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                    break;
                case 4:
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    break;

            }
            setTitle(mPanelTitles[position]);
        }

    }

}
