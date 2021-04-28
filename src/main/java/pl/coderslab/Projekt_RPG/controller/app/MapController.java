package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Projekt_RPG.project.hero.Hero;
import pl.coderslab.Projekt_RPG.project.hero.HeroRepository;
import pl.coderslab.Projekt_RPG.project.monster.Monster;
import pl.coderslab.Projekt_RPG.project.monster.MonsterRepository;
import pl.coderslab.Projekt_RPG.project.monster.MonsterSession;
import pl.coderslab.Projekt_RPG.user.User;
import pl.coderslab.Projekt_RPG.user.UserService;

@Controller
@RequestMapping("/map")
public class MapController {
    private final HeroRepository heroRepository;
    private final UserService userService;
    private final MonsterRepository monsterRepository;
    private final MonsterSession monsterSession;

    public MapController(MonsterSession monsterSession, HeroRepository heroRepository, UserService userService, MonsterRepository monsterRepository) {
        this.heroRepository = heroRepository;
        this.userService = userService;
        this.monsterRepository = monsterRepository;
        this.monsterSession = monsterSession;
    }

    @GetMapping("")
    public String map(@AuthenticationPrincipal UserDetails customUser, Model model) {
        User user = userService.findByUserName(customUser.getUsername());
        monsterSession.setLastMap(null);
        model.addAttribute("user", user);
        return "app/map";
    }

    @GetMapping("/{map}/city")
    public String mapCity(Model model, @AuthenticationPrincipal UserDetails customUser, @PathVariable String map) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        monsterSession.setLastMap(map);
        model.addAttribute("lastMap", map);
        model.addAttribute("hero", hero);
        model.addAttribute("city", "SHOP");
        return "app/mapFight";
    }

    @GetMapping("/{map}/monster/{monsterId}")
    public String mapFight(Model model, @AuthenticationPrincipal UserDetails customUser, @PathVariable String map, @PathVariable Long monsterId) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        monsterSession.setLastMap(map);
        Monster monster = monsterRepository.getOne(monsterId);
        model.addAttribute("lastMap", map);
        model.addAttribute("hero", hero);
        model.addAttribute("monster", monster);
        return "app/mapFight";
    }
}
