package com.example.githubproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AddRoutineActivity extends AppCompatActivity {
    DBHelper dbHelper;
    ListView workoutlistview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        ArrayAdapter lists = new ArrayAdapter (this, R.layout.item_listview, dbHelper.SelectAllWorkouts());

        workoutlistview = (ListView) findViewById(R.id.WorkOutList_in_routine);
        workoutlistview.setAdapter(lists);
    }

}