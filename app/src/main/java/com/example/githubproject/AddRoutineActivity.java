package com.example.githubproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AddRoutineActivity extends AppCompatActivity {
    ListView workoutlistview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        workoutlistview = (ListView) findViewById(R.id.WorkOutList_in_routine);

        displayList();
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