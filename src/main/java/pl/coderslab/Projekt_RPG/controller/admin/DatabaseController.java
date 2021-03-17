package pl.coderslab.Projekt_RPG.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/add")
public class DatabaseController {
    @GetMapping("/weapons")
    @ResponseBody
    public String addWeapons() {
        return "Weapons add success";
    }

    @GetMapping("/armors")
    @ResponseBody
    public String addArmors() {
        return "Armors add success";
    }

    @GetMapping("/monsters")
    @ResponseBody
    public String addMonsters() {
        return "Monsters add success";
    }

    @GetMapping("/quests")
    @ResponseBody
    public String addQuests() {
        return "Quests add success";
    }
}
