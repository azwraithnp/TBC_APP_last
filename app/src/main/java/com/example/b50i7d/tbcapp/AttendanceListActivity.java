package com.example.b50i7d.tbcapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.Firebase;

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


        Firebase.setAndroidContext(AttendanceListActivity.this);



        try{
          Intent i = getIntent();
          Toast.makeText(AttendanceListActivity.this,i.getStringExtra("id"),Toast.LENGTH_SHORT).show();
          final int a = Integer.parseInt(i.getStringExtra("id"));
          if(a<=14321432){
              cv1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      String url = "https://theta-cable-138914.firebaseio.com/";
                      Intent i = new Intent(AttendanceListActivity.this,AttendanceActivity.class);
                      i.putExtra("url",url);
                      i.putExtra("sec", "a");
                      i.putExtra("idnum", "" + a);
                      startActivity(i);
                  }
              });
              cv2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      String url = "https://alish.firebaseio.com/";
                      Intent i = new Intent(AttendanceListActivity.this,AttendanceActivity.class);
                      i.putExtra("url",url);
                      i.putExtra("sec", "b");
                      startActivity(i);
                  }
              });
              cv3.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      String url = "https://tbcchat.firebaseio.com/";
                      Intent i = new Intent(AttendanceListActivity.this,AttendanceActivity.class);
                      i.putExtra("url",url);
                      i.putExtra("sec", "c");
                      startActivity(i);
                  }
              });
          }else{
              Toast.makeText(AttendanceListActivity.this,"you are not valid user",Toast.LENGTH_SHORT).show();
          }

      }catch(Exception e){
            Toast.makeText(AttendanceListActivity.this,"you are not valid user",Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(AttendanceListActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
