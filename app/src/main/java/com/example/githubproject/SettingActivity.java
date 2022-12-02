package com.example.githubproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SettingActivity extends AppCompatActivity {

    Button alarm_button;
    Button delete_btn;
    Button routine_delete_btn;
    Spinner rest_time_spin;
    Button setting_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        alarm_button = findViewById(R.id.alarm_button);
        delete_btn = findViewById(R.id.workout_dlt);
        routine_delete_btn = findViewById(R.id.routine_dlt);
        rest_time_spin = findViewById(R.id.rest_time_spin);
        setting_btn = findViewById(R.id.setting_btn);

        ArrayAdapter typeAdapter = ArrayAdapter.createFromResource(this, R.array.time_num, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rest_time_spin.setAdapter(typeAdapter);

        alarm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Alarm_activity.class));
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DeleteWorkOut.class));
            }
        });

        routine_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DeleteRoutine.class));
            }
        });

        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}