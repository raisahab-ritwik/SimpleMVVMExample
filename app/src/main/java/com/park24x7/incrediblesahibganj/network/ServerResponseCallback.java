package com.park24x7.incrediblesahibganj.network;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface ServerResponseCallback {

    /**
     * Successful response callback
     */
    public void onSuccess(JSONObject resultJsonObject);

    /**
     * Callback on Failure
     */
    public void onError(VolleyError error);
}
