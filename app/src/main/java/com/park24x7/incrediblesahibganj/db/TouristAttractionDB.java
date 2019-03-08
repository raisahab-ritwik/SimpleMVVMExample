package com.park24x7.incrediblesahibganj.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.park24x7.incrediblesahibganj.model.ImageClass;
import com.park24x7.incrediblesahibganj.model.TouristAttraction;

import java.util.ArrayList;

public class TouristAttractionDB implements DBConstants {

    private static TouristAttractionDB obj = null;


    public synchronized static TouristAttractionDB obj() {

        if (obj == null)
            obj = new TouristAttractionDB();
        return obj;

    }

    public Boolean saveTouristActractionData(Context context, ContentValues cv) {

        System.out.println(" ----------  ADD ROWS INTO TouristActraction TABLE --------- ");
        SQLiteDatabase mdb = SahibgunjDBHelper.getInstance(context).getWritableDatabase();
        mdb.beginTransaction();
        try {
            mdb.insert(TOURIST_ATTRACTION_TABLE, null, cv);
            mdb.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            mdb.endTransaction();
            return true;
        }

    }

    public Boolean savePictureData(Context context, ContentValues cv) {

        System.out.println(" ----------  ADD ROWS INTO PICTURE TABLE --------- ");
        SQLiteDatabase mdb = SahibgunjDBHelper.getInstance(context).getWritableDatabase();
        mdb.beginTransaction();
        try {
            mdb.insert(PICTURE_TABLE, null, cv);
            mdb.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            mdb.endTransaction();
            return true;
        }

    }

    public ArrayList<TouristAttraction> getTouristAttraction(Context context) {

        ArrayList<TouristAttraction> touristAttractionArray = new ArrayList<TouristAttraction>();

        SQLiteDatabase mdb = SahibgunjDBHelper.getInstance(context).getReadableDatabase();

        Cursor cur = mdb.query(TOURIST_ATTRACTION_TABLE, null, null, null, null, null, null);

        if (!isDatabaseEmpty(cur)) {
            try {
                if (cur.moveToFirst()) {
                    do {
                        TouristAttraction touristAttraction = new TouristAttraction();
                        touristAttraction.id = cur.getString(cur.getColumnIndex(DBConstants.TOURIST_ATTRACTION_ID));
                        touristAttraction.name = cur.getString(cur.getColumnIndex(DBConstants.TOURIST_ATTRACTION_NAME));
                        touristAttraction.description = cur.getString(cur.getColumnIndex(DBConstants.TOURIST_ATTRACTION_DESCRIPTION));
                        touristAttraction.latitude = cur.getString(cur.getColumnIndex(DBConstants.TOURIST_ATTRACTION_LATITUDE));
                        touristAttraction.longitude = cur.getString(cur.getColumnIndex(DBConstants.TOURIST_ATTRACTION_LONGITUDE));
                        touristAttraction.images = getImages(context, cur.getString(cur.getColumnIndex(DBConstants.TOURIST_ATTRACTION_ID)));
                        touristAttractionArray.add(touristAttraction);
                    } while (cur.moveToNext());
                }
                cur.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return touristAttractionArray;
    }

    public ArrayList<ImageClass> getImages(Context context, String touristAtractionID) {

        ArrayList<ImageClass> imageArray = new ArrayList<ImageClass>();
        String[] columns = {_ID, PICTURE_ID, PICTURE, TOURIST_ATTRACTION_ID};

        SQLiteDatabase mdb = SahibgunjDBHelper.getInstance(context).getReadableDatabase();
        Cursor cur = mdb.query(PICTURE_TABLE, columns, TOURIST_ATTRACTION_ID + "=?", new String[]{touristAtractionID}, null, null, null);
        //Cursor cur = mdb.query(TOURIST_ATTRACTION_TABLE, null, null, null, null, null, null);

        if (!isDatabaseEmpty(cur)) {
            try {
                if (cur.moveToFirst()) {
                    do {
                        ImageClass image = new ImageClass();
                        image.setBase64value(cur.getString(cur.getColumnIndex(DBConstants.PICTURE)));
                        imageArray.add(image);
                    } while (cur.moveToNext());
                }
                cur.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return imageArray;
    }

    private Boolean isDatabaseEmpty(Cursor mCursor) {

        if (mCursor.moveToFirst()) {
            // NOT EMPTY
            return false;

        } else {
            // IS EMPTY
            return true;
        }

    }

	/*public void clearDBTables(Context mcContext) {
		System.out.println(" ----------  CLEAR BLOCK TABLES  --------- ");
		SQLiteDatabase mdb = DisaterManagementDatabase.getInstance(mcContext).getWritableDatabase();
		mdb.beginTransaction();
		try {
			mdb.delete(BLOCK_TABLE, null, null);
			mdb.delete(MPCS_PROJECT_TABLE, null, null);
			mdb.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mdb.endTransaction();
		}
	}*/
}