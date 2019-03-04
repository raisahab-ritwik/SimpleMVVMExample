package com.park24x7.incrediblesahibganj.hotelsandrestaurants;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.park24x7.incrediblesahibganj.R;

public class HotelsRestaurantActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = HotelsRestaurantActivity.this;
        setContentView(R.layout.acitivity_hotels_restaurant);
    }
}
