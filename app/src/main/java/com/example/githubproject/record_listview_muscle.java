package com.example.githubproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class record_listview_muscle extends AppCompatActivity {

    private MyDBHelper mydbHelper;
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_listview_muscle);

        lineChart = (LineChart) findViewById(R.id.chart);

        mydbHelper = new MyDBHelper(this, 1);

        ArrayList<Entry> entries = new ArrayList<>(); // 데이터를 담을 Arraylist
        ArrayList<Result> resultList = mydbHelper.getFilteredResultList("골격근");

        for(int i=0; i<resultList.size(); i++) {
            entries.add(new Entry(i, resultList.get(i).getNumber()));
        }

        LineData chartData = new LineData(); // 차트에 담길 데이터

        LineDataSet lineDataSet = new LineDataSet(entries, "골격근"); // 데이터가 담긴 Arraylist 를 LineDataSet 으로 변환한다.
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);
        lineChart.getLegend().setTextColor(Color.parseColor("#FFFFFF"));

        chartData.addDataSet(lineDataSet); // 해당 LineDataSet 을 적용될 차트에 들어갈 DataSet 에 넣는다.

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.enableGridDashedLine(8, 24, 0);
        xAxis.setLabelCount(resultList.size() - 1);
        xAxis.setValueFormatter(new IndexAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return resultList.get((int)value).getRecordDate();
            }
        });

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.WHITE);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.invalidate();
        lineChart.setTouchEnabled(false);
        lineChart.setData(chartData);
    }
}