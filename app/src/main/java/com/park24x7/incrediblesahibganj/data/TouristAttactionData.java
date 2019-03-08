package com.park24x7.incrediblesahibganj.data;

import android.content.Context;

import com.park24x7.incrediblesahibganj.db.TouristAttractionDB;
import com.park24x7.incrediblesahibganj.model.TouristAttraction;

import java.util.ArrayList;

public class TouristAttactionData {

    public static ArrayList<TouristAttraction> getTouristAttractions(Context mContext) {

        return new TouristAttractionDB().getTouristAttraction(mContext);
    }
}
