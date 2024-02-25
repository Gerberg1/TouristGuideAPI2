package tourism.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tourism.model.TouristAttraction;
import tourism.service.TouristService;

import java.util.List;

@Controller
@RequestMapping(path="attractions")
public class TouristController {

    private TouristService touristService;
    public TouristController(TouristService touristService){
        this.touristService = touristService;
    }

    @GetMapping(path = "")
    public String getTouristAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getTouristAttrations();
        model.addAttribute("attractions", attractions);
        return "index";
    }

    @GetMapping("/list")
    public String showAll(Model model){
        List<TouristAttraction> attractions = touristService.getTouristAttrations();
        model.addAttribute("attractions", attractions);
        return "find_a_bar";
    }

    @GetMapping(path = "/{name}/delete")
    public String deleteAttraction (@PathVariable String name, Model model)  {
        TouristAttraction touristAttraction = touristService.getAttraction(name);
        touristService.deleteAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping(path = "/{name}/tags")
    public String showTags (@PathVariable String name, Model model)  {
        TouristAttraction touristAttraction = touristService.findAttractionByName(name);
        model.addAttribute("attraction", touristAttraction);
        return "tags";
    }


    @GetMapping(path="/add")
    public String addAttraction (Model model) {
        model.addAttribute("attraction", new TouristAttraction());
        model.addAttribute("cityparts", touristService.getCityParts());
        model.addAttribute("tags", touristService.getTags());
        return "addbar";
    }



    @PostMapping("/save")
    public String addAttraction(@ModelAttribute TouristAttraction touristAttraction){
        touristService.addAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping(path="/{name}/edit")
    public String updateAttraction (@PathVariable String name, Model model) {
        TouristAttraction touristAttraction = touristService.findAttractionByName(name);
        model.addAttribute("attraction", touristAttraction);
        model.addAttribute("cityparts", touristService.getCityParts());
        model.addAttribute("tags", touristService.getTags());
        return "updatebar";


    }
    @PostMapping(path="/update")
    public String updateAttraction(TouristAttraction touristAttraction){
    touristService.updateAttraction(touristAttraction);
    return "redirect:/attractions";
    }

   @GetMapping(path="/contact")
    public String getContact(){
       return "contact";
    }

    @GetMapping(path="/about")
    public String getAbout(){
        return "about";
    }

    @GetMapping(path="/history")
    public String getHistory(){
        return "bar_history";
    }

}




