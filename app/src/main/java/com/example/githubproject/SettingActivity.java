package com.example.githubproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    Button alarm_button;
    Button delete_btn;
    Button routine_delete_btn;
    Spinner rest_time_spin;
    Button setting_btn;
    int rest_time_change = 0;


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
        ResttimeDBHelper resttimeDBHelper = new ResttimeDBHelper(SettingActivity.this, 1);
        SQLiteDatabase db = resttimeDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT Time FROM REST_TIME", null);

        while(cursor.moveToNext()) {
            switch (cursor.getInt(0)) {
                case 15:
                    rest_time_spin.setSelection(0);
                    break;
                case 30:
                    rest_time_spin.setSelection(1);
                    break;
                case 45:
                    rest_time_spin.setSelection(2);
                    break;
                case 60:
                    rest_time_spin.setSelection(3);
                    break;
                default:
                    rest_time_spin.setSelection(4);
                    break;
            }
        }
        db.close();
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
                Toast.makeText(getApplicationContext(), rest_time_spin.getSelectedItem().toString() + "로 설정됨", Toast.LENGTH_LONG).show();
                rest_time_change = Integer.parseInt(rest_time_spin.getSelectedItem().toString());
                resttimeDBHelper.Update(rest_time_change);
            }
        });
    }
}