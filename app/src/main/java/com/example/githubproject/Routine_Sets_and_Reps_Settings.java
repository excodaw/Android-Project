package com.example.githubproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Routine_Sets_and_Reps_Settings extends AppCompatActivity {
    ListViewAdapter item = new ListViewAdapter();

    Dialog dialog;
    String routine_name, workout_name;
    ListView settings_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_sets_and_reps_settings);
        settings_list = findViewById(R.id.workout_listview);
        displayList();
        settings_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog = new Dialog(Routine_Sets_and_Reps_Settings.this);
                dialog.setContentView(R.layout.custom_dialog);
                workout_name = item.getName(position);
                Log.v("Tag", workout_name);
                dia();
            }
        });
    }

    void displayList() {
        Routine_DBHelper helper = new Routine_DBHelper(this, 1);
        SQLiteDatabase db = helper.getReadableDatabase();

        routine_name = "press";

        Cursor cursor = db.rawQuery("SELECT Exercise_Name FROM Routine WHERE Routine_Name = '" + routine_name + "'", null);

        while(cursor.moveToNext()) {
            item.plusItem(cursor.getString(0));
        }
        settings_list.setAdapter(item);
        db.close();
    }

    void dia() {
        dialog.show();
        Spinner rep_spin = dialog.findViewById(R.id.reps_spin);
        Spinner set_spin = dialog.findViewById(R.id.sets_spin);
        Button apply_btn = dialog.findViewById(R.id.apply_btn);
//
//        ArrayAdapter repAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.reps_num, android.R.layout.simple_spinner_item);
//        rep_spin.setAdapter(repAdapter);
//
//        ArrayAdapter setAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.sets_num, android.R.layout.simple_spinner_item);
//        set_spin.setAdapter(setAdapter);

        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Routine_DBHelper routine_dbHelper = new Routine_DBHelper(Routine_Sets_and_Reps_Settings.this, 1);
                routine_dbHelper.update_rep(2, workout_name);
                routine_dbHelper.update_set(2, workout_name);
                Toast.makeText(getApplicationContext(), workout_name + "설정 완료", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }
}