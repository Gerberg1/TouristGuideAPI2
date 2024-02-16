package tourism.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tourism.model.TouristAttraction;
import tourism.service.TouristService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="attractions")
public class TouristController {

    private TouristService touristService;
    public TouristController(TouristService touristService){
        this.touristService = touristService;
    }

    /*@GetMapping(path = "")
    public ResponseEntity<List<TouristAttraction>> getTouristAttractions() {
        List attractions = touristService.getTouristAttrations();
        return new ResponseEntity<List<TouristAttraction>>(attractions, HttpStatus.OK);
    }*/

    @GetMapping(path = "")
    public String getTouristAttractions(Model model) {
        List attractions = touristService.getTouristAttrations();
        model.addAttribute("attractions", attractions);
        return "index";
    }

    @GetMapping("/list")
    public String showAll(Model model){
        List<TouristAttraction> attractions = touristService.getTouristAttrations();
        model.addAttribute("attractions", attractions);
        return "find_a_bar";
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<TouristAttraction> getAttraction(@PathVariable String name) {
        TouristAttraction t = touristService.getTouristAttraction(name);
        return new ResponseEntity<TouristAttraction>(t, HttpStatus.OK);
    }

    /*@GetMapping(path = "/delete/{name}")
    public ResponseEntity<List<TouristAttraction>> deleteAttraction(@PathVariable String name) {
       List attractions = touristService.deleteAttraction(name);
        return new ResponseEntity<List<TouristAttraction>>(attractions, HttpStatus.OK);

    }*/

   /* @PostMapping(path="/add")
   public ResponseEntity<TouristAttraction> addAttraction (@RequestParam @RequestBody Map<String, String> numbers) {
        String name = numbers.get("newName");
        String description = numbers.get("newDescription");
       TouristAttraction touristAttraction = touristService.addAttraction(new TouristAttraction(name, description));
       return new ResponseEntity<TouristAttraction>(touristAttraction, HttpStatus.OK);
   }*/
    @GetMapping(path="/add")
   public String addAttraction (Model model) {
        model.addAttribute("attraction", new TouristAttraction());
       return "addbar";
   }



    @PostMapping("/add")
    public String addAttraction(@ModelAttribute TouristAttraction touristAttraction){
        touristService.addAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping(path="/{name}/update")
    public String updateAttraction (@PathVariable String name, Model model) {
        TouristAttraction touristAttraction = touristService.findAttractionByName(name);
        model.addAttribute("attraction", touristAttraction);
        return "updatebar";
    }
    @PostMapping(path="/update")
    public String updateAttraction(@ModelAttribute TouristAttraction touristAttraction){
    touristService.updateAttraction(touristAttraction);
    return "updatebar";
    }


}




