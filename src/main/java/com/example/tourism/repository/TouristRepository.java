package com.example.tourism.repository;

import org.springframework.stereotype.Repository;
import com.example.tourism.model.TouristAttraction;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {

    private String attractionToUpdate = "";
    private ArrayList<TouristAttraction> attractions;

    private List<String> cityParts = List.of("Amager", "Nørrebro", "Østerbro", "Vesterbro", "North West", "Central Copenhagen", "Refshaleøen");
    private List<String> tags = List.of("Pub", "Sportsbar", "Studentbar", "Fussball", "Smoking Allowed", "Food", "Games", "Winebar", "Cocktailbar", "Dance Floor", "Karaoke", "21+");


    public List<String> getCityParts() {
        return cityParts;
    }

    public List<String> getTags() {
        return tags;
    }


    public void updateAttraction(TouristAttraction touristAttraction) {
        for (TouristAttraction t : attractions) {
            if (t.getName().equalsIgnoreCase(attractionToUpdate)) {
                t.setDescription(touristAttraction.getDescription());
                t.setCityPart(touristAttraction.getCityPart());
                t.setTags(touristAttraction.getTags());
            }
        }
    }



    public TouristRepository(){
        this.attractions = new ArrayList<>();
        attractions.add(new TouristAttraction("Guldbar", "Friday bar", "Nørrebro", List.of("Studentbar", "Fussball")));
        attractions.add(new TouristAttraction("Hatten", "Dorm bar", "Amager", List.of("Studentbar", "Fussball", "Dance Floor")));
        attractions.add(new TouristAttraction("BipBipBar", "Arcade bar", "Nørrebro", List.of("Games")));
    }

public List<TouristAttraction> getTouristAttractions(){
        return attractions;
}


    public void addAttraction(TouristAttraction touristAttraction){
        attractions.add(touristAttraction);
    }



    public TouristAttraction findAttractionByName(String name) {
        for (TouristAttraction t : attractions) {
            if (name.equalsIgnoreCase(t.getName())) {
                attractionToUpdate = t.getName();
                return t;

            }
        }
        return null;
    }

    public TouristAttraction getAttraction(String name) {
        for (TouristAttraction t : attractions) {
            if (name.equalsIgnoreCase(t.getName())) {
                return t;

            }
        }
        return null;
    }




    public void deleteAttraction(TouristAttraction touristAttraction) {
        attractions.remove(touristAttraction);
            }

}
