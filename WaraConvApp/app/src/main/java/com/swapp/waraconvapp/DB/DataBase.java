package com.swapp.waraconvapp.DB;

import android.database.sqlite.SQLiteDatabase;

import java.io.FileReader;

public class DataBase {
    SQLiteDatabase database;
    DatabaseHelper dbHelper;

    public DataBase(DatabaseHelper databaseHelper) {
        this.dbHelper = databaseHelper;
        database = dbHelper.getWritableDatabase();
    }

    public void createTableData(){
        database.execSQL(Constant.CREATE_TABLE_DATA);
    }
}
