package com.park24x7.incrediblesahibganj.network;

import com.android.volley.VolleyError;

public interface ServerResponseStringCallback {
    public void onSuccess(String resultString);

    /**
     * If there occurs any error while communicating with server
     *
     * @param error
     */
    public void ErrorMsg(VolleyError error);
}
