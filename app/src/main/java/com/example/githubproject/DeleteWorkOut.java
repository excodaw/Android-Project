package com.example.githubproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class DeleteWorkOut extends AppCompatActivity {

    ListView delete_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_work_out);

        delete_list.findViewById(R.id.delete_list);
        displayList();
    }
    void displayList() {
        DBHelper helper = new DBHelper(DeleteWorkOut.this, 1);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT Exercise_Name FROM 운동목록", null);
        ListViewItemAdapter delete_item = new ListViewItemAdapter();

        while(cursor.moveToNext()) {
            delete_item.addItem(cursor.getString(0));
        }
        delete_list.setAdapter(delete_item);
        db.close();
    }
}
