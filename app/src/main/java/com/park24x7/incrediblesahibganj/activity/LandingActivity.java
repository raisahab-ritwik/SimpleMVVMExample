package com.park24x7.incrediblesahibganj.activity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;

import com.park24x7.incrediblesahibganj.R;
import com.park24x7.incrediblesahibganj.atm.ATMActivity;
import com.park24x7.incrediblesahibganj.constant.StaticConstants;
import com.park24x7.incrediblesahibganj.touristattraction.TouristAttactionListActivity;

public class LandingActivity extends AppCompatActivity {

    private Context mContext;

    private View rootLayout;
    private int revealX;
    private int revealY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_new);
        rootLayout = findViewById(R.id.rl_landing);
        mContext = LandingActivity.this;
       /* final Intent intent = getIntent();
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
            circularReveal.setDuration(600);
            circularReveal.setInterpolator(new AccelerateInterpolator());

            // make the view visible and start the animation
            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
        } else {
            finish();
        }
    }

    public void onTouristAttractionClick(View view) {
        /*ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "transition");
        int revealX = (int) (view.getX() + view.getWidth() / 2);
        int revealY = (int) (view.getY() + view.getHeight() / 2);

        Intent intent = new Intent(this, TouristAttactionListActivity.class);
        intent.putExtra(StaticConstants.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(StaticConstants.EXTRA_CIRCULAR_REVEAL_Y, revealY);

        ActivityCompat.startActivity(this, intent, options.toBundle());*/

        Intent intent = new Intent(mContext, TouristAttactionListActivity.class);
        startActivity(intent);
    }

    public void onATMClick(View view) {

        showPopUp();
    }

    public void onHotelAndRestClick(View view) {
        showPopUp();
    }

    public void onRailwayStnClick(View view) {
        showPopUp();
    }

    public void onBusStopClick(View view) {
        showPopUp();
    }

    public void onCinemaHallClick(View view) {
        showPopUp();
    }

    public void onBankClick(View view) {
        showPopUp();
    }

    public void onHospitalClick(View view) {
        showPopUp();
    }

    private void showPopUp() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LandingActivity.this);
        // ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_batch_details, null);
        dialogBuilder.setView(dialogView);

        ImageButton ib_close = (ImageButton) dialogView.findViewById(R.id.ib_close);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        ib_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }
}


