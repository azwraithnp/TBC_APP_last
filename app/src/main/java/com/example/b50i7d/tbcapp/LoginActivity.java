package com.example.b50i7d.tbcapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText emailText, password;
    Button login;

    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = (EditText)findViewById(R.id.input_email);
        password = (EditText)findViewById(R.id.input_password);
        login = (Button)findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              login();
            }
        });

    }

    public void login()
    {
        Log.d(TAG, "Login");

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

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
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
        boolean valid = true;

        String id = emailText.getText().toString();
        String pass = password.getText().toString();

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
}
