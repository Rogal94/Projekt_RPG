package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Projekt_RPG.project.hero.Hero;
import pl.coderslab.Projekt_RPG.project.hero.HeroRepository;
import pl.coderslab.Projekt_RPG.project.hero.Quest;
import pl.coderslab.Projekt_RPG.project.hero.QuestRepository;
import pl.coderslab.Projekt_RPG.user.UserService;

@Controller
@RequestMapping("/quests")
public class QuestsController {
    private final HeroRepository heroRepository;
    private final UserService userService;
    private final QuestRepository questRepository;

    public QuestsController(HeroRepository heroRepository, UserService userService, QuestRepository questRepository) {
        this.heroRepository = heroRepository;
        this.userService = userService;
        this.questRepository = questRepository;
    }

    @GetMapping("")
    public String quests(Model model, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        Quest quest = hero.getQuest();
        if(hero.getQuest().getId()<9) {
            Quest nextQuest = questRepository.getOne(quest.getId() + 1);
            model.addAttribute("nextQuest", nextQuest);
        }
        model.addAttribute("quest", quest);
        model.addAttribute("hero", hero);
        return "app/quests";
    }
}
