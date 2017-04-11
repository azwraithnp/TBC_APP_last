package com.example.b50i7d.tbcapp;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

/**
 * Created by B50i7D on 11/29/2016.
 */
public class SettingActivity extends AppCompatActivity {

    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);

        Firebase.setAndroidContext(SettingActivity.this);

        final Firebase ChatRef = new Firebase(Config.FIREBASE_URL_bba);
        final Firebase AttendanceRef = new Firebase("https://tbcapp-1470055419551.firebaseio.com/");


        AlertDialog.Builder alert = new AlertDialog.Builder(this);


        alert.setTitle("Authorisation");
        alert.setMessage("Enter the admin password!");

        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String value = input.getText().toString();
                if(value.equals("abc"))
                {
                   dialogInterface.cancel();
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        alert.setCancelable(false);

        alert.create();

        alert.show();




        button1 = (Button)findViewById(R.id.deleteatt);
        button2 = (Button)findViewById(R.id.deletechat);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingActivity.this, "Deleting...", Toast.LENGTH_SHORT).show();
                ChatRef.removeValue();

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingActivity.this, "Deleting...", Toast.LENGTH_SHORT).show();
                AttendanceRef.removeValue();
            }
        });



    }




}

