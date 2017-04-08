package com.example.b50i7d.tbcapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText emailText, password;
    Button login;

    private ProgressDialog pDialog;

    private static final String TAG = "LoginActivity";

    String firebaseURL = "https://student-details-80045.firebaseio.com/";

    String ids[] = new String[100];

    Firebase ref;

    boolean valid;

    int count = 0;

    String id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = (EditText)findViewById(R.id.input_email);
        password = (EditText)findViewById(R.id.input_password);
        login = (Button)findViewById(R.id.btn_login);

        Firebase.setAndroidContext(LoginActivity.this);

        ref = new Firebase(firebaseURL);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ids[count] = dataSnapshot.getKey();
                count++;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        };
        ref.addChildEventListener(childEventListener);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = emailText.getText().toString();
                    login();
            }
        });

    }

    public void login() {


        if (!validate()) {

            onLoginFailed();
            return;
        }

        login.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();


        String email = emailText.getText().toString();
        String pass = password.getText().toString();

        // On complete call either onLoginSuccess or onLoginFailed
        onLoginSuccess();


    }


    public void onLoginSuccess() {
        login.setEnabled(true);
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        i.putExtra("id", emailText.getText().toString());
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }


    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        login.setEnabled(true);
    }

    public boolean validate()
    {

        final String id = emailText.getText().toString();
        String pass = password.getText().toString();


        for(int i=0;i<count;i++)
        {
            if(ids[i].equals(id))
            {
                valid = true;
            }
        }

        if(id.isEmpty() || id.length() < 10)
        {
            emailText.setError("Enter a valid TBC ID number!");
            valid = false;
        }
        else
        {
            emailText.setError(null);
        }

        if (!(pass.equals("abc"))) {
            password.setError("Incorrect pass");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        valid = false;
    }
}

