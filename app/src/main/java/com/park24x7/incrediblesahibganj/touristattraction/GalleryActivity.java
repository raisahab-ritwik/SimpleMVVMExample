package com.park24x7.incrediblesahibganj.touristattraction;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.park24x7.incrediblesahibganj.R;
import com.park24x7.incrediblesahibganj.adapter.GalleryAdapter;
import com.park24x7.incrediblesahibganj.db.TouristImageDB;
import com.park24x7.incrediblesahibganj.model.Image;
import com.park24x7.incrediblesahibganj.model.ImageClass;
import com.park24x7.incrediblesahibganj.model.TouristAttraction;
import com.park24x7.incrediblesahibganj.network.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    private String TAG = TouristAttactionListActivity.class.getSimpleName();
    //private static final String endpoint = "https://api.androidhive.info/json/glide.json";
    private Context mContext;
    private ArrayList<ImageClass> images;
    private GalleryAdapter mAdapter;
    private RecyclerView recyclerView;
    private TouristAttraction touristAttraction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        mContext = GalleryActivity.this;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        images = new ArrayList<>();
        touristAttraction = (TouristAttraction) getIntent().getSerializableExtra("touristAttraction");
        //Toast.makeText(mContext, "Tour: " + touristAttraction.id, Toast.LENGTH_SHORT).show();
        fetchImages();
    }

    private void fetchImages() {
        //Toast.makeText(mContext, "FETCH IMAGES", Toast.LENGTH_SHORT).show();
        if (touristAttraction != null) {
            images.clear();
            //Toast.makeText(mContext, "Not NULL", Toast.LENGTH_SHORT).show();
            images = new TouristImageDB().getImages(GalleryActivity.this, touristAttraction.id.trim());
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

    public void onBackBtnClick(View view) {
        onBackPressed();
    }
}