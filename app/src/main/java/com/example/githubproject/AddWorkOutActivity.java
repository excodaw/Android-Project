package com.example.githubproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddWorkOutActivity extends AppCompatActivity {

    DBHelper dbHelper;
    Button db_save,db_select, db_delete;
    EditText db_id, db_name, db_type, db_tts;
    TextView db_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work_out);

        db_save = (Button) findViewById(R.id.db_save);
        db_select = (Button) findViewById(R.id.db_select);
        db_id = (EditText) findViewById(R.id.db_id);
        db_name = (EditText) findViewById(R.id.db_name);
        db_type = (EditText) findViewById(R.id.db_type);
        db_tts = (EditText) findViewById(R.id.db_tts);
        db_view = (TextView) findViewById(R.id.db_view);
        db_delete= (Button) findViewById(R.id.db_delete);


        dbHelper = new DBHelper(AddWorkOutActivity.this, 1);

        db_save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dbHelper.insert(Integer.parseInt(db_id.getText().toString()), db_type.getText().toString(),
                        db_name.getText().toString(), Integer.parseInt(db_tts.getText().toString()));
            }
        });
        db_select.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v2){
                //dbHelper.dbCopy(AddWorkOutActivity.this);
                db_view.setText(dbHelper.getResult());
            }
        });

        db_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                dbHelper.Delete(Integer.parseInt(db_id.getText().toString()));
            }
        });
    }

}

