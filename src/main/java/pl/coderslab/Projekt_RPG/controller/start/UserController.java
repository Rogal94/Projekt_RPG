package pl.coderslab.Projekt_RPG.controller.start;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.Projekt_RPG.user.User;
import pl.coderslab.Projekt_RPG.user.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "start/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("error", "error");
        return "start/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user",new User());
        return "start/register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid User user, BindingResult result) {
        if(result.hasErrors()) {
            return "start/register";
        }
            userService.saveUser(user);
            return "redirect:/login";
    }
}
