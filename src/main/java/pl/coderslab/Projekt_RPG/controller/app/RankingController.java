package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Projekt_RPG.project.hero.Hero;
import pl.coderslab.Projekt_RPG.project.hero.HeroRepository;
import pl.coderslab.Projekt_RPG.user.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ranking")
public class RankingController {
    private final HeroRepository heroRepository;
    private final UserService userService;

    public RankingController(HeroRepository heroRepository, UserService userService) {
        this.heroRepository = heroRepository;
        this.userService = userService;
    }

//    @GetMapping("")
//    public String ranking() {
//        return "app/ranking";
//    }

    @GetMapping("/{ranking}")
    public String rankingList(@PathVariable String ranking, Model model) {
        List<Hero> rankingList = new ArrayList<>();
        switch(ranking) {
            case "level":
                rankingList = heroRepository.findTop10ByOrderByLevelDesc();
                break;
            case "attack":
                rankingList = heroRepository.findTop10ByOrderByAttackDesc();
                break;
            case "defense":
                rankingList = heroRepository.findTop10ByOrderByDefenceDesc();
                break;
        }
        model.addAttribute("rankingBy", ranking);
        model.addAttribute("rankingList", rankingList);
        return "app/rankingList";
    }
}
