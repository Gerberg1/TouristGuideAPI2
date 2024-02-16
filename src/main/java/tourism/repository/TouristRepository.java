package tourism.repository;

import org.springframework.stereotype.Repository;
import tourism.model.TouristAttraction;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {

    private String attractionToUpdate = "";
    private ArrayList<TouristAttraction> attractions;

    public TouristRepository(){
        this.attractions = new ArrayList<>();
        attractions.add(new TouristAttraction("Guldbar", "Fredagscafé"));
        attractions.add(new TouristAttraction("Hatten", "Godbar"));
        attractions.add(new TouristAttraction("BipBipBar", "Dyr bar"));
    }

public List<TouristAttraction> getTouristAttractions(){
        return attractions;
}

public TouristAttraction getTouristAttraction (String name){
        for (TouristAttraction t : attractions){
            if (name.equalsIgnoreCase(t.getName())){
                return t;
            }
        }
        return null;
    }

    public void addAttraction(TouristAttraction touristAttraction){
        attractions.add(touristAttraction);
    }

    public void updateAttraction(TouristAttraction touristAttraction) {
        for (TouristAttraction t : attractions) {
            if (t.getName().equalsIgnoreCase(attractionToUpdate)) {
                t.setDescription(touristAttraction.getDescription());
                t.setName(touristAttraction.getName());
            }
        }
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

    public List<TouristAttraction> deleteAttraction(String name) {
        for (TouristAttraction t : attractions) {
            if (name.equalsIgnoreCase(t.getName())) {
                attractions.remove(t);
            }
        }
        return attractions;
    }


}
