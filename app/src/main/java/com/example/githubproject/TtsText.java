package com.example.githubproject;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class TtsText<Exercise_Name> extends AppCompatActivity {

    TextToSpeech tts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

            public void onInit(int i) {
                if (i != TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.KOREA);
                }
            }
        });
    }

    public void bench_press () {
        tts.speak("안녕하세요", TextToSpeech.QUEUE_FLUSH, null,null);
    }
    public void shoulder_press () {

        tts.speak("안녕하세요", TextToSpeech.QUEUE_FLUSH, null,null);
    }
    public void dead_lift () {

        tts.speak("안녕하세요", TextToSpeech.QUEUE_FLUSH, null,null);
    }
    public void squat () {
        tts.speak("안녕하세요", TextToSpeech.QUEUE_FLUSH, null,null);
    }

}


