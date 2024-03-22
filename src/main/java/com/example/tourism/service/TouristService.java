package com.example.tourism.service;

import com.example.tourism.repository.TouristRepositoryDB;
import org.springframework.stereotype.Service;
import com.example.tourism.model.TouristAttraction;
import com.example.tourism.repository.TouristRepository;

import java.sql.SQLException;
import java.util.List;

@Service
public class TouristService {
    private TouristRepository repository;
    private TouristRepositoryDB tdb;
    public TouristService (TouristRepositoryDB tdb) {
        this.tdb = tdb;
    }
    public List <TouristAttraction> getTouristAttractions (){
        return tdb.getTouristAttractions();
    }

    public List <String> getCityParts(){
        return tdb.getCityParts();
    }

    public List <String> getTags(){
        return tdb.getTags();
    }


    public void addAttraction(TouristAttraction touristAttraction){
        tdb.addAttraction(touristAttraction);
    }

    public void addAttractionTags(TouristAttraction touristAttraction) {
        tdb.addAttractionTags(touristAttraction);
    }

    public TouristAttraction findAttractionByName(String name){
        return tdb.findAttractionByName(name);
    }

    public void updateAttraction(TouristAttraction touristAttraction){
        tdb.updateAttraction(touristAttraction);
    }

    public void deleteAttraction(TouristAttraction touristAttraction){
         tdb.deleteAttraction(touristAttraction);
    }


}
