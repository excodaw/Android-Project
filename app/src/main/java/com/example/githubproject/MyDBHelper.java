package com.example.githubproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME2 = "Records.db";

    public MyDBHelper(Context context, int version) {
        super(context, DATABASE_NAME2, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Record(number INT,Record_Type TEXT,Record_Date DATETIME DEFAULT (datetime('now','localtime')))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Record");
        onCreate(db);
    }

    public void insert(int number, String Record_type, String Record_date) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Record VALUES(" + number + ", '" + Record_type + "', '" + Record_date + "')");
        db.close();
    }

    public int getCount(String record_name) {
        SQLiteDatabase db = getReadableDatabase();
        int count = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM Record WHERE Record_Type =  '" + record_name + "'", null);
        while(cursor.moveToNext()) {
            count = cursor.getInt(0);
        }
        db.close();
        return count;
    }


    public String getResult() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM Record", null);
        while (cursor.moveToNext()) {
            result += " 수치 : " + cursor.getInt(0)
                    + ", 측정목록 : "
                    + cursor.getString(1)
                    + ", 날짜 : "
                    + cursor.getString(2)
                    + "\n";
        }
        db.close();
        return result;
    }

    public ArrayList<Result> getResultList() {
        ArrayList<Result> resultList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM Record", null);
        while (cursor.moveToNext()) {
            resultList.add(new Result(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                    )
            );
        }
        db.close();
        return resultList;
    }

    public ArrayList<Result> getFilteredResultList(String type) {
        ArrayList<Result> resultList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM Record", null);
        while (cursor.moveToNext()) {
            if(type.equals(cursor.getString(1)))
                resultList.add(new Result(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2)
                        )
                );
        }
        db.close();
        return resultList;
    }
}