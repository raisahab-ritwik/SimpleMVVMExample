package com.park24x7.incrediblesahibganj.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.park24x7.incrediblesahibganj.R;

public class SplashActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = SplashActivity.this;
        new SplashTimerTask().execute();
    }

    /**
     * The Splash Timer. duration ---> 2345ms
     *
     * @params {@link AsyncTask}
     */
    private class SplashTimerTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(2345);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent intent = new Intent(mContext, LandingActivity.class);
            startActivity(intent);

        }

    }

}
