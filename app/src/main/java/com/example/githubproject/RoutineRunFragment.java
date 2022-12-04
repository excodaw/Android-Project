package com.example.githubproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class RoutineRunFragment extends Fragment {
    String workout_name;
    String routine_name;
    int set=1, count;
    int count_exercise=1;
    RoutineFragment routinefragment = new RoutineFragment();
    public static RoutineRunFragment newInstance() {
        return new RoutineRunFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_routine_run, container, false);

        TextView set_num = view.findViewById(R.id.set_num);
        TextView num_per_set = view.findViewById(R.id.num_per_set);
        Button end_btn = view.findViewById(R.id.end_btn);
        Button add_btn = view.findViewById(R.id.plus_btn1);
        ImageView exc_img = view.findViewById(R.id.exc_img);
        TextView ima_tv = view.findViewById(R.id.ima_tv);


        String[] exercise = new String[]{"벤치프레스", "인클라인 벤치프레스", "덤벨 프레스", "숄더 프레스"
                , "사이드 레터널 레이즈", "데드 리프트", "풀업", "스쿼트", "런지", "바벨 컬"
                , "덤벨 컬", "라잉 트라이셉스", "케이블 푸시 다운", "행잉 레그 레이즈", "바이시클 크런치"};
        int[] exc_svg = new int[]{R.drawable.bench_press, R.drawable.incline_bench_press,
                R.drawable.dumbbell_press, R.drawable.shoulder_pres, R.drawable.side_lateral_raise
                , R.drawable.dead_lift, R.drawable.pull_up, R.drawable.squat, R.drawable.lunge
                , R.drawable.barbell_curl, R.drawable.dumbbell_curl, R.drawable.lying_triceps
                , R.drawable.cable_push_down, R.drawable.hanging_leg_raises, R.drawable.bicycle_crunch};

//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            workout_name = bundle.getString("WORKOUT_NAME");
//        }

//        for (int i = 0; i < exc_svg.length; i++) {
//                if (workout_name.equals(exercise[i])) {
//                    exc_img.setImageResource(exc_svg[i]);
//            }
//
//        }

//        ima_tv.setText(routine_name);
        if (getArguments()!=null){
            routine_name = getArguments().getString("name");
        }

        Routine_DBHelper helper = new Routine_DBHelper(getContext(), 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT Exercise_Name, Sets FROM Routine WHERE Routine_Name = '" + routine_name + "'", null);
        while(cursor.moveToNext()){
            count++;
        }
        Log.v("count",count+"");
        cursor.moveToFirst();
        ima_tv.setText(cursor.getString(0));
        num_per_set.setText(1+"세트");
        set_num.setText(cursor.getInt(1)+"세트 중");
        for (int i = 0; i < exc_svg.length; i++) {
            if (cursor.getString(0).equals(exercise[i])) {
                exc_img.setImageResource(exc_svg[i]);
            }
        }

        end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().remove(RoutineRunFragment.this).commit();
                fm.popBackStack();
                db.close();
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set++;
                num_per_set.setText(set+"세트");

                if (set == cursor.getInt(1)+1){
                    count_exercise++;
                    set=1;
                    cursor.moveToNext();
                    for (int i = 0; i < exc_svg.length; i++) {
                        if (cursor.getString(0).equals(exercise[i])) {
                             exc_img.setImageResource(exc_svg[i]);
                        }
                    }

                    set_num.setText(cursor.getInt(1)+"세트 중");
                    ima_tv.setText(cursor.getString(0));

                    num_per_set.setText(set+"세트");

                }
                if (count_exercise==count){
                    Log.v("excount",cursor.getInt(1)+"");
                    if (set==cursor.getInt(1)){
                        add_btn.setVisibility(View.INVISIBLE);
                        db.close();
                    }
                }
            }
        });

        return view;
    }
}