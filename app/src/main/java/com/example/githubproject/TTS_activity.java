package com.example.githubproject;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class TTS_activity extends AppCompatActivity {

    TextToSpeech tts;
    Button btn_tts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);

        btn_tts = findViewById(R.id.btn_tts);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.KOREA);
                }
            }
        });

        btn_tts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak("안녕하세요", TextToSpeech.QUEUE_FLUSH, null,null);
            }
        });

    }
}
