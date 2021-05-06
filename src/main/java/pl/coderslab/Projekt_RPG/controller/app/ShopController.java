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
import pl.coderslab.Projekt_RPG.project.items.Item;
import pl.coderslab.Projekt_RPG.project.items.ItemRepository;
import pl.coderslab.Projekt_RPG.project.items.ItemService;
import pl.coderslab.Projekt_RPG.project.items.items.Armor;
import pl.coderslab.Projekt_RPG.project.items.items.ArmorRepository;
import pl.coderslab.Projekt_RPG.project.items.items.Weapon;
import pl.coderslab.Projekt_RPG.project.items.items.WeaponRepository;
import pl.coderslab.Projekt_RPG.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final HeroRepository heroRepository;
    private final ItemRepository itemRepository;
    private final UserService userService;

    public ShopController(HeroRepository heroRepository, ItemRepository itemRepository, UserService userService) {
        this.heroRepository = heroRepository;
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    @GetMapping("")
    public String shop(@AuthenticationPrincipal UserDetails customUser, Model model) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        model.addAttribute("hero", hero);
        return "app/shop";
    }

    @GetMapping("/list/{transaction}")
    public String shopList(@AuthenticationPrincipal UserDetails customUser, @PathVariable String transaction, Model model) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        if(transaction.equals("sell")) {
            model.addAttribute("itemList", hero.getItem());

        }else if(transaction.equals("buy")){
            List<Item> itemList = Stream.concat(itemRepository.findAllByTier(1).stream(), itemRepository.findAllByTier(2).stream())
                    .filter(i->i.getRace().contains(hero.getRace()))
                    .collect(Collectors.toList());
            model.addAttribute("itemList", itemList);
        }

        model.addAttribute("hero", hero);
        model.addAttribute("transaction" , transaction);
        return "app/shopList";
    }

    @GetMapping("/buy/potion/{type}")
    public String shopBuyPotion(@AuthenticationPrincipal UserDetails customUser, @PathVariable String type) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        switch (type) {
            case "health":
                if(hero.getGoldAmount() >= 100) {
                    hero.setPotionHealth(hero.getPotionHealth() + 1);
                    hero.setGoldAmount(hero.getGoldAmount() - 200);
                }
                break;
            case "stamina":
                if(hero.getGoldAmount() >= 2000) {
                    hero.setPotionStamina(hero.getPotionStamina() + 1);
                    hero.setGoldAmount(hero.getGoldAmount() - 2000);
                }
                break;
        }
        heroRepository.save(hero);
        return "redirect:/shop/list/buy";
    }

    @GetMapping("/{transaction}/{id}")
    public String shopBuyWeapon(@AuthenticationPrincipal UserDetails customUser, @PathVariable String transaction, @PathVariable Long id) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        Item item = itemRepository.getOne(id);
        List <Item>itemList = hero.getItem();
        switch (transaction) {
            case "buy":
                if(hero.getGoldAmount() >= item.getPrice()) {
                    itemList.add(itemRepository.getOne(id));
                    hero.setGoldAmount(hero.getGoldAmount() - item.getPrice());
                }
                break;
            case "sell":
                itemList.remove(itemRepository.getOne(id));
                hero.setGoldAmount(hero.getGoldAmount() + item.getPrice()/5);
                break;
        }
        hero.setItem(itemList);
        heroRepository.save(hero);
        return "redirect:/shop/list/" + transaction;
    }
}
