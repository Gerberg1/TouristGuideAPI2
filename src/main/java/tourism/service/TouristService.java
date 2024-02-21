package tourism.service;

import org.springframework.stereotype.Service;
import tourism.model.TouristAttraction;
import tourism.repository.TouristRepository;

import java.util.List;

@Service
public class TouristService {
    private TouristRepository repository;
    public TouristService (TouristRepository touristRepository) {
        this.repository = touristRepository;
    }
    public List <TouristAttraction> getTouristAttrations (){
        return repository.getTouristAttractions();
    }

    public List <String> getCityParts(){
        return repository.getCityParts();
    }

    public List <String> getTags(){
        return repository.getTags();
    }


    public void addAttraction(TouristAttraction touristAttraction){
        repository.addAttraction(touristAttraction);
    }

    public TouristAttraction findAttractionByName(String name){
        return repository.findAttractionByName(name);
    }

    public void updateAttraction(TouristAttraction touristAttraction){
        repository.updateAttraction(touristAttraction);
    }

    public void deleteAttraction(TouristAttraction touristAttraction){
         repository.deleteAttraction(touristAttraction);
    }

    public TouristAttraction getAttraction(String name){
        return repository.getAttraction(name);
    }


}
