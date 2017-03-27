package com.example.b50i7d.tbcapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.b50i7d.tbcapp.ChatActivity.name;

/**
 * Created by B50i7D on 3/21/2017.
 */
public class personalActivity extends AppCompatActivity {
    public  String user,course;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity);
        sp= getApplicationContext().getSharedPreferences("fname", MODE_PRIVATE);
        user = sp.getString(name, "default123");
        username = (TextView) findViewById(R.id.username);
        username.setText(user);
        Toast.makeText(personalActivity.this, user, Toast.LENGTH_SHORT).show();
    }
}
