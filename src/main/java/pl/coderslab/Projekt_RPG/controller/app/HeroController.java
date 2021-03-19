package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Projekt_RPG.project.Hero;
import pl.coderslab.Projekt_RPG.project.HeroRepository;
import pl.coderslab.Projekt_RPG.project.HeroService;
import pl.coderslab.Projekt_RPG.user.User;
import pl.coderslab.Projekt_RPG.user.UserRepository;
import pl.coderslab.Projekt_RPG.user.UserService;

import java.util.List;

@Controller
@RequestMapping("/character")
public class HeroController {
    private final HeroRepository heroRepository;
    private final HeroService heroService;
    private final UserService userService;
    private final UserRepository userRepository;

    public HeroController(HeroRepository heroRepository, HeroService heroService, UserService userService, UserRepository userRepository) {
        this.heroRepository = heroRepository;
        this.heroService = heroService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String character(@AuthenticationPrincipal UserDetails customUser, Model model) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        model.addAttribute("hero", hero);
        return "app/character";
    }

    @GetMapping("/list")
    public String charList(Model model, @AuthenticationPrincipal UserDetails customUser) {
        User user = userService.findByUserName(customUser.getUsername());
        user.setLoggedHero(0L);
        userRepository.save(user);
        List<Hero> heroList = heroRepository.findAll();
        model.addAttribute(heroList);
        return "app/charList";
    }

    @GetMapping("/create")
    public String charCreate(Model model) {
        Hero hero = new Hero();
        model.addAttribute("hero", hero);
        return "app/charCreate";
    }

    @PostMapping("/create")
    public String charCreatePost(@AuthenticationPrincipal UserDetails customUser, Hero hero) {
        hero.setUser(userService.findByUserName(customUser.getUsername()));
        heroService.createHero(hero);
        heroRepository.save(hero);
        return "redirect:/character/list";
    }
}
