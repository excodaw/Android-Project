package com.example.githubproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddWorkOutActivity extends AppCompatActivity {

    DBHelper dbHelper;
    EditText db_name;
    Spinner type_spin;
    CheckBox timer_check;
    Spinner time_spin;

    public boolean onCreateOptionsMenu(Menu menu)    {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add_menu:
                if (db_name.getText().toString().length() != 0) {
                    if(timer_check.isChecked()) {
                        int timer = Integer.parseInt(time_spin.getSelectedItem().toString());
                        dbHelper.insert(1000, type_spin.getSelectedItem().toString(),
                                db_name.getText().toString(), timer);
                        Toast.makeText(this, db_name.getText().toString() + " 추가", Toast.LENGTH_LONG).show();
                        dbHelper.close();
                    }
                    else {
                        dbHelper.insert(1000, type_spin.getSelectedItem().toString(),
                                db_name.getText().toString(), 0);
                        Toast.makeText(this, db_name.getText().toString() + " 추가", Toast.LENGTH_LONG).show();
                        dbHelper.close();
                    }

                }
                else {
                    Toast.makeText(this, "운동 이름을 입력해 주세요", Toast.LENGTH_LONG).show();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work_out);

        db_name = (EditText) findViewById(R.id.db_name);
        dbHelper = new DBHelper(AddWorkOutActivity.this, 1);
        type_spin = findViewById(R.id.spinner_type);
        timer_check = findViewById(R.id.timer_check);
        time_spin = findViewById(R.id.tiemr_spin);

        ArrayAdapter timeAdapter = ArrayAdapter.createFromResource(this, R.array.time_num, android.R.layout.simple_spinner_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_spin.setAdapter(timeAdapter);

        timer_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    time_spin.setVisibility(View.VISIBLE);
                }
                else {
                    time_spin.setVisibility(View.INVISIBLE);
                }
            }
        });

        ArrayAdapter typeAdapter = ArrayAdapter.createFromResource(this, R.array.type_names, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_spin.setAdapter(typeAdapter);
        dbHelper.close();
    }

    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}

