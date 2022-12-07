package com.example.githubproject;

import static android.view.View.INVISIBLE;

import android.annotation.SuppressLint;
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
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;

import org.w3c.dom.Text;

import java.util.Locale;

public class RoutineDialog extends AlertDialog {
    ViewFlipper routine_flipper;
    ImageView exc_img;
    TextView ima_tv;
    TextView num_per_set;
    Button end_btn;
    TextView text_view_countdown;
    TextView Text_rest;
    TextView current_set;
    Button button_add;
    TextView workout_countdown;
    TextView timer_work;
    TextView next_workout;
    Button btn_end;
    TextView current_set_timer;
    TextView next_workout_timer;

    String routine_name;
    int loop_counter = 0;
    int set_check = 1;
    int workout_check = 0;
    int set_count = 0;
    long TIME = 0;
    long rest_time = 0;
    long work_time = 0;
    boolean timer_check;

    CountDownTimer mCountDownTimer;

    private void startTimer(long time, TextView countdownView) {
        TIME = time;
        mCountDownTimer = new CountDownTimer(TIME, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TIME = millisUntilFinished;
                updateCountDownText(TIME, countdownView);
            }

            @Override
            public void onFinish() {
                if(timer_check) {
                    btn_end.setVisibility(View.VISIBLE);
                }
                else {
                    TIME = time;
                    routine_flipper.showPrevious();
                }
            }
        }.start();
    }

    private void updateCountDownText(long time, TextView cnt_dwn) {
        int minutes = (int) (time / 1000) / 60;
        int seconds = (int) (time / 1000) % 60;
        String timerLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        cnt_dwn.setText(timerLeftFormatted);
    }

    protected RoutineDialog(Context context, String routine_name) {
        super(context, androidx.appcompat.R.style.AlertDialog_AppCompat);
        this.routine_name = routine_name;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine_dialog);

        Log.v("PLZ", "1231312");

        routine_flipper = findViewById(R.id.routine_flipper);
        exc_img = findViewById(R.id.exc_img);
        ima_tv = findViewById(R.id.ima_tv);
        num_per_set = findViewById(R.id.num_per_set);
        end_btn = findViewById(R.id.end_btn);
        text_view_countdown = findViewById(R.id.text_view_countdown);
        Text_rest = findViewById(R.id.Text_rest);
        current_set = findViewById(R.id.current_set);
        button_add = findViewById(R.id.button_add);
        workout_countdown = findViewById(R.id.workout_countdown);
        timer_work = findViewById(R.id.timer_work);
        btn_end = findViewById(R.id.btn_end);
        next_workout = findViewById(R.id.next_workout);
        current_set_timer = findViewById(R.id.current_set_timer);
        next_workout_timer = findViewById(R.id.next_workout_timer);

        Routine_DBHelper routine_dbHelper = new Routine_DBHelper(getContext(), 1);
        String[] workout_names = new String[routine_dbHelper.count(routine_name)];
        int[] set_counter = new int[routine_dbHelper.count(routine_name)];
        int[] rep_counter = new int[routine_dbHelper.count(routine_name)];
        int[] tts_counter = new int[routine_dbHelper.count(routine_name)];

        ResttimeDBHelper resttimeDBHelper = new ResttimeDBHelper(getContext(), 1);
        rest_time = get_time(resttimeDBHelper.getTime());

        SQLiteDatabase db = routine_dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Exercise_Name, Time, TTS, Reps, Sets FROM Routine WHERE Routine_Name = '" + routine_name + "'", null);
        while (cursor.moveToNext()) {
            workout_names[loop_counter] = cursor.getString(0);
            set_counter[loop_counter] = cursor.getInt(4);
            rep_counter[loop_counter] = cursor.getInt(3);
            tts_counter[loop_counter] = cursor.getInt(2);
            loop_counter++;
        }
        db.close();

        if(tts_counter[0] >= 15) {
            timer_check = true;
            routine_flipper.showNext();
            routine_flipper.showNext();
            work_time = get_time(tts_counter[workout_check]);
            startTimer(work_time, workout_countdown);
            timer_work.setText(workout_names[workout_check]);
            current_set_timer.setText(set_check + "/" + set_counter[workout_check]);
            if(workout_check + 1 != loop_counter) {
                next_workout_timer.setText(workout_names[workout_check + 1]);
            }
            else{
                next_workout_timer.setText("마지막 운동");
            }
        }

        set_img(workout_names[workout_check]);
        ima_tv.setText(workout_names[workout_check]);
        num_per_set.setText(rep_counter[workout_check]+"회");
        current_set.setText(set_check+"/"+set_counter[workout_check]);
        if(workout_check + 1 != loop_counter) {
            next_workout.setText(workout_names[workout_check + 1]);
        }
        end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (workout_check + 1 == loop_counter && set_check == set_counter[workout_check]) {
                    workout_check = 0;
                    set_check = 1;
                    dismiss();
                }
                else {
                    if(set_check++ == set_counter[workout_check] && workout_check + 1 != loop_counter) {
                        set_check = 1;
                        workout_check++;
                        if(workout_check + 1 != loop_counter) {
                            next_workout.setText(workout_names[workout_check + 1]);
                        }
                        else{
                            next_workout.setText("마지막 운동");
                        }
                        if(tts_counter[workout_check] >= 15) {
                            timer_check = true;
                            work_time = get_time(tts_counter[workout_check]);
                            timer_work.setText(workout_names[workout_check]);
                            current_set_timer.setText(set_check + "/" + set_counter[workout_check]);
                            startTimer(work_time, workout_countdown);
                            if(workout_check + 1 != loop_counter) {
                                next_workout_timer.setText(workout_names[workout_check + 1]);
                            }
                            else{
                                next_workout_timer.setText("마지막 운동");
                            }
                        }
                        else {
                            timer_check = false;
                        }
                        set_img(workout_names[workout_check]);
                        ima_tv.setText(workout_names[workout_check]);
                        num_per_set.setText(rep_counter[workout_check] + "회");
                    }
                    current_set.setText(set_check + "/" + set_counter[workout_check]);
                    routine_flipper.showNext();
                    startTimer(rest_time, text_view_countdown);
                }
                Log.v("TEST", " " + workout_check + " " + set_check + " " + loop_counter + " " + set_counter[workout_check]);
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownTimer.cancel();
                TIME = TIME + 10000;
                startTimer(TIME, text_view_countdown);
            }
        });

        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (workout_check + 1 == loop_counter && set_check == set_counter[workout_check]) {
                    workout_check = 0;
                    set_check = 1;
                    dismiss();
                }
                if(set_check++ == set_counter[workout_check] && workout_check + 1 != loop_counter) {
                    set_check = 1;
                    workout_check++;
                    if(tts_counter[workout_check] >= 15) {
                        timer_check = true;
                        work_time = get_time(tts_counter[workout_check]);
                        timer_work.setText(workout_names[workout_check]);
                        routine_flipper.showPrevious();
                        startTimer(1000, text_view_countdown);
                    }
                    else {
                        timer_check = false;
                        set_img(workout_names[workout_check]);
                        ima_tv.setText(workout_names[workout_check]);
                        num_per_set.setText(rep_counter[workout_check] + "회");
                        current_set.setText(set_check + "/" + set_counter[workout_check]);
                    }
                }
                current_set_timer.setText(set_check + "/" + set_counter[workout_check]);
                routine_flipper.showPrevious();
                startTimer(10000, text_view_countdown);
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

    public long get_time(int integer_time) {
        long time = 0;
        switch(integer_time) {
            case 15:
                time = 2000;
                break;
            case 30:
                time = 30000;
                break;
            case 45:
                time = 45000;
                break;
            case 60:
                time = 60000;
                break;
            default:
                time = 90000;
                break;
        }
        return time;
    }
}