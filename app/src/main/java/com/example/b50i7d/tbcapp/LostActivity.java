package com.example.b50i7d.tbcapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by B50i7D on 11/29/2016.
 */
public class LostActivity extends AppCompatActivity {
    ImageView sendbtn;
    TextView lost_item,date,place;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lost_activity);
          lost_item = (TextView) findViewById(R.id.item_type);
          date = (TextView) findViewById(R.id.date);
          place = (TextView) findViewById(R.id.place);
          img = (ImageView) findViewById(R.id.lostImg);
          sendbtn =(ImageView) findViewById(R.id.lostSend);


      }

}

