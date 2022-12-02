package com.example.githubproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
    RoutineRunFragment routineRunFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_start);
        routineRunFragment = new RoutineRunFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, routineRunFragment).commit();

        Routine_DBHelper routine_dbHelper = new Routine_DBHelper(RoutineStartActivity.this, 1);
        SQLiteDatabase db = routine_dbHelper.getReadableDatabase();

//        Cursor cursor = db.rawQuery("SELECT Exercise_Name FROM 운동목록 WHERE Exercise_Type = '" + workoutname + "'", null);
//        while()
    }
}
