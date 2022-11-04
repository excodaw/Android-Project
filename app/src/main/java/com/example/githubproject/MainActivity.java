package com.example.githubproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentTransaction;

import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    WorkOutFragment WorkOutFragment;
    RoutineFragment RoutineFragment;
    RecordFragment RecordFragment;
    SettingFragment SettingFragment;
    BottomNavigationView bottomNavigationView;
    Button btn_chest;

    public boolean onCreateOptionsMenu(Menu menu)    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting_menu:
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

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
        SettingFragment = new SettingFragment();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        findViewById(R.id.button).setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,WorkOutFragment).commit();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.button).setVisibility(View.GONE);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Toast.makeText(getApplicationContext(),"Setting",Toast.LENGTH_SHORT).show();
                transaction.replace(R.id.container, SettingFragment);
                transaction.commit();
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.first_tab:
                        findViewById(R.id.button).setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,WorkOutFragment).commit();
                        return true;

                    case R.id.second_tab:
                        findViewById(R.id.button).setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,RoutineFragment).commit();
                        return true;

                    case R.id.third_tab:
                        findViewById(R.id.button).setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,RecordFragment).commit();
                        return true;

                }
                return false;
            }
        });
    }
}

