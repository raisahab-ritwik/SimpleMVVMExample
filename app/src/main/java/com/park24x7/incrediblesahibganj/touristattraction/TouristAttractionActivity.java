package com.park24x7.incrediblesahibganj.touristattraction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.park24x7.incrediblesahibganj.R;

public class TouristAttractionActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tourist_attraction);
        mContext = TouristAttractionActivity.this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void onGalleryClick(View view) {
        startActivity(new Intent(mContext, GalleryActivity.class));
    }
}
