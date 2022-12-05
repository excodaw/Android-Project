package com.example.githubproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class RoutineFragment extends Fragment {
    ListViewAdapter item = new ListViewAdapter();
    SendEvent sendEvent;
    String routine_name;
    RoutineRunFragment routinerunfragment;
    RoutineFragment routinefragment;
    RoutineDialog routineDialog;
    Bundle bundle = new Bundle();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            sendEvent = (SendEvent) context;
        } catch (ClassCastException e) {
            throw new ClassCastException((context.toString() + "must implement SendEvent !"));
        }
    }

    public static RoutineFragment newInstance(String param1, String param2) {
        RoutineFragment fragment = new RoutineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private View view;
    ListView routine_name_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_routine, container, false);
        routinerunfragment = new RoutineRunFragment();
        routinefragment = new RoutineFragment();
        routine_name_list = view.findViewById(R.id.routine_name_list);
        displayList();

        routine_name_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setMessage("루틴 " + item.getName(position) + "실행하시겠습니까?");
                alert.setPositiveButton("실행", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        routine_name = item.getName(position);
                        Bundle bundle = new Bundle();
                        bundle.putString("name",item.getName(position));
                        routinerunfragment.setArguments(bundle);
                        boolean check = false;
                        Routine_DBHelper helper = new Routine_DBHelper(getContext(), 1);
                        SQLiteDatabase db = helper.getReadableDatabase();
                        Cursor cursor = db.rawQuery("SELECT TTS, Reps, Sets FROM Routine WHERE Routine_Name = '" + routine_name + "'", null);
                        while (cursor.moveToNext()) {
                            if(cursor.getInt(1) == 0 || cursor.getInt(2) == 0) {
                                check = true;
                            }
                            if(cursor.getInt(0) >= 15) {
                                check = false;
                            }
                        }
                        db.close();
                        if (check) {
                            Toast.makeText(getContext(), "세트 수와 횟수가 설정되어있지 않습니다!", Toast.LENGTH_SHORT).show();
                            sendEvent.sendRoutineName(routine_name, true);
                        }
                        else {
                            sendEvent.sendRoutineName(routine_name, false);
                            routineDialog = new RoutineDialog(getContext(), routine_name);
                            routineDialog.show();
                                }
                            }
                });
                alert.show();
            }
        });
        routine_name_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setMessage("루틴 " + item.getName(position) + "수정하시겠습니까?");

                routine_name = item.getName(position);

                alert.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendEvent.sendRoutineName(routine_name, true);
                    }
                });
                alert.show();
                return false;
            }
        });
        return view;
    }

    void displayList() {
        RoutineNameDBHelper helper = new RoutineNameDBHelper(getContext(), 1);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT Name FROM Routine_Name", null);

        while (cursor.moveToNext()) {
            item.plusItem(cursor.getString(0));
        }
        routine_name_list.setAdapter(item);
        db.close();
    }
}


