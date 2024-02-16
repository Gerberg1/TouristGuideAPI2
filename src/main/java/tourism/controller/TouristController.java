package tourism.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tourism.model.TouristAttraction;
import tourism.service.TouristService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="attractions", method={RequestMethod.GET, RequestMethod.POST})
public class TouristController {

    private TouristService touristService;
    public TouristController(TouristService touristService){
        this.touristService = touristService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<TouristAttraction>> getTouristAttractions() {
        List attractions = touristService.getTouristAttrations();
        return new ResponseEntity<List<TouristAttraction>>(attractions, HttpStatus.OK);
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<TouristAttraction> getAttraction(@PathVariable String name) {
        TouristAttraction t = touristService.getTouristAttraction(name);
        return new ResponseEntity<TouristAttraction>(t, HttpStatus.OK);
    }

    @GetMapping(path = "/delete/{name}")
    public ResponseEntity<List<TouristAttraction>> deleteAttraction(@PathVariable String name) {
       List attractions = touristService.deleteAttraction(name);
        return new ResponseEntity<List<TouristAttraction>>(attractions, HttpStatus.OK);

    }

    @PostMapping(path="/add")
   public ResponseEntity<TouristAttraction> addAttraction (@RequestParam @RequestBody Map<String, String> numbers) {
        String name = numbers.get("newName");
        String description = numbers.get("newDescription");
       TouristAttraction touristAttraction = touristService.addAttraction(new TouristAttraction(name, description));
       return new ResponseEntity<TouristAttraction>(touristAttraction, HttpStatus.OK);
   }

    @PostMapping(path="/update")
    public ResponseEntity<TouristAttraction> updateAttraction (@RequestParam @RequestBody Map<String, String> numbers) {
        String name = numbers.get("name");
        String description = numbers.get("updateDescription");
        TouristAttraction touristAttraction = touristService.updateAttraction(name, description);
        return new ResponseEntity<TouristAttraction>(touristAttraction, HttpStatus.OK);
    }



    /*@PostMapping(path = "/opret")
    public String postAttraction(@RequestBody @RequestParam Map<String, String> numbers) {
        String name = numbers.get("name");
        String description = numbers.get("description");
        TouristAttraction touristAttraction = touristService.addAttraction(new TouristAttraction(name, description));
        return "Attraktionen er oprettet!";
    }*/




}




