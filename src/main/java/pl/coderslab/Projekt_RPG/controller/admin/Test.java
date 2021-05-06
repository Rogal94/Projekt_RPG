package pl.coderslab.Projekt_RPG.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Projekt_RPG.project.items.Item;
import pl.coderslab.Projekt_RPG.project.items.ItemRepository;
import pl.coderslab.Projekt_RPG.project.items.items.Weapon;


import java.util.List;

@Controller
@RequestMapping("/test")
public class Test {
    private final ItemRepository itemRepository;

    public Test(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @GetMapping("")
    public String showAdd() {
        return "/";
    }
}
