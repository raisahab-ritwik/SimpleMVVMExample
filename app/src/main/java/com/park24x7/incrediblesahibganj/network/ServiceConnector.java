package com.park24x7.incrediblesahibganj.network;

import com.park24x7.incrediblesahibganj.constant.WebService;

import org.json.JSONObject;

public class ServiceConnector {

    protected static String baseURL = WebService.BASE_URL;

    //protected static String versionCodeURL = baseURL + "dbversion/";

    protected JSONObject outputJson;

    public static String getBaseURL() {
        return baseURL;
    }

   /* public static String getVersionCodeURL() {
        return versionCodeURL;
    }*/

    public JSONObject getOutputJson() {
        return outputJson;
    }

    public void setOutputJson(JSONObject outputJson) {
        this.outputJson = outputJson;
    }
}
