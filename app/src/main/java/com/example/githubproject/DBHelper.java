//package com.example.githubproject;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//public class DBHelper extends SQLiteOpenHelper {
//    static final String TAG = "DBHelper";
//    private static String DB_PATH = "";
//    private static String DB_NAME = "WorkOutDB.db";
//    private SQLiteDatabase mDataBase;
//    private Context mContext;
//
//    // DBHelper 생성자
//    public DBHelper(Context context, int version) {
//
//        super(context, DB_NAME, null, version);
//
//        DB_PATH = "/data/data/" + context.getPackageName() + "/databases";
//        this.mContext = context;
//        dataBaseCheck();
//    }
//
//    private void dataBaseCheck() {
//        File dbFile = new File(DB_PATH + DB_NAME);
//        if (!dbFile.exists()) {
////            dbCopy();
//            Log.d(TAG, "DB is copied")
//        }
//    }
//
//    @Override
//    public synchronized void close() {
//        if (mDataBase != null) {
//            mDataBase.close();
//        }
//        super.close();
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        Log.d(TAG, "onCreate()");
//    }
//
//    @Override
//    public void onOpen(SQLiteDatabase db) {
//        super.onOpen(db);
//
//        Log.d(TAG, "onOpen() : DB Opening");
//    }
//
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        Log.d(TAG, "onUpgrade() : DB Schema Modified and Excuting onCreat()");
//    }
//
////    private void dbCopy() {
////        try {
////            File folder = new File(DB_PATH);
////            if (!folder.exists()) {
////                folder.mkdir();
////            }
////
////        }
////    }
//
////        // Person Table 생성
////        @Override
////        public void onCreate (SQLiteDatabase db){
////            db.execSQL("CREATE TABLE Exercise(ID INT,Exercise_Type TEXT, Exercise_Name Text, is_tts INT)");
////        }
////
////        // Person Table Upgrade
////        @Override
////        public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){
////            db.execSQL("DROP TABLE IF EXISTS Exercise");
////            onCreate(db);
////        }
////
////        // Person Table 데이터 입력
////        public void insert ( int ID, String Exercise_type, String Exercise_Name,int is_tts){
////            SQLiteDatabase db = getWritableDatabase();
////            db.execSQL("INSERT INTO Exercise VALUES(" + ID + ", '" + Exercise_type + "', '" + Exercise_Name + "', " + is_tts + ")");
////            db.close();
////        }
//
////    // Person Table 데이터 수정
////    public void Update(int ID, String Exercise_type, String Exercise_Name, int is_tts) {
////        SQLiteDatabase db = getWritableDatabase();
////        db.execSQL("UPDATE Exercise SET ID = " + ID + "', " + Exercise_type + ", '" + Exercise_Name + ", '" + is_tts + "'" + " WHERE ID = '" + ID + "'");
////        db.close();
////    }
//
////    // Person Table 데이터 삭제
////    public void Delete(String ID) {
////        SQLiteDatabase db = getWritableDatabase();
////        db.execSQL("DELETE Exercise WHERE ID = '" + ID + "'");
////        db.close();
////    }
//
//        // Person Table 조회
//        public String getResult () {
//            // 읽기가 가능하게 DB 열기
//            SQLiteDatabase db = getReadableDatabase();
//            String result = "";
//
//            // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
//            Cursor cursor = db.rawQuery("SELECT * FROM Exercise", null);
//            while (cursor.moveToNext()) {
//                result += " 번호 : " + cursor.getInt(0)
//                        + ", 운동타입 : "
//                        + cursor.getString(1)
//                        + ", 운동종류 : "
//                        + cursor.getString(2)
//                        + ", tts여부 : "
//                        + cursor.getInt(3)
//                        + "\n";
//            }
//
//            return result;
//        }
//    }
//}