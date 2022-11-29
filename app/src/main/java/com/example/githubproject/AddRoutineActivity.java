package com.example.githubproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddRoutineActivity extends AppCompatActivity {
    ListView workoutlistview;
    ListViewItemAdapter item = new ListViewItemAdapter();


    public boolean onCreateOptionsMenu(Menu menu)    {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }
public int count=0;
    public boolean onOptionsItemSelected(MenuItem items) {
        switch(items.getItemId()) {
            case R.id.add_menu:
                for (int i=0; i<item.getCount(); i++){
                    if (item.isChecked(i)== true) {
                        count++;
                        Toast.makeText(this, ""+count, Toast.LENGTH_LONG).show();

                    }
                    else {
//                        Toast.makeText(this, "운동을 골라주세요", Toast.LENGTH_LONG).show();
                    }
                }

            default:
                return super.onOptionsItemSelected(items);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);


        workoutlistview = (ListView) findViewById(R.id.WorkOutList_in_routine);

        displayList();
    }

    void displayList() {
        DBHelper helper = new DBHelper(this, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT Exercise_Name FROM 운동목록", null);

        while(cursor.moveToNext()) {
            item.addItem(cursor.getString(0));
        }

        workoutlistview.setAdapter(item);
        db.close();
    }
}