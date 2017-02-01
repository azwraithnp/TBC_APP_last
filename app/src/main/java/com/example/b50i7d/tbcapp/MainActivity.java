package com.example.b50i7d.tbcapp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBarActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends ActionBarActivity {
    BottomBar btmbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btmbar = BottomBar.attach(this,savedInstanceState);
        btmbar.setItemsFromMenu(R.menu.menu_main, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if(menuItemId ==R.id.Bottombaritemone){
                    NewsFeedsActivity f = new NewsFeedsActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
                if(menuItemId ==R.id.Bottombaritemtwo){
                    ChatActivity f = new ChatActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
                if(menuItemId ==R.id.Bottombaritemthree){
                    ClassSchedulesActivity f = new ClassSchedulesActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
                if(menuItemId ==R.id.Bottombaritemfour){
                    NotesActivity f = new NotesActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
                if(menuItemId ==R.id.Bottombaritemfive){
                    CalendarActivity f = new CalendarActivity();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
        btmbar.mapColorForTab(0,"#2980b9");
        btmbar.mapColorForTab(1,"#3498db");
        btmbar.mapColorForTab(2,"#48647c");
        btmbar.mapColorForTab(3,"#e3a712");
        btmbar.mapColorForTab(4,"#303F9F");

        BottomBarBadge unread;
        unread = btmbar.makeBadgeForTabAt(3,"#ff0000",13);
        unread.show();



    }
}
