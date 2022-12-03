package com.example.githubproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RoutineLoopFragment extends Fragment {
    RoutineRunFragment routineRunFragment;
    RoutineLoopFragment routineLoopFragment;
    RestTimers restTimers;
    String routine_name;
    Button button2;


    public static RoutineLoopFragment newInstance() {
        return new RoutineLoopFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
        restTimers = new RestTimers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine_loop, container, false);
        button2 = view.findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_view_tag, new RoutineRunFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        //((RoutineStartActivity)getActivity()).replacementFragment(RoutineRunFragment.newInstance());


//        Routine_DBHelper routine_dbHelper = new Routine_DBHelper(getContext(), 1);
//        SQLiteDatabase db = routine_dbHelper.getReadableDatabase();

//        Cursor cursor = db.rawQuery("SELECT Exercise_Name, Time, TTS, Reps, Sets FROM Routine WHERE Routine_Name = '" + routine_name + "'", null);
//        while(cursor.moveToNext()) {
//            for(int i = 0; i < cursor.getInt(4); i++) {
//                bundle.putString("WORKOUT_NAME", cursor.getString(0));
//                rou.setArguments(bundle);
//                if (cursor.getInt(2) == 1) {
//                }
//                if (cursor.getInt(2) >= 15) {
//                }
//            }
//        }
        return view;
    }
}