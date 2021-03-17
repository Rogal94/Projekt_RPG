package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @GetMapping("")
    public String shop() {
        return "app/shop";
    }

    @GetMapping("/list")
    public String shopList() {
        return "app/shopList";
    }
}
