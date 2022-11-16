package com.example.githubproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class AddRoutineActivity extends AppCompatActivity {
    ListView workoutlistview;
    Button btn_chest;
    Button btn_shoulder;
    Button btn_back;
    Button btn_lower_body;
    Button btn_arm;
    Button btn_abs;
    Button btn_rehabilitation;
    String type = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        btn_chest = findViewById(R.id.btn_chest);
        btn_shoulder = findViewById(R.id.btn_shoulder);
        btn_back = findViewById(R.id.btn_back);
        btn_lower_body = findViewById(R.id.btn_lower_body);
        btn_arm = findViewById(R.id.btn_arm);
        btn_abs = findViewById(R.id.btn_abs);
        btn_rehabilitation = findViewById(R.id.btn_rehabilitation);
        workoutlistview = (ListView) findViewById(R.id.WorkOutList_in_routine);

        displayList();
        btn_chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "가슴";
                displayList();
            }
        });

        btn_shoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "어깨";
//                displayList(type);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "등";
//                displayList(type);
            }
        });

        btn_lower_body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "하체";
//                displayList(type);
            }
        });

        btn_arm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "팔";
//                displayList(type);
            }
        });

        btn_abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "복근";
//                displayList(type);
            }
        });
        btn_rehabilitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "재활";
//                displayList(type);
            }
        });
    }

    void displayList() {
        DBHelper helper = new DBHelper(this, 1);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT Exercise_Name FROM 운동목록", null);
        ListViewItemAdapter item = new ListViewItemAdapter();

        while(cursor.moveToNext()) {
            item.addItem(cursor.getString(0));
        }
        workoutlistview.setAdapter(item);
    }
}