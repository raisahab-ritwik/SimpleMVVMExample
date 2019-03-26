package com.park24x7.incrediblesahibganj.model;

import java.io.Serializable;

public class ImageClass implements Serializable {

    private static final long serialVersionUID = 3L;

    private boolean isPosted = false;
    private String base64value = "";
    private String imageType = "";
    private boolean isSelected = false;
    private int imageCount = 0;
    private String pictureID = "";
    private String touristAttractionID = "";
    private int total_image = 0;
    private String isFeaturedImage = "No";

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public void setPosted(boolean isPosted) {
        this.isPosted = isPosted;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean getIsPosted() {
        return isPosted;
    }

    public void setIsPosted(boolean isPosted) {
        this.isPosted = isPosted;
    }

    public String getBase64value() {
        return base64value;
    }

    public void setBase64value(String base64value) {
        this.base64value = base64value;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getPictureID() {
        return pictureID;
    }

    public void setPictureID(String pictureID) {
        this.pictureID = pictureID;
    }

    public String getTouristAttractionID() {
        return touristAttractionID;
    }

    public void setTouristAttractionID(String touristAttractionID) {
        this.touristAttractionID = touristAttractionID;
    }

    public int getTotal_image() {
        return total_image;
    }

    public void setTotal_image(int total_image) {
        this.total_image = total_image;
    }

    public String getIsFeaturedImage() {
        return isFeaturedImage;
    }

    public void setIsFeaturedImage(String isFeaturedImage) {
        this.isFeaturedImage = isFeaturedImage;
    }
}
