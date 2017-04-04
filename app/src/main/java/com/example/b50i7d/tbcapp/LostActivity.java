package com.example.b50i7d.tbcapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by B50i7D on 11/29/2016.
 */
public class LostActivity extends AppCompatActivity {
    private List<LostMessage> list = new ArrayList<>();
    private RecyclerView recycleView;
    private LostAdapter cAdapter;
    private int count;

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lost_activity);
        Firebase.setAndroidContext(getApplicationContext());
        Firebase ref = new Firebase(Config.FIREBASE_URL_bba);

        recycleView = (RecyclerView)findViewById(R.id.recycler_view);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(cAdapter);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                LostMessage lostMessage = dataSnapshot.getValue(LostMessage.class);
                list.add(lostMessage);
                count = list.size();
                cAdapter.notifyDataSetChanged();
                if(!(list.size() == 0))
                {
                    recycleView.scrollToPosition(list.size() - 1);
                }

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
        });
    }

}

