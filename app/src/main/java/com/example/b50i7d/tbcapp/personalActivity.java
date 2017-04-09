package com.example.b50i7d.tbcapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by B50i7D on 3/21/2017.
 */
public class personalActivity extends AppCompatActivity {
    public  String user,luser,course,pass,db,phone,add;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    TextView username,password,address,dob,cell;
    public static String name = "fname";
    public static String last_name="lname";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity);

        Toolbar myToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sp= getApplicationContext().getSharedPreferences("name", MODE_PRIVATE);
        //String value=(sp.getString("name", "Default_Value"));
        user = sp.getString("fname", "default123");
        luser = sp.getString("lname", "default123");
        db = sp.getString("dob", "default123");
        phone = sp.getString("cell", "default123");
        add = sp.getString("address", "default123");
        pass = sp.getString("password", "default123");

        username = (TextView) findViewById(R.id.username);
        address = (TextView) findViewById(R.id.address);
        dob = (TextView) findViewById(R.id.dob);
        cell = (TextView) findViewById(R.id.cell);
        password = (TextView) findViewById(R.id.pass);

        username.setText(user + " " + luser);
        address.setText(add);
        dob.setText(db);
        cell.setText(phone);
        password.setText(pass);
        //Toast.makeText(personalActivity.this, user, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(personalActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
