package com.example.githubproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "WorkOutDB.db";
    private static String DB_PATH = "";
    private Context mcontext;
    private final static String TAG = "DataBaseHelper";

    // DBHelper 생성자
    public DBHelper(Context context, int version) {

        super(context, DATABASE_NAME, null, version);
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mcontext = context;
        dataBaseCheck();
    }

    private void dataBaseCheck() {
        File dbFile = new File(DB_PATH + DATABASE_NAME);
        if (!dbFile.exists()) {
            dbCopy();
            Log.d(TAG,"Database is copied.");
        }
    }

    // Person Table 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE 운동목록(ID INT,Exercise_Type TEXT, Exercise_Name Text, TTS INT)");
    }

    // Person Table Upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS 운동목록");
        onCreate(db);
    }

    // Person Table 데이터 입력
    public void insert(int ID, String Exercise_type, String Exercise_Name, int TTS) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO 운동목록 VALUES(" + ID + ", '" + Exercise_type + "', '" + Exercise_Name + "', " + TTS + ")");
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
        db.execSQL("DELETE FROM 운동목록 WHERE ID = '" + ID + "'");
        db.close();
    }

    // Person Table 조회
    public String getResult() {
        // 읽기가 가능하게 DB 열기
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
// assets 복사해서 가져오기
    public void dbCopy() {

        try {
            File folder = new File(DB_PATH);
            if (!folder.exists()) {
                folder.mkdir();
            }

            InputStream inputStream = mcontext.getAssets().open(DATABASE_NAME);
            String out_filename = DB_PATH + DATABASE_NAME;
            OutputStream outputStream = new FileOutputStream(out_filename);
            byte[] mBuffer = new byte[1024];
            int mLength;
            while ((mLength = inputStream.read(mBuffer)) > 0) {
                outputStream.write(mBuffer,0,mLength);
            }
            outputStream.flush();;
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("dbCopy","IOException 발생함");
        }
    }
}
