package com.park24x7.incrediblesahibganj.touristattraction;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.park24x7.incrediblesahibganj.R;
import com.park24x7.incrediblesahibganj.activity.LandingActivity;
import com.park24x7.incrediblesahibganj.adapter.TouristAttractionAdapter;
import com.park24x7.incrediblesahibganj.constant.StaticConstants;
import com.park24x7.incrediblesahibganj.data.TouristAttactionData;
import com.park24x7.incrediblesahibganj.model.TouristAttraction;

import java.util.ArrayList;

public class TouristAttactionListActivity extends AppCompatActivity {

    private Context mContext;
    private GridView lv_tourist_attraction;
    private TouristAttractionAdapter adapter;
    private View rootLayout;
    private int revealX;
    private int revealY;
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_attraction_list);
       /* getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");*/
        mContext = TouristAttactionListActivity.this;
        rootLayout = findViewById(R.id.ll_tourist_attraction_list);
        lv_tourist_attraction = (GridView) findViewById(R.id.lv_tourist_attraction);
        final ArrayList<TouristAttraction> touristAttractions = TouristAttactionData.getTouristAttractions(mContext);
        adapter = new TouristAttractionAdapter(mContext, touristAttractions);
        lv_tourist_attraction.setAdapter(adapter);
        lv_tourist_attraction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TouristAttraction touristAttraction = new TouristAttraction();
                touristAttraction.name = touristAttractions.get(i).name;
                touristAttraction.description = touristAttractions.get(i).description;
                touristAttraction.id = touristAttractions.get(i).id;
                touristAttraction.latitude = touristAttractions.get(i).latitude;
                touristAttraction.longitude = touristAttractions.get(i).longitude;
                Intent intent = new Intent(mContext, TouristAttractionActivity.class);
                intent.putExtra("touristAttraction", touristAttraction);
                startActivity(intent);
            }
        });
        /*final Intent intent = getIntent();
        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                intent.hasExtra(StaticConstants.EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(StaticConstants.EXTRA_CIRCULAR_REVEAL_Y)) {
            rootLayout.setVisibility(View.INVISIBLE);

            revealX = intent.getIntExtra(StaticConstants.EXTRA_CIRCULAR_REVEAL_X, 0);
            revealY = intent.getIntExtra(StaticConstants.EXTRA_CIRCULAR_REVEAL_Y, 0);


            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        revealActivity(revealX, revealY);
                        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        } else {
            rootLayout.setVisibility(View.VISIBLE);
        }*/

    }

    protected void revealActivity(int x, int y) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);

            // create the animator for this view (the start radius is zero)
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
            circularReveal.setDuration(350);
            circularReveal.setInterpolator(new AccelerateInterpolator());

            // make the view visible and start the animation
            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
        } else {
            finish();
        }
    }

    public void onBackBtnClick(View view) {
        onBackPressed();
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(mContext, LandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
