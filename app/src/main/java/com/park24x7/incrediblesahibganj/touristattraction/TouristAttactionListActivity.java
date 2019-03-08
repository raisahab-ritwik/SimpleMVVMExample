package com.park24x7.incrediblesahibganj.touristattraction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.park24x7.incrediblesahibganj.R;
import com.park24x7.incrediblesahibganj.adapter.TouristAttractionAdapter;
import com.park24x7.incrediblesahibganj.data.TouristAttactionData;
import com.park24x7.incrediblesahibganj.model.TouristAttraction;

import java.util.ArrayList;

public class TouristAttactionListActivity extends AppCompatActivity {

    private Context mContext;
    private ListView lv_tourist_attraction;
    private TouristAttractionAdapter adapter;

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
        setContentView(R.layout.activity_tourist_attraction_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");
        mContext = TouristAttactionListActivity.this;
        lv_tourist_attraction = (ListView) findViewById(R.id.lv_tourist_attraction);
        final ArrayList<TouristAttraction> touristAttractions = TouristAttactionData.getTouristAttractions(mContext);
        adapter = new TouristAttractionAdapter(mContext, touristAttractions);
        lv_tourist_attraction.setAdapter(adapter);
        lv_tourist_attraction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, TouristAttractionActivity.class);
                intent.putExtra("touristAttraction", touristAttractions.get(i));
                startActivity(intent);
            }
        });
    }
}
