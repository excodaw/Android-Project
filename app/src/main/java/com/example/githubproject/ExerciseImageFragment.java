package com.example.githubproject;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.githubproject.R;

public class ExerciseImageFragment extends Fragment {

    ImageView exc_img;
    TextView ima_tv;
    Button comp_btn;
    String[] exercise;
    String[] exc_svg;
    //String bench_press, Incline_bench_press, dumbbell_press, shoulder_press, side_lateral_raise
    //        , dead_lift, pull_up, squat, lunge, barbell_curl, dumbbell_curl, Lying_Triceps
    //        , cable_push_down, hanging_leg_raises, bicycle_crunch;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exercise_image, container, false);

        exc_img = v.findViewById(R.id.exc_img);
        ima_tv = v.findViewById(R.id.ima_tv);
        comp_btn = v.findViewById(R.id.comp_btn);
        exercise = new String[] {"벤치 프레스", "인클라인 벤치프레스", "뎀벨 프레스", "숄더 프레스"
                , "사이드 레터널 레이즈", "데드 리프트", "풀업", "스쿼트", "런지", "바벨 컬"
                , "덤벨 컬", "라잉 트라이셉스", "케이블 푸시 다운", "행잉 레그 레이즈", "바이시클 크런치"};
        //exc_svg = new String[] {}

        String read_exc_name = ima_tv.getText().toString();

        /*
        for (int i = 0; i <exercise.length; i++) {
            final int index;
            index = i;
            switch (read_exc_name) {
                case exercise[index]:
                    exc_img.setImageResource(R.drawable.exc_svg[index]);

            }
        }
         */
        return v;
    }
}
