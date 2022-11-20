package com.example.githubproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.githubproject.ImageViewScrolling.IEventEnd;
import com.example.githubproject.ImageViewScrolling.ImageViewScrolling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecordFragment extends Fragment implements IEventEnd {

    public static RecordFragment newInstance(String param1, String param2) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    ImageView btn_up,btn_down;
    ImageViewScrolling image,image2,image3;
    TextView txt_score;

    int count_done;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);

        ListView listView = view.findViewById(R.id.record_listView);

        List<String> list = new ArrayList<>();
        list.add("몸무게");
        list.add("체지방");
        list.add("골격근");

        btn_down = (ImageView)view.findViewById(R.id.btn_down);
        btn_up = (ImageView)view.findViewById(R.id.btn_up);

        image = (ImageViewScrolling) view.findViewById(R.id.image);
        image2 = (ImageViewScrolling) view.findViewById(R.id.image2);
        image3 = (ImageViewScrolling) view.findViewById(R.id.image3);


        image.setEventEnd(RecordFragment.this);
        image2.setEventEnd(RecordFragment.this);
        image3.setEventEnd(RecordFragment.this);


        btn_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(Common.SCORE >=50)
                {
                    btn_up.setVisibility(View.VISIBLE);
                    btn_down.setVisibility(View.VISIBLE);

                    image.setValueRandom(new Random().nextInt(6),
                            new Random().nextInt((15-5)+1)+5);
                    image2.setValueRandom(new Random().nextInt(6),
                            new Random().nextInt((15-5)+1)+5);
                    image3.setValueRandom(new Random().nextInt(6),
                            new Random().nextInt((15-5)+1)+5);

                    Common.SCORE -=50;
                }
                else{

                }
            }

        });




        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.record_listfont,list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    //click 몸무게

                    Intent intent = new Intent(getActivity(), record_listview_weight.class);
                    startActivity(intent);

                }else if(position == 1){
                    //click 체지방

                    Intent intent = new Intent(getActivity(), record_listview_BodyFat.class);
                    startActivity(intent);

                }else if(position == 2){
                    //click 골격근

                    Intent intent = new Intent(getActivity(), record_listview_muscle.class);
                    startActivity(intent);

                }else{

                }
            }
        });
        return view;

    }

    @Override
    public void eventEnd(int result, int count) {
        if(count_done < 2)
            count_done++;
        else
        {
            btn_down.setVisibility(View.GONE);
            btn_up.setVisibility(View.VISIBLE);

            count_done = 0;
        }
    }
}