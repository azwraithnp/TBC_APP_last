package com.example.b50i7d.tbcapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by B50i7D on 3/30/2017.
 */

public class AttendanceActivity extends AppCompatActivity implements AttendanceList{

    RecyclerView recycleView;
    AttendanceAdapter attendanceAdapter;
    List<AttendanceObject> list = new ArrayList<>();
    List<AttendanceObject> list2 = new ArrayList<>();
    TextView txt,date;
    String firebaseURL2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Intent intent = getIntent();
        String firebaseURL = intent.getExtras().getString("url");
        firebaseURL2 = intent.getStringExtra("url2");
        txt = (TextView)findViewById(R.id.textsample);
        date = (TextView) findViewById(R.id.attendance_date);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        date.setText(formattedDate);
        Toolbar myToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Firebase.setAndroidContext(AttendanceActivity.this);

        Firebase ref = new Firebase(firebaseURL);

        Toast.makeText(AttendanceActivity.this, "Loading...", Toast.LENGTH_SHORT).show();

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                Student student = dataSnapshot.getValue(Student.class);
                AttendanceObject attendanceObject = new AttendanceObject();
                attendanceObject.setFname(student.getFname());
                attendanceObject.setLname(student.getLname());
                attendanceObject.setEmail(student.getEmail());
                attendanceObject.setId(dataSnapshot.getKey());
                attendanceObject.setAttendance("present");
                attendanceObject.setDate(sdf.format(new Date()));
                list.add(attendanceObject);
                attendanceAdapter.notifyDataSetChanged();

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

        recycleView = (RecyclerView)findViewById(R.id.attendence_rv);
        attendanceAdapter = new AttendanceAdapter(list, AttendanceActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AttendanceActivity.this);
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(attendanceAdapter);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase ref2 = new Firebase(firebaseURL2);

                ref2.removeValue();

                Toast.makeText(AttendanceActivity.this, "Pushing", Toast.LENGTH_SHORT).show();

                for(int i=0;i<list2.size();i++)
                {
                    ref2.push().setValue(list2.get(i));
                }
            }
        });


    }

    @Override
    public void onClick(List<AttendanceObject> value) {

        list2 = value;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(AttendanceActivity.this, AttendanceListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
