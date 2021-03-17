package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monsters")
public class MonsterController {
    @GetMapping("")
    public String monsters() {
        return "app/monsters";
    }

    @GetMapping("/fight")
    public String monstersFight() {
        return "app/monstersFight";
    }
}
