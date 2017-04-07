package com.example.b50i7d.tbcapp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by B50i7D on 4/7/2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
   private static final String REG_TOKEN = "REG_TOKEN";
    @Override
    public void onTokenRefresh() {
        String recent_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN,recent_token);
    }
}
