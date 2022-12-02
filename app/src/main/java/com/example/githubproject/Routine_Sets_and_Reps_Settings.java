package com.example.githubproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

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

public class Routine_Sets_and_Reps_Settings extends AppCompatActivity{
    ListViewAdapter item = new ListViewAdapter();
    int[] timer_check_int = new int[25];
    int i = 0;

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
                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_LONG).show();
                if(timer_check_int[position] == 1) {
                    withtimer(position);
                }
                else {
                    withouttimer(position);
                }
            }
        });
    }

    void displayList() {
        Routine_DBHelper helper = new Routine_DBHelper(this, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        Intent intent = getIntent();
        routine_name = intent.getStringExtra("ROUTINE_NAME");

        Cursor cursor = db.rawQuery("SELECT Exercise_Name, TTS FROM Routine WHERE Routine_Name = '" + routine_name + "'", null);

        while(cursor.moveToNext()) {
            if (cursor.getInt(1) >= 15) {
                timer_check_int[i] = 1;
            }
            else {
                timer_check_int[i] = 0;
            }
            item.plusItem(cursor.getString(0));
            i++;
        }
        settings_list.setAdapter(item);
        i = 0;
        db.close();
    }
    void withouttimer(int pos) {
        dialog = new Dialog(Routine_Sets_and_Reps_Settings.this);
        dialog.setContentView(R.layout.custom_dialog);
        workout_name = item.getName(pos);

        Spinner rep_spin = dialog.findViewById(R.id.reps_spin);
        Spinner set_spin = dialog.findViewById(R.id.sets_spin);
        Button apply_btn = dialog.findViewById(R.id.apply_btn);

        ArrayAdapter rep_spin_adapter = ArrayAdapter.createFromResource(Routine_Sets_and_Reps_Settings.this, R.array.reps_num, android.R.layout.simple_spinner_item);
        rep_spin.setAdapter(rep_spin_adapter);

        ArrayAdapter set_spin_adapter = ArrayAdapter.createFromResource(Routine_Sets_and_Reps_Settings.this, R.array.reps_num, android.R.layout.simple_spinner_item);
        set_spin.setAdapter(set_spin_adapter);

        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Routine_DBHelper routine_dbHelper = new Routine_DBHelper(Routine_Sets_and_Reps_Settings.this, 1);
                int set = 0;
                int rep = 0;
                set = Integer.parseInt(set_spin.getSelectedItem().toString());
                rep = Integer.parseInt(rep_spin.getSelectedItem().toString());

                routine_dbHelper.update_rep(rep, workout_name);
                routine_dbHelper.update_set(set, workout_name);
                Toast.makeText(getApplicationContext(), workout_name + "설정 완료", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    void withtimer(int pos) {
        dialog = new Dialog(Routine_Sets_and_Reps_Settings.this);
        dialog.setContentView(R.layout.custom_dialog_with_timer);
        workout_name = item.getName(pos);

        Spinner set_spin = dialog.findViewById(R.id.sets_spin);
        Button apply_btn = dialog.findViewById(R.id.apply_btn);

        ArrayAdapter set_spin_adapter = ArrayAdapter.createFromResource(Routine_Sets_and_Reps_Settings.this, R.array.reps_num, android.R.layout.simple_spinner_item);
        set_spin.setAdapter(set_spin_adapter);

        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Routine_DBHelper routine_dbHelper = new Routine_DBHelper(Routine_Sets_and_Reps_Settings.this, 1);
                int set = 0;
                set = Integer.parseInt(set_spin.getSelectedItem().toString());

                routine_dbHelper.update_set(set, workout_name);
                Toast.makeText(getApplicationContext(), workout_name + "설정 완료", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}