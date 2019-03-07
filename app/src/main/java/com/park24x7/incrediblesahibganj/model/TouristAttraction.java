package com.park24x7.incrediblesahibganj.model;

import java.io.Serializable;
import java.util.ArrayList;

public class TouristAttraction implements Serializable {

    public String id = "";
    public String name = "";
    public String description = "";
    public String latitude = "";
    public String longitude = "";
    public ArrayList<ImageClass> images = new ArrayList<>();

}
