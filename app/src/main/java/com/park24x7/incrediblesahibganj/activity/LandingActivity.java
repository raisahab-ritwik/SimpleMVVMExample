package com.park24x7.incrediblesahibganj.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.park24x7.incrediblesahibganj.R;
import com.park24x7.incrediblesahibganj.atm.ATMActivity;
import com.park24x7.incrediblesahibganj.touristattraction.TouristAttactionListActivity;

public class LandingActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        mContext = LandingActivity.this;
    }

    public void onTouristAttractionClick(View view) {
        startActivity(new Intent(mContext, TouristAttactionListActivity.class));
    }

    public void onATMClick(View view) {

        startActivity(new Intent(mContext, ATMActivity.class));


    }

    public void onHotelAndRestClick(View view) {

    }

    public void onRailwayStnClick(View view) {

    }

    public void onBusStopClick(View view) {

    }

    public void onCinemaHallClick(View view) {

    }

    public void onBankClick(View view) {

    }
}


