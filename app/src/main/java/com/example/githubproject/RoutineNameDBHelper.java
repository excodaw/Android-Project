package com.example.githubproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RoutineNameDBHelper extends SQLiteOpenHelper {

    public RoutineNameDBHelper(Context context, int version) {
        super(context, "RoutineName.db", null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Routine_Name(Name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Routine_Name");
        onCreate(db);
    }

    public void insert(String Routine_Name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Routine_Name VALUES('"+ Routine_Name + "')");
        db.close();
    }

    public void delete(String Routine_Name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Routine_Name WHERE Name = '" + Routine_Name + "'");
        db.close();
    }
}
