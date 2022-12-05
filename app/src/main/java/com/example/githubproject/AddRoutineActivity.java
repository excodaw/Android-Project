package com.example.githubproject;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddRoutineActivity extends AppCompatActivity {
    ListView workoutlistview;
    ListViewItemAdapter item = new ListViewItemAdapter();
    String r_name;


    public boolean onCreateOptionsMenu(Menu menu)    {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }
    public int count=0;
    public boolean onOptionsItemSelected(MenuItem items) {
        switch(items.getItemId()) {
            case R.id.add_menu:
                final EditText et = new EditText(this);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("루틴 생성").setMessage("루틴 이름을 입력하세요").setView(et).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i=0; i<item.getCount(); i++){
                            if (item.isChecked(i)== false) {
                                count++;
                            } else if (item.isChecked(i)==true) {
                                Routine_DBHelper r_helper = new Routine_DBHelper(getApplicationContext(), 1);

                                r_helper.Update(et.getText().toString());

                            }
                        }
                        if (count == workoutlistview.getCount()){
                            Toast.makeText(AddRoutineActivity.this, "운동을 골라주세요", Toast.LENGTH_LONG).show();
                            count=0;
                        }
                        else if(count != workoutlistview.getCount()){
                            RoutineNameDBHelper nameDBHelper = new RoutineNameDBHelper(getApplicationContext(), 1);
                            nameDBHelper.insert(et.getText().toString());
                            Toast.makeText(AddRoutineActivity.this, "루틴 저장 완료", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }
                });
                builder.show();

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

    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("종료하시겠습니까?");

        alert.setPositiveButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alert.setNegativeButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Routine_DBHelper routine_dbHelper = new Routine_DBHelper(getApplicationContext(), 1);
                routine_dbHelper.delete("123214124124");
                finish();
            }
        });

        alert.show();
    }
}