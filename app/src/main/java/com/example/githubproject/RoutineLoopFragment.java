package com.example.githubproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RoutineLoopFragment extends Fragment {
    int tts = 0;
    int reps = 0;
    int sets = 0;
    int count = 0;
    int array_set = 0;
    Bundle bundle;
    RoutineRunFragment routineRunFragment;
    RestTimers restTimers;
    String routine_name;


    public static RoutineLoopFragment newInstance() {
        return new RoutineLoopFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine_loop, container, false);
        routineRunFragment = new RoutineRunFragment();
        restTimers = new RestTimers();
        routine_name = this.getArguments().getString("ROUTINE_NAME");

        Routine_DBHelper routine_dbHelper = new Routine_DBHelper(getContext(), 1);
        SQLiteDatabase db = routine_dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT Exercise_Name, Time, TTS, Reps, Sets FROM Routine WHERE Routine_Name = '" + routine_name + "'", null);
//        array_set = routine_dbHelper.getCount(routine_name);
//
//        workout_names = new String[array_set];
//        while(cursor.moveToNext()) {
//            workout_names[count] = cursor.getString(0);
//            tts = cursor.getInt(2);
//            reps = cursor.getInt(3);
//            sets = cursor.getInt(4);
//            count++;
//        }
//
//        for(int i = 0; i < workout_names.length; i++) {
//            for(int j = 0; j < sets; j++) {
//                Log.v("씨발?", "|||" + i + "|||" + j);
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container_view_tag, new RoutineRunFragment())
//                        .addToBackStack(null)
//                        .commit();
//            }
//        }
        return view;
    }

}