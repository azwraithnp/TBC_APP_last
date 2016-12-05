package com.example.b50i7d.tbcapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by B50i7D on 11/28/2016.
 */
public class ClassSchedulesActivity extends Fragment {
    TextView tv11,tv12,tv21,tv22,tv31,tv32,tv41,tv42,tv51,tv52;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.classschedules_activity,container,false);
        tv11 = (TextView)v.findViewById(R.id.txt11);
        tv12 = (TextView)v.findViewById(R.id.txt12);
        tv21 = (TextView)v.findViewById(R.id.txt21);
        tv22 = (TextView)v.findViewById(R.id.txt22);
        tv31 = (TextView)v.findViewById(R.id.txt31);
        tv32 = (TextView)v.findViewById(R.id.txt32);
        tv41 = (TextView)v.findViewById(R.id.txt41);
        tv42 = (TextView)v.findViewById(R.id.txt42);
        tv51 = (TextView)v.findViewById(R.id.txt51);
        tv52 = (TextView)v.findViewById(R.id.txt52);

        Firebase.setAndroidContext(getActivity());

        Firebase mRef = new Firebase("https://blazing-heat-4318.firebaseio.com/");
        Firebase scheduleRef11 = mRef.child("sub11");
        scheduleRef11.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tv11.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                tv11.setText("loading error");
            }
        });
        Firebase scheduleRef12 = mRef.child("sub12");
        scheduleRef12.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tv12.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        Firebase scheduleRef21 = mRef.child("sub21");
        scheduleRef21.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tv21.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        Firebase scheduleRef22 = mRef.child("sub22");
        scheduleRef22.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tv22.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        Firebase scheduleRef31 = mRef.child("sub31");
        scheduleRef31.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tv31.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        Firebase scheduleRef32 = mRef.child("sub32");
        scheduleRef32.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tv32.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        Firebase scheduleRef41 = mRef.child("sub41");
        scheduleRef41.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tv41.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        Firebase scheduleRef42 = mRef.child("sub42");
        scheduleRef42.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tv42.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        Firebase scheduleRef51 = mRef.child("sub51");
        scheduleRef51.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tv51.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        Firebase scheduleRef52 = mRef.child("sub52");
        scheduleRef52.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tv52.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        return v;
    }
}
