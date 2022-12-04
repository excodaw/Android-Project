package com.example.githubproject;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.StateSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;

import org.w3c.dom.Text;

import java.util.Locale;

public class RoutineDialog extends AlertDialog {
    String routine_name;
    ViewFlipper routine_flipper;
    ImageView exc_img;
    TextView ima_tv;
    TextView num_per_set;
    Button end_btn;
    TextView text_view_countdown;
    TextView Text_rest;
    Button button_add;
    int array_counter = 0;
    int loop_counter = 0;
    int set_check = 0;
    int workout_check = 0;

    protected RoutineDialog(Context context) {
        super(context, androidx.appcompat.R.style.AlertDialog_AppCompat);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine_dialog);

        routine_flipper = findViewById(R.id.routine_flipper);
        exc_img = findViewById(R.id.exc_img);
        ima_tv = findViewById(R.id.ima_tv);
        num_per_set = findViewById(R.id.num_per_set);
        end_btn = findViewById(R.id.end_btn);
        text_view_countdown = findViewById(R.id.text_view_countdown);
        Text_rest = findViewById(R.id.Text_rest);
        button_add = findViewById(R.id.button_add);
        routine_name = "333";
        Routine_DBHelper routine_dbHelper = new Routine_DBHelper(getContext(), 1);
        array_counter = routine_dbHelper.count(routine_name);
        String[] workout_names = new String[array_counter];
        int[] set_counter = new int[array_counter];
        int[] rep_counter = new int[array_counter];

        SQLiteDatabase db = routine_dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Exercise_Name, Time, TTS, Reps, Sets FROM Routine WHERE Routine_Name = '" + routine_name + "'", null);
        while (cursor.moveToNext()) {
            workout_names[loop_counter] = cursor.getString(0);
            set_counter[loop_counter] = cursor.getInt(4);
            rep_counter[loop_counter] = cursor.getInt(3);
            loop_counter++;
        }
        db.close();

        set_img(workout_names[workout_check]);
        ima_tv.setText(workout_names[workout_check]);

        end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("TAT", " " + workout_check + " " + set_check + " " + loop_counter + " " + workout_names[workout_check]);
                set_img(workout_names[workout_check]);
                ima_tv.setText(workout_names[workout_check]);
                set_check++;
                if (set_check == set_counter[workout_check]) {
                    set_check = 0;
                    workout_check++;
                }
                if (workout_check == loop_counter) {
                    set_check = 0;
                    workout_check = 0;
                    dismiss();
                }
                else {
                    routine_flipper.showNext();
                }
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_img(workout_names[workout_check]);
                ima_tv.setText(workout_names[workout_check]);
                routine_flipper.showPrevious();
            }
        });
    }

    public void set_img(String workout_name) {
        String[] exercise = new String[]{"벤치프레스", "인클라인 벤치프레스", "덤벨 프레스", "숄더 프레스"
                , "사이드 레터널 레이즈", "데드 리프트", "풀업", "스쿼트", "런지", "바벨 컬"
                , "덤벨 컬", "라잉 트라이셉스", "케이블 푸시 다운", "행잉 레그 레이즈", "바이시클 크런치"};

        int[] exc_svg = new int[]{R.drawable.bench_press, R.drawable.incline_bench_press,
                R.drawable.dumbbell_press, R.drawable.shoulder_pres, R.drawable.side_lateral_raise
                , R.drawable.dead_lift, R.drawable.pull_up, R.drawable.squat, R.drawable.lunge
                , R.drawable.barbell_curl, R.drawable.dumbbell_curl, R.drawable.lying_triceps
                , R.drawable.cable_push_down, R.drawable.hanging_leg_raises, R.drawable.bicycle_crunch};

        for (int i = 0; i < exercise.length; i++) {
            if (workout_name.equals(exercise[i])) {
                exc_img.setImageResource(exc_svg[i]);
            }
        }
    }
}
