package com.example.githubproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.view.View;

public class RoutineListDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "RoutineList.db";

    public RoutineListDBHelper(View.OnClickListener context) {
        super((Context) context, "RoutineList.db", null, 1);
    }

    public static class FeedEntry implements BaseColumns {
    public static final String TABLE_NAME = "routine_tb";
    public static final String COLUMN_NAME_TITLE = "Set_Number";
    public static final String COLUMN_NAME_SUBTITLE1 = "Number_Per_Set";
    public static final String COLUMN_NAME_SUBTITLE2 = "Weight";

}

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY ," +
                    FeedEntry.COLUMN_NAME_TITLE + " Set_Number," +
                    FeedEntry.COLUMN_NAME_SUBTITLE1 + " Number_Per_Set," +
                    FeedEntry.COLUMN_NAME_SUBTITLE2 + " Weight)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
