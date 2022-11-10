package com.example.githubproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "Exercise.db";

    // DBHelper 생성자
    public DBHelper(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    // Person Table 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Exercise(ID INT,Exercise_Type TEXT, Exercise_Name Text, is_tts INT)");
    }

    // Person Table Upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Exercise");
        onCreate(db);
    }

    // Person Table 데이터 입력
    public void insert(int ID, String Exercise_type, String Exercise_Name, int is_tts) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Exercise VALUES(" + ID + ", '" + Exercise_type + "', '" + Exercise_Name + "', " + is_tts + ")");
        db.close();
    }

//    // Person Table 데이터 수정
//    public void Update(int ID, String Exercise_type, String Exercise_Name, int is_tts) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("UPDATE Exercise SET ID = " + ID + "', " + Exercise_type + ", '" + Exercise_Name + ", '" + is_tts + "'" + " WHERE ID = '" + ID + "'");
//        db.close();
//    }

    // Person Table 데이터 삭제
    public void Delete(int ID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Exercise WHERE ID = '" + ID + "'");
        db.close();
    }

    // Person Table 조회
    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM Exercise", null);
        while (cursor.moveToNext()) {
            result += " 번호 : " + cursor.getInt(0)
                    + ", 운동종류 : "
                    + cursor.getString(1)
                    + ", 운동이름 : "
                    + cursor.getString(2)
                    + ", tts여부 : "
                    + cursor.getInt(3)
                    + "\n";
        }

        return result;
    }
}
