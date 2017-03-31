package com.example.b50i7d.tbcapp;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragmet extends Fragment {

    private RecyclerView recycler;
    private String newNote;
    CardAdapter cardAdapter;


    List<String> notes = new ArrayList<String>();
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public NotesFragmet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_notes,container,false);

        Toolbar toolbar = (Toolbar)v.findViewById(R.id.notes_toolbar);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Add a note");
                final EditText input = new EditText(getContext());
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        newNote = input.getText().toString();
                        notes.add(0, newNote);
                        savetoSP();
                        cardAdapter.notifyItemInserted(0);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.show();
            }
        });


        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sp.edit();

        Set<String> notesSet = sp.getStringSet("notes", null);
        if(notesSet != null) {
            notes = new ArrayList<>(notesSet);
        }
        savetoSP();

        recycler = (RecyclerView)v.findViewById(R.id.recyclerView);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
            recycler.setLayoutManager(linearLayoutManager);
        cardAdapter = new CardAdapter(notes);
        recycler.setAdapter(cardAdapter);

        ItemClickSupport.addTo(recycler).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Delete?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        notes.remove(position);
                        savetoSP();
                        cardAdapter.notifyItemRemoved(position);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setNeutralButton("Underline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         
                    }
                });
                builder.show();
            }
        });
        return v;
    }


    public void savetoSP()
    {
        Set<String> set = new HashSet<String>();
        set.addAll(notes);
        editor.putStringSet("notes", set);
        editor.apply();
    }

    /**
     * A simple {@link Fragment} subclass.
     */
    public static class SettingsFragment extends Fragment {


        public SettingsFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_settings, container, false);
        }

    }
}
