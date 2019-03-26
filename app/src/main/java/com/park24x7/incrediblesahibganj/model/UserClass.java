package com.park24x7.incrediblesahibganj.model;

import java.io.Serializable;

public class UserClass implements Serializable {

    private boolean isFirstTime = true;
    private int total_pages = 0;
    private int current_image_page_index = 0;

    public boolean isFirstTime() {
        return isFirstTime;
    }

    public void setFirstTime(boolean firstTime) {
        isFirstTime = firstTime;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getCurrent_image_page_index() {
        return current_image_page_index;
    }

    public void setCurrent_image_page_index(int current_image_page_index) {
        this.current_image_page_index = current_image_page_index;
    }
}
