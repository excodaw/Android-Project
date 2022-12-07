package com.example.githubproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecordFragment extends Fragment{
    LineChart lineChart;
    TabLayout graph_tabs;
    TextView graph;
    int type = 0;

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
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        graph_tabs = view.findViewById(R.id.graph_tab);
        lineChart = view.findViewById(R.id.graph_chart);
        graph = view.findViewById(R.id.graph);
        graphShow("몸무게");
        graph_tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                if(position == 0) {
                    type = 0;
                    graphShow("몸무게");
                }
                else if(position == 1) {
                    type = 1;
                    graphShow("3대 중량");
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return view;
    }

    public void graphShow(String chart_name) {
        int count = 0;
        MyDBHelper mydbHelper = new MyDBHelper(getContext(), 1);
        count = mydbHelper.getCount(chart_name);
        if (count < 2) {
            lineChart.setVisibility(View.INVISIBLE);
            graph.setVisibility(View.INVISIBLE);
        }
        else if (count >= 2){
            graph.setVisibility(View.VISIBLE);
            lineChart.setVisibility(View.VISIBLE);
            List<Entry> entries = new ArrayList<>();
            ArrayList<Result> resultList = mydbHelper.getFilteredResultList(chart_name);
            for(int i=0; i<resultList.size(); i++) {
                entries.add(new Entry(i, resultList.get(i).getNumber()));
            }

            LineDataSet lineDataSet = new LineDataSet(entries, chart_name);
            lineDataSet.setLineWidth(2);
            lineDataSet.setCircleRadius(6);
            lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC")); //FFA1B4DC
            lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
            lineDataSet.setDrawCircleHole(true);
            lineDataSet.setDrawCircles(true);
            lineDataSet.setDrawHorizontalHighlightIndicator(false);
            lineDataSet.setDrawHighlightIndicators(false);
            lineDataSet.setDrawValues(false);
            lineChart.setDragXEnabled(true);
            lineChart.setDragEnabled(true);
            lineChart.setDragYEnabled(true);
            lineChart.getLegend().setTextColor(Color.parseColor("#FFFFFF"));

            Legend legend = lineChart.getLegend();
            legend.setTextSize(21);



            LineData lineData = new LineData(lineDataSet);
            lineChart.setData(lineData);

            XAxis xAxis = lineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setEnabled(false);
/*
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            lineChart.getXAxis().setYOffset(15f);
            lineChart.getAxisLeft().setXOffset(15f);
            xAxis.setGranularityEnabled(true);
            xAxis.setGranularity(1f);
            xAxis.setTextColor(Color.WHITE);
            xAxis.enableGridDashedLine(8, 24, 0); //라인대쉬형태로 (라인길이, 공간길이, 단계)
            xAxis.setLabelCount(resultList.size() - 1, true);
            xAxis.setDrawGridLines(false); //격자무늬 삭제
*/
            /*xAxis.setValueFormatter(new IndexAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return resultList.get((int)value).getRecordDate();
                }
            });*/
            MyMarkerView marker = new MyMarkerView(getContext(), graph, R.layout.custom_marker_view);

            YAxis yLAxis = lineChart.getAxisLeft();
            yLAxis.setTextColor(Color.WHITE);

            YAxis yRAxis = lineChart.getAxisRight();
            yRAxis.setDrawLabels(false);
            yRAxis.setDrawAxisLine(false);
            yRAxis.setDrawGridLines(false);

            Description description = new Description();
            description.setText("");
            description.setTextColor(Color.WHITE);
            description.setTextSize(14);


            lineChart.setDoubleTapToZoomEnabled(false);
            lineChart.setDrawGridBackground(false);
            lineChart.setDescription(description);
            lineChart.invalidate();
            marker.setChartView(lineChart);
            lineChart.setMarker(marker);
        }
    }
}