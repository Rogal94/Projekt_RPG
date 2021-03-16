package pl.coderslab.Projekt_RPG;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "start/home";
    }

    @GetMapping("/login")
    public String login() {
        return "start/login";
    }

    @PostMapping("/login")
    public String loginPost() {
        return "redirect:/character/list";
    }

    @GetMapping("/register")
    public String register() {
        return "start/register";
    }

    @PostMapping("/register")
    public String registerPost() {
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @RequestMapping("/character/list")
    public String charList() {
        return "app/charList";
    }

    @GetMapping("/character/create")
    public String charCreate() {
        return "app/charCreate";
    }

    @PostMapping("/character/create")
    public String charCreatePost() {
        return "redirect:/character/list";
    }

    @RequestMapping("/panel")
    public String mainPanel() {
        return "app/mainPanel";
    }

    @RequestMapping("/character")
    public String character() {
        return "app/character";
    }

    @RequestMapping("/items")
    public String items() {
        return "app/items";
    }

    @RequestMapping("/items/list")
    public String itemsList() {
        return "app/itemsList";
    }

    @RequestMapping("/shop")
    public String shop() {
        return "app/shop";
    }

    @RequestMapping("/shop/list")
    public String shopList() {
        return "app/shopList";
    }

    @RequestMapping("/monsters")
    public String monsters() {
        return "app/monsters";
    }

    @RequestMapping("/monsters/fight")
    public String monstersFight() {
        return "app/monstersFight";
    }

    @RequestMapping("/quests")
    public String quests() {
        return "app/quests";
    }

    @RequestMapping("/reward")
    public String reward() {
        return "app/reward";
    }
}
