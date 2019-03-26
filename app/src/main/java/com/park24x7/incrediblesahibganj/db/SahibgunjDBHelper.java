package com.park24x7.incrediblesahibganj.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SahibgunjDBHelper extends SQLiteOpenHelper implements DBConstants {

    private static final String TAG = "SahibgunjDBHelper";
    private static SahibgunjDBHelper mDatabase;
    private SQLiteDatabase mDb;

    public SahibgunjDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static final SahibgunjDBHelper getInstance(Context context) {
        if (mDatabase == null) {
            mDatabase = new SahibgunjDBHelper(context);
            mDatabase.getWritableDatabase();
        }
        return mDatabase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.e(TAG, "oncreate tables");
        // create table
        String[] createStatements = getCreatetableStatements();
        int total = createStatements.length;
        for (int i = 0; i < total; i++) {
            Log.i(TAG, "executing create query " + createStatements[i]);
            Log.i("Database", "Database created");
            db.execSQL(createStatements[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade drop older tables
        Log.e("Tag", "Old version" + oldVersion + " New version: " + newVersion + "Constant variable version name: " + DB_VERSION);
        db.execSQL("DROP TABLE IF EXISTS " + TOURIST_ATTRACTION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PICTURE_TABLE);
        //db.execSQL("DROP TABLE IF EXISTS " + MEMBERS_DIRECTORY_TABLE);
        // db.execSQL("DROP TABLE IF EXISTS " + MEMBERS_DIRECTORY_SHSS_TABLE);

        db.setVersion(DB_VERSION);

        // create new tables
        onCreate(db);
    }

    private String[] getCreatetableStatements() {

        // String[] create = new String[4];
        String[] create = new String[2];

        // Tourist Attraction table -> _id , touristAttractionID, touristAttractionName, touristAttractionDescription, touristAttractionLatitude,  touristAttractionLatitude
        String touristAttractionTableStatement = CREATE_TABLE_BASE + TOURIST_ATTRACTION_TABLE + START_COLUMN + _ID + INTEGER + PRIMARY_KEY
                + AUTO_INCREMENT + COMMA + TOURIST_ATTRACTION_ID + TEXT + COMMA + TOURIST_ATTRACTION_DESCRIPTION + TEXT + COMMA + TOURIST_ATTRACTION_LATITUDE + TEXT + COMMA +
                TOURIST_ATTRACTION_LONGITUDE + TEXT + COMMA + TOURIST_ATTRACTION_NAME + TEXT + COMMA + UNIQUE + START_COLUMN
                + TOURIST_ATTRACTION_ID + FINISH_COLUMN + ON_CONFLICT_REPLACE + FINISH_COLUMN;

        // Picture table -> _id , muId , threadId , image , latitude, longitude , comments , keywords ,
        // address , date , time , schoolCode ,villageName , otherData
        String pictureTableStatement = CREATE_TABLE_BASE + PICTURE_TABLE + START_COLUMN + _ID + INTEGER + PRIMARY_KEY
                + AUTO_INCREMENT + COMMA + PICTURE_ID + TEXT + COMMA + PICTURE + TEXT + COMMA + IS_FEATURED + TEXT + COMMA + TOURIST_ATTRACTION_ID
                + TEXT + COMMA + UNIQUE + START_COLUMN + PICTURE_ID + FINISH_COLUMN + ON_CONFLICT_REPLACE + FINISH_COLUMN;

        // MEMBERS_DIRECTORY_TABLE --> _id,
       /* String membersDirectoryTableStatement = CREATE_TABLE_BASE + MEMBERS_DIRECTORY_TABLE + START_COLUMN + _ID + INTEGER
                + PRIMARY_KEY + AUTO_INCREMENT + COMMA + CITY + TEXT + COMMA + ID + TEXT + COMMA + NAME + TEXT + COMMA + ID_NO
                + TEXT + COMMA + SEQUENCE + TEXT + COMMA + SPOUSE_NAME + TEXT + COMMA + CONTACT_NO + TEXT + COMMA + MOBILE + TEXT + COMMA + EMAIL + TEXT
                + COMMA + DESIGNATION + TEXT + COMMA + ADD1 + TEXT + COMMA + ADD2 + TEXT + COMMA + ADD3 + TEXT + COMMA + PIN
                + TEXT + COMMA + TOWN + TEXT + COMMA + PIC + TEXT + COMMA + UNIQUE + START_COLUMN + ID + FINISH_COLUMN
                + ON_CONFLICT_REPLACE + FINISH_COLUMN;*/

        // MEMBERS_DIRECTORY_TABLE --> _id,
        /*String membersDirectorySSHTableStatement = CREATE_TABLE_BASE + MEMBERS_DIRECTORY_SHSS_TABLE + START_COLUMN + _ID + INTEGER
                + PRIMARY_KEY + AUTO_INCREMENT + COMMA + CITY + TEXT + COMMA + ID + TEXT + COMMA + NAME + TEXT + COMMA + ID_NO
                + TEXT + COMMA + SEQUENCE + TEXT + COMMA + SPOUSE_NAME + TEXT + COMMA + CONTACT_NO + TEXT + COMMA + MOBILE + TEXT + COMMA + EMAIL + TEXT
                + COMMA + DESIGNATION + TEXT + COMMA + ADD1 + TEXT + COMMA + ADD2 + TEXT + COMMA + ADD3 + TEXT + COMMA + PIN
                + TEXT + COMMA + TOWN + TEXT + COMMA + PIC + TEXT + COMMA + UNIQUE + START_COLUMN + ID + FINISH_COLUMN
                + ON_CONFLICT_REPLACE + FINISH_COLUMN;*/

        Log.e("touristAttTableStmnt", "touristAttractionTableStatement: " + touristAttractionTableStatement);
        Log.e("pictureTableStatement", "pictureTableStatement: " + pictureTableStatement);

        create[0] = touristAttractionTableStatement;
        create[1] = pictureTableStatement;
        //create[2] = membersDirectoryTableStatement;
        //create[3] = membersDirectorySSHTableStatement;

        return create;
    }

    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {

        return mDb != null ? mDb : (mDb = super.getWritableDatabase());
    }

    @Override
    public synchronized SQLiteDatabase getReadableDatabase() {

        return mDb != null ? mDb : (mDb = super.getReadableDatabase());
    }

    public void startmanagingcursor() {
        mDatabase.startmanagingcursor();
    }

}