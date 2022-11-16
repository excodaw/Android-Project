package com.example.githubproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BodyInfoDBHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "BodyInfo.db";

    public BodyInfoDBHelper(Context context, int version) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE BodyInfo(Date INTEGER, Weight INTEGER, BodyFat INTEGER, Muscle INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS BodyInfo");
        onCreate(db);
    }

    public void insert(int date, int weight, int bodyfat, int muscle) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO BodyInfo VALUES("+ date + ", '" + weight +"','"+ bodyfat + "', "+ muscle + ")");
        db.close();
    }

}
