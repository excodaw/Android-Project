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
        tts.speak("바벨을 어깨 너비보다 넓게 잡고, 가슴을 피기 위해 등 전체에 아치를 살짝 만들어주고, 발 뒤꿈치가 땅에 닿게 해주세요. 바벨을 뽑아 어깨 위치로 이동 시킨 후 프레스!", TextToSpeech.QUEUE_FLUSH, null,null);
    }
    public void shoulder_press () {
        tts.speak("바벨을 손꿈치에 가깝게 두고 전완을 지면과 수직으로 위치시키세요. 손목을 중립으로 유지하고 정수리 쪽으로 바벨을 프레스!", TextToSpeech.QUEUE_FLUSH, null,null);
    }
    public void dead_lift () {
        tts.speak("발 전체에 무게 중심이 실리게 한 뒤, 정강이가 바벨에서 2 ~ 3 센치에 두고 고관절을 접으면서 바벨을 집으세요. 팔을 바깥으로 돌린다는 생각으로 리프트!", TextToSpeech.QUEUE_FLUSH, null,null);
    }
    public void squat () {
        tts.speak("발 전체에 무게 중심이 실리게 한 뒤, 바벨을 승모근에 올리고 고관절의 움직임에 집중하며 스쾃!", TextToSpeech.QUEUE_FLUSH, null,null);
    }

}


