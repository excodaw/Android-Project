package com.example.githubproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
    private static String Type;
    int ID;

    public boolean onCreateOptionsMenu(Menu menu)    {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add_menu:
                if (db_name.getText().toString().length() != 0) {
                    type_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    dbHelper.insert(1000, type_spin.getSelectedItem().toString(),
                            db_name.getText().toString(), 0);
                    Toast.makeText(this, db_name.getText().toString() + " 추가", Toast.LENGTH_LONG).show();
                    dbHelper.close();
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

        ArrayAdapter typeAdapter = ArrayAdapter.createFromResource(this, R.array.type_names, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_spin.setAdapter(typeAdapter);
        dbHelper.close();
    }
}

