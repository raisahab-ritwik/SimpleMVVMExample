package com.park24x7.incrediblesahibganj.cinemahall;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.park24x7.incrediblesahibganj.R;

public class CinemaHallActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_hall);
        mContext = CinemaHallActivity.this;
    }
}
