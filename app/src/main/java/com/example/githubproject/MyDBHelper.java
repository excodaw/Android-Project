package com.example.githubproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME2 = "Records.db";

    // DBHelper 생성자
    public MyDBHelper(Context context, int version) {
        super(context, DATABASE_NAME2, null, version);
    }

    // Person Table 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Record(number INT,Record_Type TEXT,Record_Date DATETIME DEFAULT (datetime('now','localtime')))");
    }

    // Person Table Upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Record");
        onCreate(db);
    }

    // Person Table 데이터 입력
    public void insert(int number, String Record_type, String Record_date) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Record VALUES(" + number + ", '" + Record_type + "', '" + Record_date + "')");
        db.close();
    }

//    // Person Table 데이터 수정
//    public void Update(int ID, String Exercise_type, String Exercise_Name, int is_tts) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("UPDATE Exercise SET ID = " + ID + "', " + Exercise_type + ", '" + Exercise_Name + ", '" + is_tts + "'" + " WHERE ID = '" + ID + "'");
//        db.close();
//    }

//    // Person Table 데이터 삭제
//    public void Delete(String ID) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("DELETE Exercise WHERE ID = '" + ID + "'");
//        db.close();
//    }

    // Person Table 조회
    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM Record", null);
        while (cursor.moveToNext()) {
            result += " 수치 : " + cursor.getInt(0)
                    + ", 측정목록 : "
                    + cursor.getString(1)
                    + ", 날짜 : "
                    + cursor.getString(2)
                    + "\n";
        }

        return result;
    }
}