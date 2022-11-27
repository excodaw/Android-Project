package com.example.githubproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RecordFragment extends Fragment{

    public static RecordFragment newInstance(String param1, String param2) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



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

}