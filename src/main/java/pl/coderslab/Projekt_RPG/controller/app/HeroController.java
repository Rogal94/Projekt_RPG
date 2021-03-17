package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/character")
public class HeroController {
    @GetMapping("")
    public String character() {
        return "app/character";
    }

    @GetMapping("/list")
    public String charList() {
        return "app/charList";
    }

    @GetMapping("/create")
    public String charCreate() {
        return "app/charCreate";
    }

    @PostMapping("/create")
    public String charCreatePost() {
        return "redirect:/character/list";
    }
}
