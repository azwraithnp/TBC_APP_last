package com.example.b50i7d.tbcapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;
import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by B50i7D on 11/28/2016.
 */
public class CalendarActivity extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    CustomCalendarView calendarView;
    ListView list;
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_calendar,container,false);
        calendarView = (CustomCalendarView) v.findViewById(R.id.calendar_view);
        final Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        calendarView.setShowOverflowDate(false);
        calendarView.refreshCalendar(currentCalendar);

        list = (ListView)v.findViewById(R.id.listView);

        String[] holidays = {"July 10: Holiday day 1", "July 16: Holiday day 2", "July 19: Holiday day 3", "July 23: Holiday day 4", "July 28: Holiday day 5"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, holidays){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(getResources().getColor(R.color.black));
                return view;
            }
        };

        list.setAdapter(adapter);

        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                String dt = "";
                dt = dt + date.toString().charAt(8) + date.toString().charAt(9);
                if(dt.equals("10"))
                {
                    Toast.makeText(getActivity(), "Holiday day 1", Toast.LENGTH_SHORT).show();
                }
                else if(dt.equals("16"))
                {
                    Toast.makeText(getActivity(), "Holiday day 2", Toast.LENGTH_SHORT).show();
                }
                else if(dt.equals("19"))
                {
                    Toast.makeText(getActivity(), "Holiday day 3", Toast.LENGTH_SHORT).show();
                }
                else if(dt.equals("23"))
                {
                    Toast.makeText(getActivity(), "Holiday day 4", Toast.LENGTH_SHORT).show();
                }
                else if(dt.equals("28"))
                {
                    Toast.makeText(getActivity(), "Holiday day 5", Toast.LENGTH_SHORT).show();
                }
                decorateAll(currentCalendar);
            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
                Toast.makeText(getActivity(), df.format(date), Toast.LENGTH_SHORT).show();
            }
        });

        decorateAll(currentCalendar);
        return v;
    }

    public void decorateAll(Calendar currentCalendar)
    {
        List decorators = new ArrayList<>();
        decorators.add(new ColorDecorator());
        calendarView.setDecorators(decorators);
        calendarView.refreshCalendar(currentCalendar);
    }


    private class ColorDecorator implements DayDecorator {

        @Override
        public void decorate(DayView cell) {
            int color = Color.argb(255, 127, 255, 212);
            String date = cell.getDate().toString();
            String day = "";
            day = day + date.charAt(8) + date.charAt(9);
            if(day.equals("10") || day.equals("16") || day.equals("19") || day.equals("23") || day.equals("28")) {
                cell.setBackgroundColor(color);
            }
        }
    }

    }

