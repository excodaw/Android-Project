package com.example.githubproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    WorkOutFragment WorkOutFragment;
    RoutineFragment RoutineFragment;
    RecordFragment RecordFragment;
    BottomNavigationView bottomNavigationView;

    DBHelper dbHelper;
    Button db_save,db_select;
    EditText db_id, db_name, db_type, db_tts;
    TextView db_view;

    public boolean onCreateOptionsMenu(Menu menu)    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting_menu:
                findViewById(R.id.fab).setVisibility(View.GONE);
                findViewById(R.id.setting_menu).setVisibility(View.GONE);
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WorkOutFragment = new WorkOutFragment();
        RoutineFragment = new RoutineFragment();
        RecordFragment = new RecordFragment();

        findViewById(R.id.fab).setVisibility(View.VISIBLE);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getApplicationContext(), view);
                MenuInflater inflater = popup.getMenuInflater();

                inflater.inflate(R.menu.floating_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch(menuItem.getItemId()) {
                            case R.id.add_routine:
                                startActivity(new Intent(getApplicationContext(), AddWorkOutActivity.class));
                                return true;
                            case R.id.add_workout:
                                Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.container,WorkOutFragment).commit();

        bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.first_tab:
                        findViewById(R.id.setting_menu).setVisibility(View.VISIBLE);
                        findViewById(R.id.fab).setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,WorkOutFragment).commit();
                        return true;

                    case R.id.second_tab:
                        findViewById(R.id.setting_menu).setVisibility(View.VISIBLE);
                        findViewById(R.id.fab).setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,RoutineFragment).commit();
                        return true;

                    case R.id.third_tab:
                        findViewById(R.id.setting_menu).setVisibility(View.VISIBLE);
                        findViewById(R.id.fab).setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,RecordFragment).commit();
                        return true;

                }
                return false;
            }
        });

        db_save= (Button) findViewById(R.id.db_save);
        db_select= (Button) findViewById(R.id.db_select);
        db_id= (EditText) findViewById(R.id.db_id);
        db_name= (EditText) findViewById(R.id.db_name);
        db_type= (EditText) findViewById(R.id.db_type);
        db_tts=(EditText) findViewById(R.id.db_tts);
        db_view=(TextView) findViewById(R.id.db_view);

        dbHelper = new DBHelper(MainActivity.this, 1);

        db_save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dbHelper.insert(Integer.parseInt(db_id.getText().toString()), db_type.getText().toString(),
                        db_name.getText().toString(), Integer.parseInt(db_tts.getText().toString()));
            }
        });
        db_select.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v2){
                db_view.setText(dbHelper.getResult());
            }
        });


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
                finish();
            }
        });

        alert.show();
    }

}



