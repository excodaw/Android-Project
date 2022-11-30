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
            db.execSQL("CREATE TABLE Routine(Routine_Name TEXT,Exercise_Name TEXT,Time INT,TTS INT)");
        }

        // Person Table Upgrade
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Routine");
            onCreate(db);
        }

        // Person Table 데이터 입력
        public void insert(String Routine_Name, String Exercise_Name, int Time, int TTS) {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("INSERT INTO 운동목록 VALUES("+ Routine_Name + ", '" + Exercise_Name +"','"+ Time + "', "+ TTS + ")");
            db.close();
        }

    // Person Table 데이터 수정
    public void Update( int Time ) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Routine SET Time = Time WHERE Time = '" + Time + "'");
        db.close();
    }

//    // Person Table 데이터 삭제
//    public void Delete(String ID) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("DELETE Exercise WHERE ID = '" + ID + "'");
//        db.close();
//    }

        // Person Table 조회
//        public String getResult() {
//            // 읽기가 가능하게 DB 열기
//            SQLiteDatabase db = getReadableDatabase();
//            String result = "";
//
//            // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
//            Cursor cursor = db.rawQuery("SELECT * FROM Routine", null);
//            while (cursor.moveToNext()) {
//                result += " 수치 : " + cursor.getInt(0)
//                        + ", 측정목록 : "
//                        + cursor.getString(1)
//                        + ", 날짜 : "
//                        + cursor.getString(2)
//                        + "\n";
//            }
//
//            return result;
//        }
}
