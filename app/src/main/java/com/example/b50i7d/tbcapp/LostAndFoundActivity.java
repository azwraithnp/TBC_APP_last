package com.example.b50i7d.tbcapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by B50i7D on 4/2/2017.
 */

public class LostAndFoundActivity extends AppCompatActivity {
    public  String user,luser,course,pass,db,phone,add;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    CardView cv_1,cv_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found);
        Toolbar myToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sp= getApplicationContext().getSharedPreferences("name", MODE_PRIVATE);
        //String value=(sp.getString("name", "Default_Value"));
        user = sp.getString("fname", "default123");
        luser = sp.getString("lname", "default123");
        db = sp.getString("dob", "default123");
        phone = sp.getString("cell", "default123");
        add = sp.getString("address", "default123");
        pass = sp.getString("password", "default123");
        TextView tv = (TextView) findViewById(R.id.name_of_student);



        tv.setText(user + " " + luser);

        cv_1 = (CardView)findViewById(R.id.lost_cv);
        cv_2 = (CardView) findViewById(R.id.found_cv);

        cv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LostAndFoundActivity.this,LostActivity.class);
                startActivity(i);
            }
        });

        cv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LostAndFoundActivity.this,NewsFeedsActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(LostAndFoundActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
