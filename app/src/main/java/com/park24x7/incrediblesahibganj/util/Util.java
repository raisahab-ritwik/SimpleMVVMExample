package com.park24x7.incrediblesahibganj.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.park24x7.incrediblesahibganj.model.UserClass;

import java.io.IOException;

public class Util {

    private static String USERCLASS = "USERCLASS";


    private static final String PREF_NAME = "IncredibleSahibganjPrefs";

    /**
     * To check Internet Connection
     */
    public static boolean isInternetAvailable(Context context) {

        ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conManager.getActiveNetworkInfo();
        if ((i == null) || (!i.isConnected()) || (!i.isAvailable())) {

            return false;
        }
        return true;
    }

    /**
     * Hide Soft Keyboard
     **/
    public static void hideSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    /**
     * Hide Soft Keyboard
     **/
    public static void showSoftKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        ;
    }

    /**
     * Saving UserClass details
     **/
    public static void saveUserClass(final Context mContext, UserClass userClass) {
        SharedPreferences LastMilePrefs = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = LastMilePrefs.edit();
        try {
            prefsEditor.putString(USERCLASS, ObjectSerializer.serialize(userClass));
        } catch (IOException e) {
            e.printStackTrace();
        }
        prefsEditor.commit();
    }

    /**
     * Fetching UserClass details
     **/
    public static UserClass fetchUserClass(final Context mContext) {
        SharedPreferences LastMilePrefs = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        UserClass userClass = null;
        String serializeOrg = LastMilePrefs.getString(USERCLASS, null);
        try {
            if (serializeOrg != null) {
                userClass = (UserClass) ObjectSerializer.deserialize(serializeOrg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return userClass;
    }

    /**
     * show global alert message
     *
     * @param context   application context
     * @param title     alert title
     * @param btn_title alert click button name
     * @param msg       alert message
     */
    public static void alertMessage(Context context, String title, String btn_title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg).setCancelable(false).setPositiveButton(btn_title, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Show log
     *
     * @param type       of log 0 for info,1 error,2 for verbosa
     * @param logtitle
     * @param logcontent
     * @return
     */
    public static void printLog(int type, String logtitle, String logcontent) {

        switch (type) {
            case 0:
                Log.e(logtitle, logcontent + "");
                break;
            case 1:
                Log.e(logtitle, logcontent + "");
                break;
            case 2:
                Log.e(logtitle, logcontent + "");
                break;

            default:
                Log.e(logtitle, logcontent + "");
                break;
        }
    }

}
