package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemsController {
    @GetMapping("")
    public String items() {
        return "app/items";
    }

    @GetMapping("/list")
    public String itemsList() {
        return "app/itemsList";
    }
}
