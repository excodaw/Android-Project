package com.example.githubproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MyMarkerView extends MarkerView {

    private TextView tvContent;
    RecordFragment rf = new RecordFragment();
    int x;
    String a="";

    public MyMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = (TextView)findViewById(R.id.tvContent);

    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)

    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {

            CandleEntry ce = (CandleEntry) e;

            tvContent.setText("" + Utils.formatNumber(ce.getHigh(), 0, true)+"" + Utils.formatNumber(ce.getHigh(), 0, true));
        } else {
            x=(int)e.getX();
            MyDBHelper mbhelper = new MyDBHelper(getContext(),1);
            SQLiteDatabase mb = mbhelper.getReadableDatabase();
            Cursor cursor = mb.rawQuery("SELECT number, Record_Date FROM Record WHERE Record_Type = '"+ "몸무게"+ "'",null);
            for(int i=0; i<e.getX()+1; i++){
                cursor.moveToNext();
            }
            a=cursor.getString(1);
            Toast.makeText(getContext(), cursor.getInt(0)+"kg"+cursor.getString(1)+"", Toast.LENGTH_SHORT).show();
            tvContent.setText("" + Utils.formatNumber(e.getX()+1, 0, true)+"번째 측정\n" + cursor.getString(1)+"kg");
        }

        super.refreshContent(e, highlight);
    }
    public String db(){
        return a;

    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
