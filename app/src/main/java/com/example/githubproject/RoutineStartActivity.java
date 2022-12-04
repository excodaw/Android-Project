package com.example.githubproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class RoutineStartActivity extends AppCompatActivity{
    Bundle bundle = new Bundle();
    RoutineRunFragment routineRunFragment;
    RestTimers restTimers;
    WorkoutWithTime restTimers_two;
    String routine_name;
    RoutineLoopFragment routineLoopFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_start);
        routineRunFragment = new RoutineRunFragment();
        restTimers = new RestTimers();
        restTimers_two = new WorkoutWithTime();

        Intent intent = getIntent();
        routine_name = intent.getStringExtra("ROUTINE_NAME");

        routineLoopFragment = new RoutineLoopFragment();
        bundle.putString("ROUTINE_NAME", routine_name);
        routineLoopFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, routineRunFragment).commit();
    }
    public void replacementFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view_tag, fragment).commit();
    }
}
