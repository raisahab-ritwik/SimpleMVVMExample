package com.park24x7.incrediblesahibganj.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.park24x7.incrediblesahibganj.R;
import com.park24x7.incrediblesahibganj.constant.WebService;
import com.park24x7.incrediblesahibganj.db.DBConstants;
import com.park24x7.incrediblesahibganj.db.TouristAttractionDB;
import com.park24x7.incrediblesahibganj.model.TouristAttraction;
import com.park24x7.incrediblesahibganj.model.UserClass;
import com.park24x7.incrediblesahibganj.network.PostWithJsonWebTask;
import com.park24x7.incrediblesahibganj.network.ServerResponseCallback;
import com.park24x7.incrediblesahibganj.network.ServerResponseStringCallback;
import com.park24x7.incrediblesahibganj.network.VolleyTaskManager;
import com.park24x7.incrediblesahibganj.util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class WelcomeActivity extends AppCompatActivity implements ServerResponseCallback, DBConstants {

    private Context mContext;
    //private VolleyTaskManager volleyTaskManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mContext = WelcomeActivity.this;
        //volleyTaskManager = new VolleyTaskManager(mContext);
    }

    public void onEnterClick(View view) {

        UserClass userClass = Util.fetchUserClass(mContext);
        if (userClass != null && !userClass.isFirstTime()) {
            gotoLandingScreen();
        } else {
            downloadResources();
        }

        //finish();
    }

    private void downloadResources() {

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
                    UserClass userClass = Util.fetchUserClass(mContext);
                    if (userClass == null) {
                        userClass = new UserClass();
                    }
                    userClass.setFirstTime(false);
                    Util.saveUserClass(mContext, userClass);
                    gotoLandingScreen();

                } catch (Exception e) {
                    Log.e("Exception", "Exception: " + e.getLocalizedMessage(), e);

                }
            }

            @Override
            public void ErrorMsg(VolleyError error) {
            }
        }, true, Request.Method.GET);
    }

    @Override
    public void onSuccess(JSONObject resultJsonObject) {
        Log.e("Response", "Response: " + resultJsonObject);
    }

    @Override
    public void onError(VolleyError error) {

    }

    private void gotoLandingScreen() {
        Intent intent = new Intent(mContext, LandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
