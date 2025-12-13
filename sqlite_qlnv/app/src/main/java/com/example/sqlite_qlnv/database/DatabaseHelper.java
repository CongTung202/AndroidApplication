package com.example.sqlite_qlnv.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "QLNV.db";
    private static final int VERSION = 2; // Increased version to trigger onUpgrade

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Person(" +
                "Id INTEGER PRIMARY KEY," +
                "Name TEXT," +
                "Birthday TEXT," +
                "Gender TEXT)";
        db.execSQL(sql);

        // Insert sample data
        db.execSQL("INSERT INTO Person VALUES(1, 'Nguyễn Công Tùng', '20/02/2005', 'Nam')");
        db.execSQL("INSERT INTO Person VALUES(2, 'Trịnh Thị Thu Thảo', '15/05/2005', 'Nữ')");
        db.execSQL("INSERT INTO Person VALUES(3, 'Lê Minh Trí', '11/08/2005', 'Nữ')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Person");
        onCreate(db);
    }
}