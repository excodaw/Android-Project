package com.example.githubproject;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CountDown extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.countdowntimer);

        textView = findViewById(R.id.text_view);

        long duration = TimeUnit.MINUTES.toMillis(1);

        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                String sDuration = String.format(Locale.ENGLISH,"%02d : %02d",TimeUnit.MILLISECONDS.toMinutes(1),TimeUnit.MILLISECONDS.toSeconds(1) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(1)));
                textView.setText(sDuration);
            }

            @Override
            public void onFinish() {
                textView.setVisibility(View.GONE);

                Toast.makeText(getApplicationContext(),"휴식 종료",Toast.LENGTH_LONG).show();

            }
        }.start();

    }

}
