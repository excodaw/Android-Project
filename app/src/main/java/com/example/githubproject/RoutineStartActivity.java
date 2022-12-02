package com.example.githubproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class RoutineStartActivity extends AppCompatActivity{
    Bundle bundle = new Bundle();
    RoutineRunFragment routineRunFragment;
    RestTimers restTimers;
    String routine_name;
    int set_count = 0;
    int rep_count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_start);
        routineRunFragment = new RoutineRunFragment();
        restTimers = new RestTimers();

        Intent intent = getIntent();
        routine_name = intent.getStringExtra("ROUTINE_NAME");

        Routine_DBHelper routine_dbHelper = new Routine_DBHelper(RoutineStartActivity.this, 1);
        SQLiteDatabase db = routine_dbHelper.getReadableDatabase();



        Cursor cursor = db.rawQuery("SELECT Exercise_Name, Time, TTS, Reps, Sets FROM Routine WHERE Routine_Name = '" + routine_name + "'", null);
        while(cursor.moveToNext()) {
            for(int i = 1; i <= cursor.getInt(4); i++) {
                bundle.putString("WORKOUT_NAME", cursor.getString(0));
                routineRunFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, routineRunFragment).commit();
            }
        }
    }
}
