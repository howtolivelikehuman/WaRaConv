package com.swapp.waraconvapp.DB;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataBase {
    SQLiteDatabase database;
    DatabaseHelper dbHelper;

    public DataBase(DatabaseHelper databaseHelper) {
        this.dbHelper = databaseHelper;
        database = dbHelper.getWritableDatabase();
    }

    //전체 지역이름 받아오기
    public ArrayList<String> findArea(){
        ArrayList<String> list = new ArrayList<>();
        String coloums[] = {Constant.DATA_NAME};
        Cursor cursor = database.query(Constant.TABLE_NAME[0], coloums, null, null, null, null, null);
        int recordCount = cursor.getCount();
        Log.d("지역 수", Integer.toString(recordCount));

        while(cursor.moveToNext()){
            list.add(cursor.getString(0));
        }
        cursor.close();
        return list;
    }

    //코드 불러오기
    public ArrayList<Integer> findCode(ArrayList<String> name){
        ArrayList<Integer> list = new ArrayList<>();
        String columns[] = {Constant.DATA_CODE};

        if(name.get(0).equals("전체")){
            String selection = Constant.DATA_PARENT + " LIKE ?";
            String[] selectionArgs = {"11"};

            Cursor cursor  = database.query(Constant.TABLE_NAME[0], columns, selection, selectionArgs, null,null, null);
            while(cursor.moveToNext()){
                list.add(cursor.getInt(0));
            }
            cursor.close();
        }else{
            for(int i=0; i<name.size(); i++){
                String selection = Constant.DATA_NAME + " LIKE ?";
                String[] selectionArgs = {"%"+name.get(i)+"%"};

                Cursor cursor  = database.query(Constant.TABLE_NAME[0], columns, selection, selectionArgs, null,null, null);
                while(cursor.moveToNext()){
                    list.add(cursor.getInt(0));
                }
                cursor.close();
            }
        }


        return list;
    }

    //전체 지역 임대시세 받아오기
    public ArrayList<String[]> findRent(){
        ArrayList<String[]> list = new ArrayList<>();

        String selection = Constant.DATA_PARENT + " LIKE ?";
        String[] selectionArgs = {"11"};
        String[] columns = {Constant.DATA_NAME, Constant.DATA_RENT[2]};
        String sortorder = Constant.DATA_RENT[2] + " ASC";

        Cursor cursor  = database.query(Constant.TABLE_NAME[0], columns, selection, selectionArgs, null,null, sortorder);
        int recordCount = cursor.getCount();
        Log.d("지역 수", Integer.toString(recordCount));

        if(cursor.getCount() > 0) {
            String[] areaRent;
            while(cursor.moveToNext()){
                areaRent = new String[2];
                areaRent[0] = cursor.getString(0);
                areaRent[1] = Integer.toString(cursor.getInt(1));
                list.add(areaRent);
            }
        }
        cursor.close();
        return list;
    }

    //랭킹 받아오기
    public ArrayList<DetailInfo> findRank(int rentalmax, int rentalmin, ArrayList<Integer> areacode, int ratio){
        ArrayList<DetailInfo> list = new ArrayList<DetailInfo>();
        DetailInfo d;
        float p_ratio = (float)ratio/100;
        float s_ratio = (float)(100-ratio)/100;

        StringBuffer query = new StringBuffer();

        query.append(Constant.FIND_RANK1);
        query.append(Constant.RANK_PROFITSCORE + "*" + p_ratio + "+");
        query.append(Constant.RANK_STABLESCORE + "*" + s_ratio);
        query.append(Constant.FIND_RANK2);

        //지역 범위
        if(areacode != null){
            query.append(Constant.FIND_RANGE_AREA);
            query.append(areacode.get(0));

            for(int i=1; i< areacode.size(); i++){
                query.append(" OR ");
                query.append(Constant.TABLE_NAME[1]+"."+Constant.MAP_PARENT+ " = ");
                query.append(areacode.get(i));
            }
        }
        query.append(" INTERSECT ");

        //임대시세 범위
        query.append(Constant.FIND_RANGE_RENT);
        query.append(rentalmin);
        query.append(" AND ");
        query.append(rentalmax);

        query.append(Constant.FIND_RANK3);

        Cursor cursor = database.rawQuery(query.toString(),null);
        Log.d("first", query.toString());

        int ranknum = 1;
        while (cursor.moveToNext()){
            d = new DetailInfo();
            d.setCode(cursor.getInt(0));
            d.setName(cursor.getString(1));
            d.setParentName(cursor.getString(2));
            d.setParent(cursor.getInt(3));
            d.setProfitscore(cursor.getFloat(4));
            d.setStablescore(cursor.getFloat(5));
            d.setTotalscore(cursor.getFloat(6));
            d.setRanknum(ranknum);
            list.add(d);
            if(ranknum > 29){
                break;
            }
            else{
                ranknum++;
            }
        }
        cursor.close();
        return list;
    }
}
