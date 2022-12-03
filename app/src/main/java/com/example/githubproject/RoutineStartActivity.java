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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Timer;
import java.util.TimerTask;

public class RoutineStartActivity extends AppCompatActivity{
    Bundle bundle = new Bundle();
    RoutineRunFragment routineRunFragment;
    RestTimers restTimers;
    RestTimers_two restTimers_two;
    String routine_name;
    RoutineLoopFragment routineLoopFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_start);
        routineRunFragment = new RoutineRunFragment();
        restTimers = new RestTimers();
        restTimers_two = new RestTimers_two();

        Intent intent = getIntent();
        routine_name = intent.getStringExtra("ROUTINE_NAME");

        routineLoopFragment = new RoutineLoopFragment();
        bundle.putString("ROUTINE_NAME", routine_name);
        routineLoopFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, routineLoopFragment).commit();
    }
    public void replacementFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view_tag, fragment).commit();
    }
}
