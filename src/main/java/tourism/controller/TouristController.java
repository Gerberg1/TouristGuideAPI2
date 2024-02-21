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
        touristService.deleteAttraction(name);
        model.addAttribute("attraction", name);
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



    @PostMapping("/add")
    public String addAttraction(@ModelAttribute TouristAttraction touristAttraction){
        touristService.addAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping(path="/{name}/update")
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


}




