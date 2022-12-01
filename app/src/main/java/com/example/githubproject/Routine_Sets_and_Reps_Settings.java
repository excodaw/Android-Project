package com.example.githubproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class Routine_Sets_and_Reps_Settings extends AppCompatActivity {

    ListView settings_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_sets_and_reps_settings);

        settings_list = findViewById(R.id.workout_listview);
        displayList();
    }

    void displayList() {
        Routine_DBHelper helper = new Routine_DBHelper(this, 1);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT Exercise_Name FROM Routine WHERE Routine_Name = '123'", null);
        ListViewAdapter item = new ListViewAdapter();

        while(cursor.moveToNext()) {
            item.plusItem(cursor.getString(0));
        }
        settings_list.setAdapter(item);
        db.close();
    }
}