package com.example.b50i7d.tbcapp;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.attr.bitmap;
import static com.example.b50i7d.tbcapp.R.id.sendButton;
import static java.security.AccessController.getContext;

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

