package com.example.b50i7d.tbcapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by B50i7D on 3/30/2017.
 */

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder>{
    private List<AttendanceObject> attendance = new ArrayList<>();
    private AttendanceList callback;


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendence_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final int count = position;
        final AttendanceObject attendanceObject = attendance.get(position);
        holder.txt1.setText(attendanceObject.getFname() + " " + attendanceObject.getLname());
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(attendanceObject.getAttendance().equals("present"))
                    {
                        attendanceObject.setAttendance("absent");
                        attendance.set(count, attendanceObject);
                        holder.img2.setImageResource(R.drawable.absent);
                    }
                    else if(attendanceObject.getAttendance().equals("absent"))
                    {
                        attendanceObject.setAttendance("late");
                        attendance.set(count, attendanceObject);
                        holder.img2.setImageResource(R.drawable.late);
                    }
                    else
                    {
                        attendanceObject.setAttendance("present");
                        attendance.set(count, attendanceObject);
                        holder.img2.setImageResource(R.drawable.present);
                    }
            }
        });

        if(position >= attendance.size() - 1)
        {
            callback.onClick(attendance);
        }

    }

    @Override
    public int getItemCount() {
        return attendance.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView img1, img2;
        public TextView txt1;
        public LinearLayout mainlayout;

        public MyViewHolder(View view)
        {
            super(view);
            img1 = (ImageView)view.findViewById(R.id.student_img);
            img2 = (ImageView)view.findViewById(R.id.attendicon);
            txt1 = (TextView)view.findViewById(R.id.student_name);
            mainlayout = (LinearLayout)view.findViewById(R.id.mainRow);

        }


    }

    public AttendanceAdapter(List<AttendanceObject> attendances, AttendanceList listcallback)
    {
        attendance = attendances;
        callback = listcallback;

    }



}
