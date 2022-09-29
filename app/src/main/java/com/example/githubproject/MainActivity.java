package com.example.githubproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    WorkOutFragment workOutFragment;
    RoutineFragment routineFragment;
    RecordFragment recordFragment;
    SettingFragment settingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        workOutFragment = new WorkOutFragment();
        routineFragment = new RoutineFragment();
        recordFragment = new RecordFragment();
        settingFragment = new SettingFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, workOutFragment).commit();
    }
}