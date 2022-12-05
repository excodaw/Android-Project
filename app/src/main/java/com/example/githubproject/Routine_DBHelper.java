package com.example.githubproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Routine_DBHelper extends SQLiteOpenHelper{
    static final String Routine_DATABASE_NAME = "Routine_list.db";

    // DBHelper 생성자
    public Routine_DBHelper(Context context, int version) {
        super(context, Routine_DATABASE_NAME, null, version);
    }

    // Person Table 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Routine(Routine_Name TEXT, Exercise_Name TEXT, Time INT, TTS INT, Reps INT, Sets INT)");
    }

    // Person Table Upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Routine");
        onCreate(db);
    }

    // Person Table 데이터 입력
    public void insert(String Routine_Name, String Exercise_Name, int Time, int TTS, int Reps, int Sets) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Routine VALUES('" + Routine_Name + "','" + Exercise_Name + "', " + Time + ", " + TTS + ", " + Reps + ", " + Sets + ")");
        db.close();
    }

    // Person Table 데이터 수정
    public void Update( String Routine_Name ) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Routine SET Routine_Name = '"+ Routine_Name +"' WHERE Routine_Name = '123214124124'");
        db.close();
    }

    public int count(String routine_name) {
        SQLiteDatabase db = getReadableDatabase();
        int count = 0;

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM Routine WHERE Routine_Name =  '" + routine_name + "'", null);
        while(cursor.moveToNext()) {
            count = cursor.getInt(0);
        }
        db.close();
        return count;
    }

    public void delete(String routine_name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Routine WHERE Routine_Name = '" + routine_name + "'");
        db.close();
    }

    public void delete2(String Exercise_name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Routine WHERE Exercise_Name = '" + Exercise_name + "'");
        db.close();
    }

    public void update_set(int set, String routine_name, String workout) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Routine SET Sets = " + set + " WHERE Routine_Name = '" + routine_name + "'AND" + " Exercise_Name = '" + workout + "'");
        db.close();
    }

    public void update_rep(int rep, String routine_name, String workout) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Routine SET Reps = " + rep + " WHERE Routine_Name = '" + routine_name + "'AND" + " Exercise_Name = '" + workout + "'");
        db.close();
    }
}
