package pl.coderslab.Projekt_RPG.controller.start;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("")
    public String home() {
        return "start/home";
    }
}
