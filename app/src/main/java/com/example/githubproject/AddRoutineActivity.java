package com.example.githubproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class AddRoutineActivity extends AppCompatActivity {
    DBHelper dbHelper;
    ListView workoutlistview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        workoutlistview = (ListView) findViewById(R.id.WorkOutList_in_routine);

        displayList();
    }

    void displayList() {

    }
}