package com.example.githubproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class RoutineRunFragment extends Fragment {
    String workout_name;

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
        ImageView exc_img = view.findViewById(R.id.exc_img);
        TextView ima_tv = view.findViewById(R.id.ima_tv);

        String[] exercise = new String[]{"벤치 프레스", "인클라인 벤치프레스", "뎀벨 프레스", "숄더 프레스"
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

        end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_view_tag, new RestTimers())
//                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}