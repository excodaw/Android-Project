package com.example.githubproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class WorkOutFragment extends Fragment {
    public static WorkOutFragment newInstance(String param1, String param2) {
        WorkOutFragment fragment = new WorkOutFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    TabLayout tabLayout;
    ListView WL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work_out, container, false);
        tabLayout = view.findViewById(R.id.tabs);
        WL = view.findViewById(R.id.WorkOutList);
        displayList("가슴");
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                if(position == 0) {
                    displayList("가슴");
                }
                else if(position == 1) {
                    displayList("등");
                }
                else if(position == 2) {
                    displayList("어깨");
                }
                else if(position == 3) {
                    displayList("하체");
                }
                else if(position == 4) {
                    displayList("복근");
                }
                else if(position == 5) {
                    displayList("팔");
                }
                else if(position == 6) {
                    displayList("재활");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                if(position == 0) {
                    displayList("가슴");
                }
                else if(position == 1) {
                    displayList("등");
                }
                else if(position == 2) {
                    displayList("어깨");
                }
                else if(position == 3) {
                    displayList("하체");
                }
                else if(position == 4) {
                    displayList("복근");
                }
                else if(position == 5) {
                    displayList("팔");
                }
                else if(position == 6) {
                    displayList("재활");
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
    void displayList(String workoutname) {
        DBHelper helper = new DBHelper(getContext(), 1);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT Exercise_Name FROM 운동목록 WHERE Exercise_Type = '" + workoutname + "'", null);
        ListViewAdapter item = new ListViewAdapter();

        while(cursor.moveToNext()) {
            item.plusItem(cursor.getString(0));
        }
        WL.setAdapter(item);
        db.close();
    }
}