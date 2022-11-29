package com.example.githubproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class DeleteWorkOut extends AppCompatActivity {
    ListView dlt_list;

    public boolean onCreateOptionsMenu(Menu menu)    {
        getMenuInflater().inflate(R.menu.delete_workout, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_work_out);

        dlt_list = findViewById(R.id.delete_list);
        displayList();
    }
    void displayList() {
        DBHelper helper = new DBHelper(this, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT Exercise_Name FROM 운동목록 WHERE ID = 1000", null);
        ListViewItemAdapter item = new ListViewItemAdapter();

        while(cursor.moveToNext()) {
            item.addItem(cursor.getString(0));
        }
        dlt_list.setAdapter(item);
        db.close();
    }
}
