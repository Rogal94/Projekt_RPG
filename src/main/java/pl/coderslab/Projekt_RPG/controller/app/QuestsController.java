package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quests")
public class QuestsController {
    @GetMapping("")
    public String quests() {
        return "app/quests";
    }
}