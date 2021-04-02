package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Projekt_RPG.user.User;
import pl.coderslab.Projekt_RPG.user.UserRepository;
import pl.coderslab.Projekt_RPG.user.UserService;


@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final UserService userService;
    private final UserRepository userRepository;

    public SettingsController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String settings(@AuthenticationPrincipal UserDetails customUser, Model model) {
        User user = userService.findByUserName(customUser.getUsername());
        model.addAttribute("user", user);
        return "app/settings";
    }

    @PostMapping("")
    public String settingsForm(User user, @AuthenticationPrincipal UserDetails customUser) {
        User cUser = userService.findByUserName(customUser.getUsername());
        if(user.getMapSize()>20) {
            cUser.setMapSize(user.getMapSize());
            cUser.setMusicVolume(user.getMusicVolume());
            cUser.setCombatStyle(user.getCombatStyle());
            userRepository.save(cUser);
        }
        return "redirect:/panel";
    }
}

