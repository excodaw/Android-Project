package com.example.githubproject;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Locale;

public class RestTimers_two extends Fragment {
    private static final long START_TIME_IN_MILLIS = 10000;

    private TextView mTextViewCountDown;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimerLeftInMillis = START_TIME_IN_MILLIS;

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimerLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                //fragmentManager.beginTransaction().remove(RestTimers.this).commit();
                //fragmentManager.popBackStack(); 스택을 이용한 이전 프래그먼트로 바꾸기


            }
        }.start();

        mTimerRunning = true;
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    private void resetTimer() {
        mTimerLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
    }


    private void updateCountDownText() {
        int minutes = (int) (mTimerLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimerLeftInMillis / 1000) % 60;

        String timerLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timerLeftFormatted);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.rest_timer_two, container, false);

        mTextViewCountDown = view.findViewById(R.id.text_view_countdown);


        startTimer();


        updateCountDownText();
        return view;
    }

}