package com.example.githubproject;

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
    }
}
