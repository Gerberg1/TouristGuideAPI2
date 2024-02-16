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

    public TouristAttraction addAttraction(TouristAttraction touristAttraction){
        repository.addAttraction(touristAttraction);
        return touristAttraction;
    }

    public TouristAttraction updateAttraction(String name, String newDescription){
        return repository.updateAttraction(name, newDescription);
    }

    public List<TouristAttraction> deleteAttraction(String name){
         return repository.deleteAttraction(name);
    }


}
