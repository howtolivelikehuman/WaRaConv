package com.swapp.waraconvapp.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, Constant.DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    public void onOpen(SQLiteDatabase db){
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        println("onUpgrade 호출됨 : " +i+" -> "+ i1);
        if(i1 > 1){
            for(int a=0; a<Constant.TABLE_NAME.length; a++){
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Constant.TABLE_NAME[a]);
            }

        }
    }
    public static void println(String data){
        Log.d("DB" , data);
    }
}
