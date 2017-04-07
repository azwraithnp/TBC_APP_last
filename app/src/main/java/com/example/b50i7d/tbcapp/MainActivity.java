package com.example.b50i7d.tbcapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends ActionBarActivity {
    BottomBar btmbar;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] mPanelTitles;
    private ActionBarDrawerToggle mDrawerToggle;

    String firebaseURL = "https://student-details-80045.firebaseio.com/";

    public static String name = "fname";
    public static String last_name="lname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar myToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent i = getIntent();
        firebaseURL = firebaseURL + i.getStringExtra("id") + "/";
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("TBC");
        if(i.getStringExtra("notification") == null){
            Toast.makeText(MainActivity.this,"no notification",Toast.LENGTH_SHORT).show();
        }else{
            alertDialog.setMessage(i.getStringExtra("notification"));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }

        SharedPreferences pref = getApplicationContext().getSharedPreferences("name", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();


        Firebase.setAndroidContext(this);
        Firebase ref = new Firebase(firebaseURL);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("fname").getValue(String.class);
                editor.putString("fname", value);
                editor.commit();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        Firebase add = ref.child("address");
        add.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                editor.putString("address", value);
                editor.commit();
               // Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        Firebase lname = ref.child("lname");
        lname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                editor.putString("lname", value);
                editor.commit();
                //Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        Firebase dob = ref.child("dob");
        dob.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                editor.putString("dob", value);
                editor.commit();
                //Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Firebase cell = ref.child("cell");
        cell.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                editor.putString("cell", value);
                editor.commit();
                //Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        Firebase password = ref.child("password");
        password.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                editor.putString("password", value);
                editor.commit();
                //Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        mPanelTitles = new String[]{"College", "Personal", "Attendance", "Lost And Found", "About Us", "Settings"};
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
                    SettingActivity f = new SettingActivity();
                    myToolbar.setBackgroundColor(Color.parseColor("#2980b9"));
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
                if (menuItemId == R.id.Bottombaritemtwo) {
                    ChatActivity f = new ChatActivity();
                    myToolbar.setBackgroundColor(Color.parseColor("#3498db"));
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
                if (menuItemId == R.id.Bottombaritemthree) {
                    ClassSchedulesActivity f = new ClassSchedulesActivity();
                    myToolbar.setBackgroundColor(Color.parseColor("#48647c"));
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
                if (menuItemId == R.id.Bottombaritemfour) {
                    NotesFragmet f = new NotesFragmet();
                    myToolbar.setBackgroundColor(Color.parseColor("#F4D03F"));
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
                if (menuItemId == R.id.Bottombaritemfive) {
                    CalendarActivity f = new CalendarActivity();
                    myToolbar.setBackgroundColor(Color.parseColor("#303F9F"));
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
        btmbar.mapColorForTab(3, "#F4D03F");
        btmbar.mapColorForTab(4, "#303F9F");

        BottomBarBadge unread;
        unread = btmbar.makeBadgeForTabAt(3, "#ff0000", 13);
        //unread.show();


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
            case R.id.profile:
                Intent intent = new Intent(MainActivity.this, personalActivity.class);
                startActivity(intent);
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
                    startActivity(new Intent(MainActivity.this, CollegeActivity.class));
                    break;
                case 1:
                    Intent intent = new Intent(MainActivity.this, personalActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this, AttendanceListActivity.class));
                     break;

                case 3:
                    Intent intent1 = new Intent(MainActivity.this, LostAndFoundActivity.class);
                    startActivity(intent1);
                     break;

                case 4:
                    startActivity(new Intent(MainActivity.this, AboutActivity.class));
                    break;
            }
            setTitle(mPanelTitles[position]);
        }
    }

}
