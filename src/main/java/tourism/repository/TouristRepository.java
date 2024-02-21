package tourism.repository;

import org.springframework.stereotype.Repository;
import tourism.model.TouristAttraction;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {

    private String attractionToUpdate = "";
    private ArrayList<TouristAttraction> attractions;

    private List<String> cityParts = List.of("Amager", "Nørrebro", "Østerbro", "Vesterbro", "Nordvest");
    private List<String> tags = List.of("pub", "sportsbar", "studentbar", "fussball", "smoking allowed");


    public List<String> getCityParts(){
        return cityParts;
    }

    public List<String> getTags(){
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
        attractions.add(new TouristAttraction("Guldbar", "Fredagscafé", "Nørrebro", List.of("studentbar")));
        attractions.add(new TouristAttraction("Hatten", "Godbar", "Amager", List.of("studentbar", "pub")));
        attractions.add(new TouristAttraction("BipBipBar", "Dyr bar", "Nørrebro", List.of("sportsbar")));
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

    public void deleteAttraction(String name) {
        for (TouristAttraction t : attractions) {
            if (name.equalsIgnoreCase(t.getName())) {
                attractions.remove(t);
            }
        }
    }


}
