package com.example.b50i7d.tbcapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by B50i7D on 4/5/2017.
 */

public class AttendanceListActivity extends AppCompatActivity {
    CardView cv1,cv2,cv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendence_list_activity);
        cv1 = (CardView)findViewById(R.id.l4seca);
        cv2 = (CardView)findViewById(R.id.l4secb);
        cv3 = (CardView)findViewById(R.id.l4secc);

        Toolbar myToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String url = "https://student-details-80045.firebaseio.com/";
                String url2 = "https://tbcapp-1470055419551.firebaseio.com/";
                Intent i = new Intent(AttendanceListActivity.this,AttendanceActivity.class);
                i.putExtra("url",url);
                i.putExtra("url2", url2);
                startActivity(i);
            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://alish.firebaseio.com/";
                String url2 = "https://theta-cable-138914.firebaseio.com/";
                Intent i = new Intent(AttendanceListActivity.this,AttendanceActivity.class);
                i.putExtra("url",url);
                i.putExtra("url2", url2);
                startActivity(i);
            }
        });
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://tbcchat.firebaseio.com/";
                String url2 = "https://api-project-1-1139.firebaseio.com/";
                Intent i = new Intent(AttendanceListActivity.this,AttendanceActivity.class);
                i.putExtra("url",url);
                i.putExtra("url2", url2);
                startActivity(i);
            }
        });
    }
}
