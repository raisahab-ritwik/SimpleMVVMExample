package com.park24x7.incrediblesahibganj.railwaystation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.park24x7.incrediblesahibganj.R;

public class RailwayStationActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_railway_station);
        mContext = RailwayStationActivity.this;
    }
}
