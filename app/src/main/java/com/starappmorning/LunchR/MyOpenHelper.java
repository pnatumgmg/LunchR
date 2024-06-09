package com.starappmorning.LunchR;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

    // データーベースのバージョン
    private static final int DATABASE_VERSION = 2;

    // データーベース情報を変数に格納
    private static final String DATABASE_NAME = "MyMealDB.db";
    private static final String Dinner_TB = "myDinnertb";
    private static final String Lunch_TB = "myLunchtb";
    private static final String _ID = "_id";
    private static final String Menu_Dinner = "dinner_menu";
    private static final String Menu_Lunch = "lunch_menu";
    private static final String Shop_Lunch = "lunch_shop";
    private static final String Price_Lunch = "lunch_price";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Dinner_TB + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    Menu_Dinner + " TEXT)";

    private static final String SQL_CREATE_ENTRIES_LUNCH =
            "CREATE TABLE " + Lunch_TB + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    Menu_Lunch + " TEXT," +
                    Shop_Lunch + " TEXT," +
                    Price_Lunch + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Dinner_TB;

    private static final String SQL_DELETE_ENTRIES_LUNCH =
            "DROP TABLE IF EXISTS " + Lunch_TB;

    MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_LUNCH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_LUNCH);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
