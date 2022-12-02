package com.example.githubproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ResttimeDBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "Rest_time.db";

    public ResttimeDBHelper(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Rest_Time(Time INT)");
        db.execSQL("INSERT INTO Rest_Time VALUES (30)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Rest_Time");
        onCreate(db);
    }

    public void Update( int time ) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Rest_Time SET Time = " + time);
        db.close();
    }


}
