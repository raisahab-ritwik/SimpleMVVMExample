package com.park24x7.incrediblesahibganj.data;

import com.park24x7.incrediblesahibganj.model.Image;
import com.park24x7.incrediblesahibganj.model.ImageClass;
import com.park24x7.incrediblesahibganj.model.TouristAttraction;

import java.util.ArrayList;

public class Dummy {

    public static ArrayList<TouristAttraction> getDummyTouristAttractions() {
        ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();
        TouristAttraction touristAttraction = new TouristAttraction();
        touristAttraction.id = "1";
        touristAttraction.name = "Shukrawasini Mandir";
        touristAttraction.description = "Lorem ipsum dolor sit amet, ne ius oporteat recusabo accusamus, everti accumsan qualisque te quo. Sit eruditi dolores omittam no, per ei dicunt ocurreret. Eu officiis partiendo petentium usu, id vero eloquentiam mei. Nisl quidam aeterno mea id, ea pri fierent incorrupte. Eos ne lorem novum maiestatis.\n" +
                "\n" +
                "No pri dictas menandri, est ad doming tamquam definitiones, altera commodo atomorum nec no. An utinam mandamus mel, pro et vide aeterno, eu vix insolens sapientem. Pro in quidam labores. No nostro everti scripserit quo. An graeci sententiae eos, id suas delectus salutandi vel.\n";
        touristAttraction.images = new ArrayList<ImageClass>();
        touristAttractions.add(touristAttraction);
        return touristAttractions;
    }
}
