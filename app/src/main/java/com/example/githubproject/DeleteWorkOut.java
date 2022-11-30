package com.example.githubproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class DeleteWorkOut extends AppCompatActivity {
    ListView dlt_list;
    ListViewItemAdapter item = new ListViewItemAdapter();

    public boolean onCreateOptionsMenu(Menu menu)    {
        getMenuInflater().inflate(R.menu.delete_workout, menu);
        return true;
    }

    public int count=0;
    public boolean onOptionsItemSelected(MenuItem items) {
        switch(items.getItemId()) {
            case R.id.add_menu:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("정말로 삭제하시겠습니까?");

                alert.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alert.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j = 0; j < item.getCount(); j++){
                            if (item.isChecked(j) == false) {
                                count++;
                            }
                            else if (item.isChecked(j) == true) {
                                Log.v("list count", "list" + item.getItem(j));
                                DBHelper helper = new DBHelper(DeleteWorkOut.this, 1);
                                helper.delete(item.getItem(j).toString());
                            }
                        }
                        if (dlt_list.getCount() == count){
                            Toast.makeText(DeleteWorkOut.this, "삭제할 운동을 골라주세요", Toast.LENGTH_LONG).show();
                            count=0;
                        }
                        else if(count != dlt_list.getCount()){
                            Toast.makeText(DeleteWorkOut.this, "운동 삭제 완료", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }
                });

                alert.show();

            default:
                return super.onOptionsItemSelected(items);
        }
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

        while(cursor.moveToNext()) {
            item.addItem(cursor.getString(0));
        }
        dlt_list.setAdapter(item);
        db.close();
    }
}
