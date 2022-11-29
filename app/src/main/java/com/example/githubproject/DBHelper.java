package com.example.githubproject;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "WorkOutDB.db";


    // DBHelper 생성자
    public DBHelper(Context context, int version) {

        super(context, DATABASE_NAME, null, version);

    }

    // Person Table 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE 운동목록(ID INTEGER,Exercise_Type TEXT, Exercise_Name TEXT, TTS INTEGER)");
    }

    // Person Table Upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS 운동목록");
        onCreate(db);
    }

    // Person Table 데이터 입력
    public void insert(int ID, String Exercise_Type, String Exercise_Name, int TTS) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO 운동목록 VALUES("+ ID + ", '" + Exercise_Type +"','"+ Exercise_Name + "', "+ TTS + ")");
        db.close();
    }

//    // Person Table 데이터 수정
//    public void Update(int ID, String Exercise_type, String Exercise_Name, int is_tts) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("UPDATE Exercise SET ID = " + ID + "', " + Exercise_type + ", '" + Exercise_Name + ", '" + is_tts + "'" + " WHERE ID = '" + ID + "'");
//        db.close();
//    }

    // Person Table 데이터 삭제
    public void delete(String name) {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM 운동목록 WHERE Exercise_Name = '" + name + "'");
            db.close();
    }

    // Person Table 조회
    public String getResult() {        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM 운동목록", null);
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

//    public ArrayList SelectAllWorkouts() {
//        String SELECT_QUERY = "SELECT Exercise_Name FROM 운동목록";
//        ArrayList workout_name = new ArrayList<String>();
//
//        Cursor cur =  getWritableDatabase().rawQuery(SELECT_QUERY, null);
//        if(cur != null && cur.moveToFirst()) {
//            do {
//                workout_name.add(cur.getString(0));
//            }while(cur.moveToNext());
//        }
//        return workout_name;
//    }

    public void dbCopy(Activity act) {

        AssetManager am = act.getAssets();
        File f = new File("/data/data/com.example.githubproject/databases/"+DATABASE_NAME);

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {

            InputStream is = am.open(DATABASE_NAME);
            BufferedInputStream bis = new BufferedInputStream(is);

            // 만약에 파일이 있다면 복사 하지 않음
            if (f.exists()) {
//                f.delete();   //파일 삭제
//                f.createNewFile();    //파일 재생성
                return;
            }
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);

            int read = -1;
            byte[] buffer = new byte[1024];
            while ((read = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, read);
            }
            bos.flush();

            fos.close();
            bos.close();
            is.close();
            bis.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
