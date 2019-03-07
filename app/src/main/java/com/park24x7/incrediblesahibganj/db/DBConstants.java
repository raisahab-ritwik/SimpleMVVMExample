package com.park24x7.incrediblesahibganj.db;


public interface DBConstants {

    public static final int DB_VERSION = 1; // 7th Sept 2018

    public static final String DB_NAME = "IncredibleSahibganj.db";
    /* public static final String DB_NAME = Environment.getExternalStorageDirectory() + "/IncredibleSahibganj.db";*/ //This is for debugging purpose only

    public static final String _ID = "_id";

    final String CREATE_TABLE_BASE = "CREATE TABLE IF NOT EXISTS ";

    final String ON = " ON ";

    final String PRIMARY_KEY = " PRIMARY KEY";

    final String INTEGER = " Integer";

    final String TEXT = " TEXT";

    final String DATE_TIME = " DATETIME";

    final String BLOB = " BLOB";

    final String AUTO_INCREMENT = " AUTOINCREMENT";

    final String UNIQUE = "UNIQUE";

    final String START_COLUMN = " ( ";

    final String FINISH_COLUMN = " ) ";

    final String COMMA = ",";

    final String ON_CONFLICT_REPLACE = "ON CONFLICT REPLACE";

    // Tourist Attraction Table
    public static final String TOURIST_ATTRACTION_TABLE = " touristAttractionTable";

    public static final String TOURIST_ATTRACTION_ID = "touristAttractionID";

    public static final String TOURIST_ATTRACTION_NAME = "touristAttractionName";

    public static final String TOURIST_ATTRACTION_DESCRIPTION = "touristAttractionDescription";

    public static final String TOURIST_ATTRACTION_LATITUDE = "touristAttractionLatitude";

    public static final String TOURIST_ATTRACTION_LONGITUDE = "touristAttractionLongitude";


    // Picture Table
    public static final String PICTURE_TABLE = " pictureTable";

    public static final String PICTURE_ID = "pictureID";

    public static final String PICTURE = "picture";


    //ATM Table

}