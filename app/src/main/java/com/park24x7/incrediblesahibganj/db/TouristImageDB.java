package com.park24x7.incrediblesahibganj.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.park24x7.incrediblesahibganj.model.ImageClass;
import com.park24x7.incrediblesahibganj.model.TouristAttraction;

import java.util.ArrayList;

public class TouristImageDB implements DBConstants {

    private static TouristImageDB obj = null;


    public synchronized static TouristImageDB obj() {

        if (obj == null)
            obj = new TouristImageDB();
        return obj;

    }

    public ArrayList<ImageClass> getImages(Context context, String touristAtractionID) {

        ArrayList<ImageClass> imageArray = new ArrayList<ImageClass>();
        String[] columns = {_ID, PICTURE_ID, PICTURE, TOURIST_ATTRACTION_ID, IS_FEATURED};

        SQLiteDatabase mdb = SahibgunjDBHelper.getInstance(context).getReadableDatabase();
        Cursor cur = mdb.query(PICTURE_TABLE, columns, TOURIST_ATTRACTION_ID + "=?", new String[]{touristAtractionID}, null, null, null);
        //Cursor cur = mdb.query(TOURIST_ATTRACTION_TABLE, null, null, null, null, null, null);

        if (!isDatabaseEmpty(cur)) {
            try {
                if (cur.moveToFirst()) {
                    do {
                        ImageClass image = new ImageClass();
                        image.setBase64value(cur.getString(cur.getColumnIndex(DBConstants.PICTURE)));
                        image.setIsFeaturedImage(cur.getString(cur.getColumnIndex(DBConstants.IS_FEATURED)));
                        image.setTouristAttractionID(cur.getString(cur.getColumnIndex(DBConstants.TOURIST_ATTRACTION_ID)));
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