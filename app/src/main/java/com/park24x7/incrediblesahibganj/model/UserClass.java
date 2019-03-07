package com.park24x7.incrediblesahibganj.model;

import java.io.Serializable;

public class UserClass implements Serializable {

    private boolean isFirstTime = true;

    public boolean isFirstTime() {
        return isFirstTime;
    }

    public void setFirstTime(boolean firstTime) {
        isFirstTime = firstTime;
    }
}
