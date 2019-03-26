package com.park24x7.incrediblesahibganj.activity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.park24x7.incrediblesahibganj.R;
import com.park24x7.incrediblesahibganj.constant.StaticConstants;
import com.park24x7.incrediblesahibganj.constant.WebService;
import com.park24x7.incrediblesahibganj.db.DBConstants;
import com.park24x7.incrediblesahibganj.db.TouristAttractionDB;
import com.park24x7.incrediblesahibganj.model.UserClass;
import com.park24x7.incrediblesahibganj.network.PostWithJsonWebTask;
import com.park24x7.incrediblesahibganj.network.ServerResponseCallback;
import com.park24x7.incrediblesahibganj.network.ServerResponseStringCallback;
import com.park24x7.incrediblesahibganj.util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class WelcomeActivity extends AppCompatActivity implements ServerResponseCallback, DBConstants {

    private Context mContext;
    //private VolleyTaskManager volleyTaskManager;
    private ProgressDialog pDialog;
    private RelativeLayout rl_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_new);
        mContext = WelcomeActivity.this;
        //volleyTaskManager = new VolleyTaskManager(mContext);
        rl_welcome = findViewById(R.id.rl_welcome);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Downloading Resources! Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setCancelable(false);
    }

    public void onEnterClick(View view) {

        UserClass userClass = Util.fetchUserClass(mContext);
        if (userClass != null && !userClass.isFirstTime()) {
            gotoLandingScreen(view);
        } else {
            downloadResources(view);
        }

    }

    private void downloadResources(final View view) {

        PostWithJsonWebTask.callPostWithStringReqWebtask(WelcomeActivity.this, WebService.TOURIST_ATTRACTION_URL, new HashMap<String, String>(), new ServerResponseStringCallback() {
            @Override
            public void onSuccess(String resultString) {
                Log.e("Response", "Response: " + resultString);
                try {
                    JSONArray jsonArray = new JSONArray(resultString.trim());
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        ContentValues cv = new ContentValues();
                        cv.put(TOURIST_ATTRACTION_ID, jsonObject.optString("touristattractionId"));
                        cv.put(TOURIST_ATTRACTION_NAME, jsonObject.optString("name"));
                        cv.put(TOURIST_ATTRACTION_DESCRIPTION, jsonObject.optString("description"));
                        cv.put(TOURIST_ATTRACTION_LATITUDE, jsonObject.optString("latitude"));
                        cv.put(TOURIST_ATTRACTION_LONGITUDE, jsonObject.optString("longitude"));
                        new TouristAttractionDB().saveTouristActractionData(mContext, cv);

                    }
                    /*UserClass userClass = Util.fetchUserClass(mContext);
                    if (userClass == null) {
                        userClass = new UserClass();
                    }
                    userClass.setFirstTime(false);
                    Util.saveUserClass(mContext, userClass);
                    gotoLandingScreen();*/


                    downloadImages(view);

                } catch (Exception e) {
                    Log.e("Exception", "Exception: " + e.getLocalizedMessage(), e);
                }
            }

            @Override
            public void ErrorMsg(VolleyError error) {
            }
        }, true, Request.Method.GET);
    }

    private static int currentIndex = 0;

    private void downloadImages(final View view) {
        pDialog.show();
        PostWithJsonWebTask.callPostWithStringReqWebtask(WelcomeActivity.this, WebService.TOURIST_IMAGE_URL, new HashMap<String, String>(), new ServerResponseStringCallback() {
            @Override
            public void onSuccess(String resultString) {
                Log.e("Response", "Response: " + resultString);
                try {
                    JSONObject jsonObject = new JSONObject(resultString);
                    UserClass userClass = Util.fetchUserClass(mContext);
                    if (userClass == null) {
                        userClass = new UserClass();
                    }
                    userClass.setTotal_pages(Integer.parseInt(jsonObject.optString("totalPage").trim()));
                   /* JSONArray jsonArray = new JSONArray(resultString.trim());
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        ContentValues cv = new ContentValues();
                        cv.put(TOURIST_ATTRACTION_ID, jsonObject.optString("touristattractionId"));
                        cv.put(TOURIST_ATTRACTION_NAME, jsonObject.optString("name"));
                        cv.put(TOURIST_ATTRACTION_DESCRIPTION, jsonObject.optString("description"));
                        cv.put(TOURIST_ATTRACTION_LATITUDE, jsonObject.optString("latitude"));
                        cv.put(TOURIST_ATTRACTION_LONGITUDE, jsonObject.optString("longitude"));
                        new TouristAttractionDB().saveTouristActractionData(mContext, cv);

                    }

                    //userClass.setFirstTime(false);

                    gotoLandingScreen();*/


                    // downloadImages();


                    JSONArray jsonArray = jsonObject.optJSONArray("image");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonImageObject = jsonArray.optJSONObject(i);
                        ContentValues cv = new ContentValues();
                        cv.put(PICTURE_ID, jsonImageObject.optString("touristimageId"));
                        cv.put(PICTURE, jsonImageObject.optString("image"));
                        cv.put(TOURIST_ATTRACTION_ID, jsonImageObject.optString("touristattractionId"));
                        cv.put(IS_FEATURED, jsonImageObject.optString("featuredImage"));
                        new TouristAttractionDB().savePictureData(mContext, cv);
                    }
                    currentIndex = 1;
                    userClass.setCurrent_image_page_index(1);
                    Util.saveUserClass(mContext, userClass);

                    double ratio = ((double) currentIndex / (double) userClass.getTotal_pages()) * 100;
                    Log.e("Ratio", "Ratio: " + ratio);

                    int progress = (int) ratio;

                    Log.e("PROGRESS", "Progress: " + progress);
                    pDialog.setProgress(progress);

                    downloadRemainingImages("?&wtpage=" + (currentIndex + 1), view);


                } catch (Exception e) {
                    Log.e("Exception", "Exception: " + e.getLocalizedMessage(), e);
                }
            }

            @Override
            public void ErrorMsg(VolleyError error) {
            }
        }, false, Request.Method.GET);
    }

    private void downloadRemainingImages(String url, final View view) {


        if (currentIndex < Util.fetchUserClass(mContext).getTotal_pages()) {
            PostWithJsonWebTask.callPostWithStringReqWebtask(WelcomeActivity.this, WebService.TOURIST_IMAGE_URL + url, new HashMap<String, String>(), new ServerResponseStringCallback() {
                @Override
                public void onSuccess(String resultString) {
                    Log.e("Response", "Response: " + resultString);
                    try {
                        UserClass userClass = Util.fetchUserClass(mContext);
                        if (userClass == null) {
                            userClass = new UserClass();
                        }
                        JSONObject jsonObject = new JSONObject(resultString);

                        JSONArray jsonArray = jsonObject.optJSONArray("image");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonImageObject = jsonArray.optJSONObject(i);
                            ContentValues cv = new ContentValues();
                            cv.put(PICTURE_ID, jsonImageObject.optString("touristimageId"));
                            cv.put(PICTURE, jsonImageObject.optString("image"));
                            cv.put(TOURIST_ATTRACTION_ID, jsonImageObject.optString("touristattractionId"));
                            cv.put(IS_FEATURED, jsonImageObject.optString("featuredImage"));
                            new TouristAttractionDB().savePictureData(mContext, cv);
                        }
                        currentIndex = Integer.parseInt(jsonObject.optString("currentPage").trim());
                        userClass.setCurrent_image_page_index(currentIndex);
                        Util.saveUserClass(mContext, userClass);
                        double ratio = ((double) currentIndex / (double) userClass.getTotal_pages()) * 100;
                        Log.e("Ratio", "Ratio: " + ratio);

                        int progress = (int) ratio;

                        Log.e("PROGRESS", "Progress: " + progress);
                        pDialog.setProgress(progress);

                        downloadRemainingImages("?&wtpage=" + (currentIndex + 1), view);
                   /* JSONArray jsonArray = new JSONArray(resultString.trim());
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        ContentValues cv = new ContentValues();
                        cv.put(TOURIST_ATTRACTION_ID, jsonObject.optString("touristattractionId"));
                        cv.put(TOURIST_ATTRACTION_NAME, jsonObject.optString("name"));
                        cv.put(TOURIST_ATTRACTION_DESCRIPTION, jsonObject.optString("description"));
                        cv.put(TOURIST_ATTRACTION_LATITUDE, jsonObject.optString("latitude"));
                        cv.put(TOURIST_ATTRACTION_LONGITUDE, jsonObject.optString("longitude"));
                        new TouristAttractionDB().saveTouristActractionData(mContext, cv);

                    }
                    UserClass userClass = Util.fetchUserClass(mContext);
                    if (userClass == null) {
                        userClass = new UserClass();
                    }
                    userClass.setFirstTime(false);
                    Util.saveUserClass(mContext, userClass);
                    gotoLandingScreen();*/


                        // downloadImages();

                    } catch (Exception e) {
                        Log.e("Exception", "Exception: " + e.getLocalizedMessage(), e);
                    }
                }

                @Override
                public void ErrorMsg(VolleyError error) {
                }
            }, false, Request.Method.GET);
        } else {
            pDialog.dismiss();
            UserClass userClass = Util.fetchUserClass(mContext);
            userClass.setFirstTime(false);
            Util.saveUserClass(mContext, userClass);
            gotoLandingScreen(view);
        }
    }

    @Override
    public void onSuccess(JSONObject resultJsonObject) {
        Log.e("Response", "Response: " + resultJsonObject);
    }

    @Override
    public void onError(VolleyError error) {

    }

    private void gotoLandingScreen(View view) {

        /*Intent intent = new Intent(mContext, LandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);*/
        presentActivity(view);


    }

    public void presentActivity(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, view, "transition");
        int revealX = (int) (view.getX() + view.getWidth() / 2);
        int revealY = (int) (view.getY() + view.getHeight() / 2);

        Intent intent = new Intent(this, LandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(StaticConstants.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(StaticConstants.EXTRA_CIRCULAR_REVEAL_Y, revealY);

        ActivityCompat.startActivity(this, intent, options.toBundle());
    }


    @Override
    public void onBackPressed() {

    }
}
