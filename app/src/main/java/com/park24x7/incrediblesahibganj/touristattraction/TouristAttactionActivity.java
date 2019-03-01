package com.park24x7.incrediblesahibganj.touristattraction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.park24x7.incrediblesahibganj.R;

public class TouristAttactionActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_attraction);
        mContext = TouristAttactionActivity.this;
    }

    public void onGalleryClick(View view) {
        startActivity(new Intent(TouristAttactionActivity.this, GalleryActivity.class));
    }
}
