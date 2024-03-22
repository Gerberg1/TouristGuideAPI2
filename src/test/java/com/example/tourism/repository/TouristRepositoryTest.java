package com.example.tourism.repository;

import com.example.tourism.model.TouristAttraction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TouristRepositoryTest {

    @Test
    void getTags(){
        TouristRepository touristRepository = new TouristRepository();
   List<String> tags = List.of("Pub", "Sportsbar", "Studentbar", "Fussball", "Smoking Allowed", "Food", "Games", "Winebar", "Cocktailbar", "Dance Floor", "Karaoke", "21+");
    List expectedResult = touristRepository.getTags();
    List actualResult = tags;
    assertEquals(expectedResult, actualResult);
    }

    @Test
    void addAttraction() {
        TouristRepository touristRepository = new TouristRepository();
        int number = touristRepository.getTouristAttractions().size();
        int expectedResult = touristRepository.getTouristAttractions().size()+1;
        TouristAttraction atttractionToAdd = new TouristAttraction("bar", "a tester bar", "amager");
        touristRepository.addAttraction(atttractionToAdd);
        int actualResult = touristRepository.getTouristAttractions().size();
        assertEquals(expectedResult, actualResult);
    }

}