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

public class DeleteRoutine extends AppCompatActivity {
    ListView dlt_routine_list;
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
                                RoutineNameDBHelper nameDBHelper = new RoutineNameDBHelper(DeleteRoutine.this, 1);
                                Routine_DBHelper helper = new Routine_DBHelper(DeleteRoutine.this, 1);

                                helper.delete(item.getItem(j).toString());
                                nameDBHelper.delete(item.getItem(j).toString());
                            }
                        }
                        if (dlt_routine_list.getCount() == count){
                            Toast.makeText(DeleteRoutine.this, "삭제할 루틴을 선택하세요", Toast.LENGTH_LONG).show();
                            count=0;
                        }
                        else if(count != dlt_routine_list.getCount()){
                            Toast.makeText(DeleteRoutine.this, "루틴 삭제 완료", Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.activity_delete_routine);

        dlt_routine_list = findViewById(R.id.routine_dlt);
        displayList();
    }
    void displayList() {
        RoutineNameDBHelper helper = new RoutineNameDBHelper(this, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT Name FROM Routine_Name", null);

        while(cursor.moveToNext()) {
            item.addItem(cursor.getString(0));
        }
        dlt_routine_list.setAdapter(item);
        db.close();
    }
}