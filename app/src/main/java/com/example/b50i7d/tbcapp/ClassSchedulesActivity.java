package com.example.b50i7d.tbcapp;

import android.content.SharedPreferences;
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

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by B50i7D on 11/28/2016.
 */
public class ClassSchedulesActivity extends Fragment {

    public static String sub11 = "sub11_value";
    public static String sub12 = "sub12_value";
    public static String sub21 = "sub21_value";
    public static String sub22 = "sub22_value";
    public static String sub31 = "sub31_value";
    public static String sub32 = "sub32_value";
    public static String sub41 = "sub41_value";
    public static String sub42 = "sub42_value";
    public static String sub51 = "sub51_value";
    public static String sub52 = "sub52_value";


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
        SharedPreferences sp = getContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();


        tv11.setText(sp.getString(sub11,""));
        tv12.setText(sp.getString(sub12,""));
        tv21.setText(sp.getString(sub21,""));
        tv22.setText(sp.getString(sub22,""));
        tv31.setText(sp.getString(sub31,""));
        tv32.setText(sp.getString(sub32,""));
        tv41.setText(sp.getString(sub41,""));
        tv42.setText(sp.getString(sub42,""));
        tv51.setText(sp.getString(sub51,""));
        tv52.setText(sp.getString(sub52,""));
        //https://l4sectionc.firebaseio.com/

        sp= getContext().getSharedPreferences("name", MODE_PRIVATE);
        //String value=(sp.getString("name", "Default_Value"));
        String user = sp.getString("sec", "default123");

        Firebase mRef = null;
        if(user.equals("a")){
             mRef = new Firebase("https://blazing-heat-4318.firebaseio.com/");
        }
        if(user.equals("b")){
             mRef = new Firebase("https://l4sectionb.firebaseio.com/");
        }
        if(user.equals("c")){
             mRef = new Firebase("https://l4sectionc.firebaseio.com/");
        }

        Firebase scheduleRef11 = mRef.child("sub11");

        scheduleRef11.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                editor.putString("sub11_value",value);
                tv11.setText(value);
                editor.commit();
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
                editor.putString("sub12_value",value);
                tv12.setText(value);
                editor.commit();
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
                editor.putString("sub21_value",value);
                tv21.setText(value);
                editor.commit();
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
                editor.putString("sub22_value",value);
                tv22.setText(value);
                editor.commit();
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
                editor.putString("sub31_value",value);
                tv31.setText(value);
                editor.commit();
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
                editor.putString("sub32_value",value);
                tv32.setText(value);
                editor.commit();
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
                editor.putString("sub41_value",value);
                tv41.setText(value);
                editor.commit();            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        Firebase scheduleRef42 = mRef.child("sub42");
        scheduleRef42.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                editor.putString("sub42_value",value);
                tv42.setText(value);
                editor.commit();
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
                editor.putString("sub51_value",value);
                tv51.setText(value);
                editor.commit();
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
                editor.putString("sub52_value",value);
                tv52.setText(value);
                editor.commit();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        return v;
    }
}
