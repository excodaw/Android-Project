package com.example.githubproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Timer;
import java.util.TimerTask;

public class RoutineRunFragment extends Fragment {

    TextView Before_exc, doing_exc, break_exc,
            end_exc, set_num, num_per_set;
    Button start_btn, next_set_btn,
            stop_btn, end_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine_run, container, false);

        TextView Before_exc = view.findViewById(R.id.Before_exc);
        TextView doing_exc = view.findViewById(R.id.doing_exc);
        TextView break_exc = view.findViewById(R.id.break_exc);
        TextView end_exc = view.findViewById(R.id.end_exc);
        TextView set_num = view.findViewById(R.id.set_num);
        TextView num_per_set = view.findViewById(R.id.num_per_set);
        Button start_btn = view.findViewById(R.id.start_btn);
        Button next_set_btn = view.findViewById(R.id.next_set_btn);
        Button stop_btn = view.findViewById(R.id.stop_btn);
        Button end_btn = view.findViewById(R.id.end_btn);

        // 세트 수 세트 당 횟수 변수
        final int[] setnum = {0};
        final int[] numperset = {0};

        Timer timer1 = new Timer();
        // 세트 당 횟수 올리기, 세트수 증가
        TimerTask timerTask1 = new TimerTask() {
            @Override
            //텍스트 숫자 업데이트마다 tts출력 하면 될지도
            //세트 당 횟수가 25(임시)면 세트 수 증가 아니면 계속 실행
            public void run() {
                if (setnum[0] != 25) {
                    numperset[0]++;
                    num_per_set.setText(numperset[0]);
                }
                else {
                    setnum[0]++;
                    //세트 수가 3(임시)면 운동 종료 아니면 계속 실행
                    if (numperset[0] != 3) {
                        numperset[0] = 0;
                        set_num.setText(setnum[0]);
                        num_per_set.setText(numperset[0]);
                        doing_exc.setVisibility(View.INVISIBLE);
                        break_exc.setVisibility(View.VISIBLE);
                        next_set_btn.setVisibility(View.VISIBLE);
                        next_set_btn.setClickable(true);
                        timer1.cancel();
                    }
                    else {
                        doing_exc.setVisibility(View.INVISIBLE);
                        end_exc.setVisibility(View.VISIBLE);
                        end_btn.setVisibility(View.VISIBLE);
                        timer1.cancel();
                    }
                }
            }
        };

        //시작 버튼
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Before_exc.setVisibility(View.INVISIBLE);
                doing_exc.setVisibility(View.VISIBLE);
                start_btn.setVisibility(View.INVISIBLE);
                stop_btn.setVisibility(View.VISIBLE);
                stop_btn.setClickable(true);
                next_set_btn.setClickable(true);
                timer1.schedule(timerTask1, 0, 2000);

            }
        });
        //다음 세트 버튼
        next_set_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                break_exc.setVisibility(View.INVISIBLE);
                doing_exc.setVisibility(View.VISIBLE);
                next_set_btn.setVisibility(View.INVISIBLE);
                stop_btn.setVisibility(View.VISIBLE);
                next_set_btn.setClickable(false);
                stop_btn.setClickable(true);
                timer1.schedule(timerTask1, 0, 2000);
            }
        });

        //중지 버튼
        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                break_exc.setVisibility(View.VISIBLE);
                doing_exc.setVisibility(View.INVISIBLE);
                start_btn.setVisibility(View.INVISIBLE);
                stop_btn.setVisibility(View.INVISIBLE);
                stop_btn.setClickable(false);
                start_btn.setClickable(true);
                timer1.schedule(timerTask1, 0, 2000);
            }
        });
        return view;
    }
}