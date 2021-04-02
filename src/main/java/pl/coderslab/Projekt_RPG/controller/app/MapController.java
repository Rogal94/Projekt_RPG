package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Projekt_RPG.project.*;
import pl.coderslab.Projekt_RPG.user.User;
import pl.coderslab.Projekt_RPG.user.UserService;

@Controller
@RequestMapping("/map")
public class MapController {
    private final HeroRepository heroRepository;
    private final UserService userService;
    private final MonsterRepository monsterRepository;

    public MapController(HeroRepository heroRepository, UserService userService, MonsterRepository monsterRepository) {
        this.heroRepository = heroRepository;
        this.userService = userService;
        this.monsterRepository = monsterRepository;
    }

    @GetMapping("")
    public String map(@AuthenticationPrincipal UserDetails customUser, Model model) {
        User user = userService.findByUserName(customUser.getUsername());
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        hero.setLastMap(null);
        heroRepository.save(hero);
        model.addAttribute("user", user);
        return "app/map";
    }

    @GetMapping("/{map}/city")
    public String mapCity(Model model, @AuthenticationPrincipal UserDetails customUser, @PathVariable String map) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        hero.setLastMap(map);
        heroRepository.save(hero);
        model.addAttribute("hero", hero);
        model.addAttribute("city", "SHOP");
        return "app/mapFight";
    }

    @GetMapping("/{map}/monster/{monsterId}")
    public String mapFight(Model model, @AuthenticationPrincipal UserDetails customUser, @PathVariable String map, @PathVariable Long monsterId) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        hero.setLastMap(map);
        heroRepository.save(hero);
        Monster monster = monsterRepository.getOne(monsterId);
        model.addAttribute("hero", hero);
        model.addAttribute("monster", monster);
        return "app/mapFight";
    }
}
