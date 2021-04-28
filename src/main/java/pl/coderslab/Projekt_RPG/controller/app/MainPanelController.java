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
import pl.coderslab.Projekt_RPG.project.hero.HeroService;
import pl.coderslab.Projekt_RPG.user.User;
import pl.coderslab.Projekt_RPG.user.UserRepository;
import pl.coderslab.Projekt_RPG.user.UserService;

@Controller
@RequestMapping("/panel")
public class MainPanelController {

    private final HeroService heroService;
    private final UserService userService;
    private final HeroRepository heroRepository;
    private final UserRepository userRepository;

    public MainPanelController(HeroService heroService, UserService userService, HeroRepository heroRepository, UserRepository userRepository) {
        this.heroService = heroService;
        this.userService = userService;
        this.heroRepository = heroRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String mainPanel(@AuthenticationPrincipal UserDetails customUser, Model model) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        model.addAttribute("hero", hero);
        return "app/mainPanel";
    }

    @GetMapping("/{id}")
    public String mainPanelStart(@AuthenticationPrincipal UserDetails customUser, @PathVariable Long id) {
        User user = userService.findByUserName(customUser.getUsername());
        if(heroService.checkHero(id, user)) {
            user.setLoggedHero(id);
            userRepository.save(user);
            return "redirect:/panel";
        }
        return "redirect:/logout";
    }


}
