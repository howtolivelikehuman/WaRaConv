package com.swapp.waraconvapp.DB;

public class Constant {
    private Constant(){}

    public static final String PACKAGE_NAME = "com.swapp.waraconv.app";

    public static final String DATABASE_NAME = "WaraDB";
    public static final String TABLE_RANK = "Rank";

    //for classify
    public static final String DATA_CODE = "code";
    public static final String DATA_NAME = "name";
    public static final String DATA_TYPE = "type";
    public static final String DATA_PARENT = "parent";
    public static final String DATA_YEAR = "year";

    //dataset
    public static final String DATA_MALE = "male";
    public static final String DATA_FEMALE = "female";
    public static final String DATA_ONE_HOUSEHOLD = "onehouse";
    public static final String DATA_CONV_NUM = "convnum";
    public static final String DATA_RENT = "rent";


    public static final String TABLE_DATA_ = "Data";
    public static final String[] TABLE_NAME = {"data2018", "data2019", "data2020", "Rank"};

    public static final String CREATE_TABLE_DATA = "create table if not exists "
            + TABLE_DATA_ + "("
            + DATA_NAME +" text, "
            + DATA_CODE +" integer, "
            + DATA_TYPE +" text, "
            + DATA_PARENT + " text, "
            + DATA_YEAR + " integer, "
            + DATA_MALE + " integer, "
            + DATA_FEMALE + " integer, "
            + DATA_ONE_HOUSEHOLD + " integer, "
            + DATA_CONV_NUM + " integer, "
            + DATA_RENT + " integer)" ;

}
