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
        query.append(Constant.TABLE_NAME[2]+"."+Constant.RANK_PROFITSCORE+ ",  ");
        query.append(Constant.TABLE_NAME[2]+"."+Constant.RANK_STABLESCORE+ ",  ");
        query.append(Constant.RANK_PROFITSCORE + "*" + p_ratio + "+"+Constant.RANK_STABLESCORE + "*" + s_ratio);
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
        Log.d("조건", query.toString());
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
            d.setRatio(ratio);
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
        return list.size() > 0 ? list : null;
    }


    //성별 배열 받아오기
    public int[] findGender(int code){
        int[] list = new int[2];
        String selection = Constant.DATA_CODE + " LIKE ?";
        String[] selectionArgs = {Integer.toString(code)};
        String[] columns = {"male", Constant.DATA_GENDER[1]};

        Cursor cursor  = database.query(Constant.TABLE_NAME[3], columns, selection, selectionArgs, null,null, null);
        Log.d("검색결과", "코드" + code + " "+cursor.getCount());
        if(cursor.getCount() > 0) {
            cursor.moveToNext();
            list[0] = cursor.getInt(0);
            list[1] = cursor.getInt(1);
        }
        cursor.close();
        return list;
    }
    //세대 인구(10대~60대)
    public int[] findGeneration(int code){
        int[] list = new int[Constant.DATA_GENERATGION.length];
        String selection = Constant.DATA_CODE + " LIKE ?";
        String[] selectionArgs = {Integer.toString(code)};
        String[] columns = Constant.DATA_GENERATGION;

        Cursor cursor  = database.query(Constant.TABLE_NAME[3], columns, selection, selectionArgs, null,null, null);
        if(cursor.getCount() > 0) {
            cursor.moveToNext();
            for(int i=0; i<list.length; i++){
                list[i] = cursor.getInt(i);
            }
        }
        cursor.close();
        return list;
    }
    //가족수 당 세대수(1~5)
    public int[] findHouse(int code){
        int[] list = new int[Constant.DATA_HOUSE.length];
        String selection = Constant.DATA_CODE + " LIKE ?";
        String[] selectionArgs = {Integer.toString(code)};
        String[] columns = Constant.DATA_HOUSE;

        Cursor cursor  = database.query(Constant.TABLE_NAME[3], columns, selection, selectionArgs, null,null, null);
        if(cursor.getCount() > 0) {
            cursor.moveToNext();
            for(int i=0; i<list.length; i++){
                list[i] = cursor.getInt(i);
            }
        }
        cursor.close();
        return list;
    }

    public int[] findLiveWork(int code){
        int[] list = new int[2];
        String selection = Constant.DATA_CODE + " LIKE ?";
        String[] selectionArgs = {Integer.toString(code)};
        String[] columns = {Constant.DATA_LIVE, Constant.DATA_WORK};

        Cursor cursor  = database.query(Constant.TABLE_NAME[3], columns, selection, selectionArgs, null,null, null);
        if(cursor.getCount() > 0) {
            cursor.moveToNext();
            list[0] = cursor.getInt(0);
            list[1] = cursor.getInt(1);
        }
        cursor.close();
        return list;
    }

    //편의점수
    public int findConv(int code){
        int list = 0;
        String selection = Constant.DATA_CODE + " LIKE ?";
        String[] selectionArgs = {Integer.toString(code)};
        String[] columns = {Constant.DATA_CONVNUM};

        Cursor cursor  = database.query(Constant.TABLE_NAME[0], columns, selection, selectionArgs, null,null, null);
        while(cursor.moveToNext()){
            list = cursor.getInt(0);
        }
        cursor.close();
        return list;
    }

    //3년치 임대시세 (서울, 지역구, 동)받아오기
    public int[][] findRent3(int code, int parentcode, int[] conv){
        int[][] list = new int[3][3];
        String query = Constant.FIND_RENT;
        query = query + parentcode;
        query = query + Constant.FIND_RENT2 + code + " ORDER BY "+ Constant.DATA_CODE + " ASC";

        Cursor cursor = database.rawQuery(query.toString(),null);
        int index = 0;
        while (cursor.moveToNext()){
            list[index][0] = cursor.getInt(0);
            list[index][1] = cursor.getInt(1);
            list[index][2] = cursor.getInt(2);
            conv[index] = cursor.getInt(3);
            index++;
        }
        cursor.close();
        return list;
    }

    public void findProfit_Survive(int code, int parentcode, int[][] profit, float[][] survive){
        String query = Constant.FIND_PROFIT;
        query = query + parentcode;
        query = query + Constant.FIND_PROFIT2 + code + " ORDER BY "+ Constant.DATA_CODE + " ASC";

        Cursor cursor = database.rawQuery(query.toString(),null);
        int index = 0;
        while (cursor.moveToNext()){
            profit[index][0] = cursor.getInt(3);
            profit[index][1] = cursor.getInt(4);
            profit[index][2] = cursor.getInt(5);
            profit[index][3] = cursor.getInt(6);
            survive[index][0] = cursor.getFloat(7);
            survive[index][1] = cursor.getFloat(8);
            survive[index][2] = cursor.getFloat(9);
            survive[index][3] = cursor.getFloat(10);
            index++;
        }
        cursor.close();
    }
}
