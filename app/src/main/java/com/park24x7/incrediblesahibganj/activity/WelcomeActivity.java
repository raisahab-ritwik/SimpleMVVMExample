package com.park24x7.incrediblesahibganj.activity;

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
import com.park24x7.incrediblesahibganj.model.UserClass;
import com.park24x7.incrediblesahibganj.network.PostWithJsonWebTask;
import com.park24x7.incrediblesahibganj.network.ServerResponseCallback;
import com.park24x7.incrediblesahibganj.network.ServerResponseStringCallback;
import com.park24x7.incrediblesahibganj.network.VolleyTaskManager;
import com.park24x7.incrediblesahibganj.util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class WelcomeActivity extends AppCompatActivity implements ServerResponseCallback {

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
            Intent intent = new Intent(mContext, LandingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            downloadResources();
        }

        //finish();
    }

    private void downloadResources() {

        //volleyTaskManager.doGet(WebService.BUS_STOP_URL);
        PostWithJsonWebTask.callPostWithStringReqWebtask(WelcomeActivity.this, WebService.TOURIST_ATTRACTION_URL, new HashMap<String, String>(), new ServerResponseStringCallback() {
            @Override
            public void onSuccess(String resultString) {
                Log.e("Response", "Response: " + resultString);
                try {
                    JSONArray jsonArray = new JSONArray(resultString.trim());
                    for (int i = 0; i < jsonArray.length(); i++) {


                    }
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
}
