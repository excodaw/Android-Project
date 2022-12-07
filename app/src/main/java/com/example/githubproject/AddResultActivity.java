package com.example.githubproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddResultActivity extends AppCompatActivity {

    MyDBHelper mydbHelper;
    Button record_save,record_select;
    EditText record_id;
    TextView record_view;
    Spinner type_spins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_result);

        record_save = (Button) findViewById(R.id.record_save);
        record_select = (Button) findViewById(R.id.record_select);
        record_id = (EditText) findViewById(R.id.record_id);
        record_view = (TextView) findViewById(R.id.record_view);

        mydbHelper = new MyDBHelper(this, 1);
        type_spins = findViewById(R.id.spinner_types);

        ArrayAdapter typeAdapter = ArrayAdapter.createFromResource(this, R.array.result_names, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_spins.setAdapter(typeAdapter);

        record_save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(record_id.length() == 0) {

                }
                else if(record_id.length() >= 4) {
                    Toast.makeText(getApplicationContext(), "설마... 아니죠?", Toast.LENGTH_LONG).show();
                }
                else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                    String currentDate = simpleDateFormat.format(new Date());

                    mydbHelper.insert(Integer.parseInt(record_id.getText().toString()), type_spins.getSelectedItem().toString(), currentDate);
                }
            }
        });

        record_select.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v2){
                record_view.setText(mydbHelper.getResult());
            }
        });

    }
}