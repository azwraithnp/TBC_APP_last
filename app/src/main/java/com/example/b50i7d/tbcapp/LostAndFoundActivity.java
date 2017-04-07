package com.example.b50i7d.tbcapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by B50i7D on 4/2/2017.
 */

public class LostAndFoundActivity extends AppCompatActivity {
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
}
