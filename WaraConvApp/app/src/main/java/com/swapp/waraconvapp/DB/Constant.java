package com.swapp.waraconvapp.DB;

public class Constant {
    private Constant(){}

    public static final String PACKAGE_NAME = "com.swapp.waraconv.app";

    public static final String DATABASE_NAME = "WaraDB";
    public static final String[] TABLE_NAME = {"DATA", "MAP", "RANK"};
    
    //for classify
    public static final String DATA_CODE = "code";
    public static final String DATA_NAME = "name";
    public static final String DATA_PARENT = "parent";
    public static final String DATA_PARENTNAME = "parentName";

    //dataset
    public static final String[] DATA_MALE = {"male2018", "male2019", "male2020"};
    public static final String[] DATA_FEMALE = {"female2018", "female2019", "female2020"};
    public static final String[] DATA_ONE_HOUSEHOLD = {"onehouse2018", "onehouse2019", "onehouse2020"};
    public static final String[] DATA_CONV_NUM = {"convnum2018", "convnum2019", "convnum2020"};
    public static final String[] DATA_RENT = {"rent2018", "rent2019", "rent2020"};

    //map
    public static final String MAP_CHILD = "childcode";
    public static final String MAP_PARENT = "parentcode";

    //RANK
    public static final String RANK_CODE = "code";
    public static final String RANK_PROFITSCORE = "profitscore";
    public static final String RANK_STABLESCORE = "stablescore";
    public static final String RANK_TOTALSCORE = "totalscore";



    /*SELECT rank.code, data2020.name, rank.profitscore, rank.stablescore, rank.profitscore*0.7+rank.stablescore*0.1 as totalscore
    FROM data2020, rank
    WHERE rank.code IN (
        SELECT map.childcode From map
        where map.parentcode = 11110 OR map.parentcode = 11200
        INTERSECT

        SELECT data2020.code From data2020
        WHERE data2020.rent BETWEEN 111111 AND 222222
    )AND rank.code = data2020.code
    ORDER BY totalscore desc */

    public static final String FIND_RANK1 = "SELECT " + TABLE_NAME[2]+"."+RANK_CODE+ ", "
            + TABLE_NAME[0]+"."+DATA_NAME+ ", "
            + TABLE_NAME[0]+"."+DATA_PARENTNAME+", "
            + TABLE_NAME[0]+"."+DATA_PARENT+", "
            + TABLE_NAME[2]+"."+RANK_PROFITSCORE+ ", "
            + TABLE_NAME[2]+"."+RANK_STABLESCORE + ", ";

    public static final String FIND_RANK2 = " AS " + RANK_TOTALSCORE
            + " FROM " + TABLE_NAME[0] +", " + TABLE_NAME[2]
            + " WHERE " + TABLE_NAME[2]+"."+RANK_CODE + " IN (";

    public static final String FIND_RANGE_AREA = "SELECT " + TABLE_NAME[1]+"."+MAP_CHILD
            + " FROM " + TABLE_NAME[1]
            + " WHERE " + TABLE_NAME[1]+"."+MAP_PARENT+ " = ";

    public static final String FIND_RANGE_RENT = "SELECT " + TABLE_NAME[0]+"."+DATA_CODE
            + " FROM " + TABLE_NAME[0]
            + " WHERE " + TABLE_NAME[0]+"."+DATA_RENT[2]+ " BETWEEN ";

    public static final String FIND_RANK3 = " ) AND " + TABLE_NAME[2]+"."+RANK_CODE
            + " = " + TABLE_NAME[0]+"."+DATA_CODE
            + " ORDER BY " + RANK_TOTALSCORE + " DESC";
}
