package com.park24x7.incrediblesahibganj.touristattraction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.park24x7.incrediblesahibganj.R;
import com.park24x7.incrediblesahibganj.model.TouristAttraction;

public class TouristAttractionActivity extends AppCompatActivity {

    private Context mContext;
    private TextView tv_description;
    private TouristAttraction touristAttraction;

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
        tv_description = (TextView) findViewById(R.id.tv_description);
        mContext = TouristAttractionActivity.this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");
        touristAttraction = (TouristAttraction) getIntent().getSerializableExtra("touristAttraction");
        if (touristAttraction != null) {
            tv_description.setText(touristAttraction.description);
        } else {
            Toast.makeText(mContext, "Data could not be loaded. Please try again!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onGalleryClick(View view) {
        startActivity(new Intent(mContext, GalleryActivity.class));
        //Toast.makeText(mContext, "show Gallery", Toast.LENGTH_SHORT).show();
    }
}
