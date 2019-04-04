package com.park24x7.incrediblesahibganj.touristattraction;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.park24x7.incrediblesahibganj.R;
import com.park24x7.incrediblesahibganj.activity.LandingActivity;
import com.park24x7.incrediblesahibganj.adapter.GalleryAdapter;
import com.park24x7.incrediblesahibganj.db.TouristImageDB;
import com.park24x7.incrediblesahibganj.model.ImageClass;
import com.park24x7.incrediblesahibganj.model.TouristAttraction;
import com.park24x7.incrediblesahibganj.util.GPSTracker;
import com.park24x7.incrediblesahibganj.util.Util;

import java.util.ArrayList;
import java.util.Locale;

public class TouristAttractionActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Context mContext;
    private TextView tv_description;
    private TouristAttraction touristAttraction;
    private TextView tv_tourist_attraction_name;
    private ImageView iv_featureImage;
    private ArrayList<ImageClass> images = new ArrayList<>();
    private LinearLayout ll_history, ll_map, ll_gallery;
    private GalleryAdapter mAdapter;
    private RecyclerView recyclerView;
    private GoogleMap mMap;
    private GPSTracker gpsTracker;
    private double latitude = 0.0, longitude = 0.0;
    private boolean isMapReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tourist_attraction);
        //revealRed();
        tv_description = (TextView) findViewById(R.id.tv_description);

        tv_tourist_attraction_name = (TextView) findViewById(R.id.tv_tourist_attraction_name);
        iv_featureImage = (ImageView) findViewById(R.id.iv_featureImage);
        ll_history = (LinearLayout) findViewById(R.id.ll_history);
        ll_map = (LinearLayout) findViewById(R.id.ll_map);
        ll_gallery = (LinearLayout) findViewById(R.id.ll_gallery);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        images = new ArrayList<>();
        mContext = TouristAttractionActivity.this;
        touristAttraction = (TouristAttraction) getIntent().getSerializableExtra("touristAttraction");
        if (touristAttraction != null) {
            tv_description.setText(touristAttraction.description);
            tv_tourist_attraction_name.setText(touristAttraction.name);
        } else {
            Toast.makeText(mContext, "Data could not be loaded. Please try again!", Toast.LENGTH_SHORT).show();
        }
        loadFeaturedImage();
        //getGps();
        /*MapFragment mapFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.mapView);
        mapFrag.getMapAsync(this);*/
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }

    private void loadFeaturedImage() {

        images = new TouristImageDB().getImages(mContext, touristAttraction.id.trim());
        if (images.size() > 0) {
            iv_featureImage.setImageBitmap(Util.getBitmapBase64FromString(images.get(0).getBase64value()));
            mAdapter = new GalleryAdapter(getApplicationContext(), images);

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);

            recyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new GalleryAdapter.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("images", images);
                    bundle.putInt("position", position);

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                    newFragment.setArguments(bundle);
                    newFragment.show(ft, "slideshow");
                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));
        }
    }

    public void onHistoryClick(View view) {
        ll_gallery.setVisibility(View.GONE);
        ll_map.setVisibility(View.GONE);
        ll_history.setVisibility(View.VISIBLE);
    }

    public void onGalleryClick(View view) {
        if (touristAttraction != null && images.size() > 0) {

            /*Intent intent = new Intent(mContext, GalleryActivity.class);
            intent.putExtra("touristAttraction", touristAttraction);
            startActivity(intent);*/

            ll_gallery.setVisibility(View.VISIBLE);
            ll_map.setVisibility(View.GONE);
            ll_history.setVisibility(View.GONE);
        }
        //Toast.makeText(mContext, "show Gallery", Toast.LENGTH_SHORT).show();
    }

    public void onDirectionsClick(View view) {
        if (touristAttraction != null) {
            ll_history.setVisibility(View.GONE);
            ll_gallery.setVisibility(View.GONE);
            ll_map.setVisibility(View.VISIBLE);
            /*LatLng TutorialsPoint = new LatLng(21, 57);
            mMap.addMarker(new MarkerOptions().position(TutorialsPoint).title("Tutorialspoint.com"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint));*/

            /*Uri gmmIntentUri = Uri.parse("google.navigation:q=28.5675,77.3260");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);*/

          /*  Uri uri = Uri.parse("https://maps.google.com/?q=pizza+hut&ll=-" + touristAttraction.latitude + "," + touristAttraction.longitude + "&z=12");
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            startActivity(intent);*/

            /*try {

                String uri = "http://maps.google.com/maps?q=" + touristAttraction.latitude + "," + touristAttraction.longitude;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            } catch (Exception e) {
                Log.e("Lat", "Latitude: " + touristAttraction.latitude);
                e.printStackTrace();
            }*/

            try {
                if (isMapReady) {
                    LatLng touristPlaceLocation = new LatLng(Double.parseDouble(touristAttraction.latitude.trim()), Double.parseDouble(touristAttraction.longitude.trim()));
                    mMap.addMarker(new MarkerOptions().position(touristPlaceLocation).title("" + touristAttraction.name));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(touristPlaceLocation, 15));
                }

            } catch (Exception e) {
                Log.e("Lat", "Latitude: " + touristAttraction.latitude);
                e.printStackTrace();
            }
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

    private void revealRed() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                /*animateRevealColor(bgViewGroup, R.color.sample_red);

                body.setText(R.string.reveal_body3);
                body.setTextColor(ContextCompat.getColor(RevealActivity.this, R.color.theme_red_background));
                btnRed.setLayoutParams(originalParams);*/
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        /*TransitionManager.beginDelayedTransition(bgViewGroup, transition);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        btnRed.setLayoutParams(layoutParams);*/
    }

    private void getGps() {

        if (Util.isInternetAvailable(mContext)) {

            ProgressDialog mDialog = ProgressDialog.show(mContext, "", "Loading. Please wait...", true);

            gpsTracker = new GPSTracker(mContext);

            if (gpsTracker.canGetLocation()) {

                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();
                double data = 0.0;

                Log.e("latitude before ", "" + latitude);
                Log.e("Longitute before", "" + longitude);

                if ((data == latitude) && (data == longitude)) {
                    mDialog.dismiss();
                    //getGps();
                    return;
                }

                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                mDialog.dismiss();

            } else {
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                gpsTracker.showSettingsAlert();
            }

        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        isMapReady = true;

    }
}
