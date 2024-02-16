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

    public TouristAttraction getTouristAttraction (String name) {
        return repository.getTouristAttraction(name);
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

    public List<TouristAttraction> deleteAttraction(String name){
         return repository.deleteAttraction(name);
    }


}
