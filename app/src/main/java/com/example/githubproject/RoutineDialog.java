package com.example.githubproject;

import static android.view.View.INVISIBLE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.SoundPool;
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
    SoundPool soundPool;
    ViewFlipper routine_flipper;
    ImageView exc_img;
    TextView ima_tv;
    TextView num_per_set;
    Button end_btn;
    TextView text_view_countdown;
    TextView Text_rest;
    TextView current_set;
    TextView countdown;
    TextView timer_workout;
    TextView current_set_timer;
    Button button_add;
    Button timer_end_btn;

    String next_ex_name;
    String routine_name;
    int next_tts = 0;
    int array_counter = 0;
    int loop_counter = 0;
    int set_check = 1;
    int workout_check = 0;
    int set_count = 0;
    int timer_set_count = 0;
    long rest_time = 0;
    long workout_time = 0;
    long next_workout_time = 0;
    int tts_checker = 0;
    int DING_SOUND = 0;
    boolean timer_checker;
    CountDownTimer mCountDownTimer;
    boolean mTimerRunning;
    long mTimerLeftInMillis = 0;

    protected RoutineDialog(Context context, String routine_name) {
        super(context, androidx.appcompat.R.style.AlertDialog_AppCompat);
        this.routine_name = routine_name;
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
        current_set = findViewById(R.id.current_set);
        button_add = findViewById(R.id.button_add);
        countdown = findViewById(R.id.countdown);
        timer_workout = findViewById(R.id.timer_workout);
        current_set_timer = findViewById(R.id.current_set_timer);
        timer_end_btn = findViewById(R.id.timer_end_btn);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        DING_SOUND = soundPool.load(getContext(), R.raw.ding, 1);

        ResttimeDBHelper resttimeDBHelper = new ResttimeDBHelper(getContext(), 1);
        rest_time = get_time(resttimeDBHelper.getTime());

        Routine_DBHelper routine_dbHelper = new Routine_DBHelper(getContext(), 1);
        array_counter = routine_dbHelper.count(routine_name);
        String[] workout_names = new String[array_counter];
        int[] set_counter = new int[array_counter];
        int[] rep_counter = new int[array_counter];
        int[] tts_counter = new int[array_counter];

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

        if(tts_counter[workout_check] >= 15) {
            timer_checker = true;
        }
        else {
            timer_checker = false;
        }

        if(workout_check + 1 < workout_names.length) {
            next_ex_name = workout_names[workout_check + 1];
            tts_checker = tts_counter[workout_check + 1];
            next_workout_time = get_time(tts_counter[workout_check + 1]);
        }

        set_img(workout_names[workout_check]);
        ima_tv.setText(workout_names[workout_check]);
        num_per_set.setText(rep_counter[set_count] + "회");
        current_set.setText(set_check + "/" + set_counter[workout_check]);

        end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("TAT", " " + workout_check + " " + set_check + " " + loop_counter + " " + workout_names[workout_check]);
                if (set_count==10000){//마지막 운동 세트 끝나면 종료
                    set_count=0;
                    dismiss();
                }
                set_check++;
                num_per_set.setText(rep_counter[set_count]+"회");
                if (set_check == set_counter[workout_check]+1) {
                    set_check = 1;
                    workout_check++;
                    if(tts_counter[workout_check] >= 15) {
                        timer_checker = true;
                    }
                    else {
                        timer_checker = false;
                    }
                    set_count++;
                    set_img(workout_names[workout_check]);
                    timer_workout.setText(workout_names[workout_check]);
                    num_per_set.setText(rep_counter[set_count]+"회");
                }
                current_set.setText(set_check + "/" + set_counter[workout_check]);//세트/총세트 표시
                if (workout_check + 1 == loop_counter && set_check==set_counter[workout_check]) {// 마지막운동 일 때
                    set_check = 1;
                    workout_check = 0;
                    set_count=10000;
                }

                routine_flipper.showNext();
                startTimer(3000);
                Log.v("TEST", " " + workout_check + " " + set_check + " " + loop_counter + " " + set_counter[workout_check]);
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownTimer.cancel();
                mTimerLeftInMillis = mTimerLeftInMillis + 10000;
                startTimer(mTimerLeftInMillis);
            }
        });

        if(timer_checker) {
            workout_time = get_time(tts_counter[workout_check]);
            timer_workout.setText(workout_names[workout_check]);
            current_set_timer.setText(set_check + "/" + set_counter[workout_check]);

            routine_flipper.showNext();
            routine_flipper.showNext();
            startWorkoutTimer(2000);

            timer_end_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (set_count==10000){//마지막 운동 세트 끝나면 종료
                        set_count=0;
                        soundPool.pause(DING_SOUND);
                        dismiss();
                    }
                    set_check++;
                    num_per_set.setText(rep_counter[set_count]+"회");
                    if (set_check == set_counter[workout_check] + 1) {
                        set_check = 1;
                        workout_check++;
                        if(tts_counter[workout_check] >= 15) {
                            timer_checker = true;
                        }
                        else {
                            timer_checker = false;
                            set_img(workout_names[workout_check]);
                            timer_workout.setText(workout_names[workout_check]);
                            num_per_set.setText(rep_counter[set_count]+"회");
                        }
                        set_count++;
                    }
                    current_set.setText(set_check + "/" + set_counter[workout_check]);//세트/총세트 표시
                    if (workout_check + 1 == loop_counter && set_check==set_counter[workout_check]) {// 마지막운동 일 때
                        set_check = 1;
                        workout_check = 0;
                        set_count=10000;
                    }

                    routine_flipper.showNext();
                    startTimer(3000);
                    Log.v("TEST", " " + workout_check + " " + set_check + " " + loop_counter + " " + set_counter[workout_check]);
                }
            });
        }
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
                time = 15000;
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

    private void startTimer(long set_time) {
        current_set_timer.setText(set_check + "/" + timer_set_count);
        mTimerLeftInMillis = set_time;
        mCountDownTimer = new CountDownTimer(mTimerLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerLeftInMillis = millisUntilFinished;
                updateCountDownText(mTimerLeftInMillis);
            }
            @Override
            public void onFinish() {
                soundPool.play(DING_SOUND, 1f, 1f, 0, 0, 1f);
                mTimerRunning = false;
                mTimerLeftInMillis = set_time;
                if(timer_checker) {
                    routine_flipper.showNext();
                    startWorkoutTimer(workout_time);
                }
                else {
                    routine_flipper.showPrevious();
                }
            }
        }.start();

        mTimerRunning = true;
    }

    private void updateCountDownText(long time) {
        int minutes = (int) (time / 1000) / 60;
        int seconds = (int) (time / 1000) % 60;
        String timerLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        text_view_countdown.setText(timerLeftFormatted);
    }

    private void startWorkoutTimer(long set_time) {
        Log.v("TEST", " " + workout_check + " " + set_check + " " + loop_counter + " " + timer_set_count);
        mTimerLeftInMillis = set_time;
        mCountDownTimer = new CountDownTimer(mTimerLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerLeftInMillis = millisUntilFinished;
                updateWCountDownText(mTimerLeftInMillis);
            }
            @Override
            public void onFinish() {
                soundPool.play(DING_SOUND, 1f, 1f, 0, 0, 1f);
                mTimerRunning = false;
                mTimerLeftInMillis = set_time;
                Log.v("TEST", " " + workout_check + " " + set_check + " " + loop_counter + " " + timer_set_count);
            }
        }.start();
        mTimerRunning = true;
    }

    private void updateWCountDownText(long time) {
        int minutes = (int) (time / 1000) / 60;
        int seconds = (int) (time / 1000) % 60;
        String timerLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        countdown.setText(timerLeftFormatted);
    }
}