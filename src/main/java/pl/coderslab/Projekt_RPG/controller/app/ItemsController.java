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
import pl.coderslab.Projekt_RPG.project.items.Item;
import pl.coderslab.Projekt_RPG.project.items.ItemRepository;
import pl.coderslab.Projekt_RPG.project.items.ItemService;
import pl.coderslab.Projekt_RPG.project.items.items.Armor;
import pl.coderslab.Projekt_RPG.project.items.items.ArmorRepository;
import pl.coderslab.Projekt_RPG.project.items.items.Weapon;
import pl.coderslab.Projekt_RPG.project.items.items.WeaponRepository;
import pl.coderslab.Projekt_RPG.user.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/items")
public class ItemsController {
    private final HeroRepository heroRepository;
    private final UserService userService;
    private final WeaponRepository weaponRepository;
    private final HeroService heroService;
    private final ArmorRepository armorRepository;
    private final ItemRepository itemRepository;
    private final ItemService itemService;

    public ItemsController(ItemService itemService, HeroRepository heroRepository, UserService userService, WeaponRepository weaponRepository, HeroService heroService, ArmorRepository armorRepository,ItemRepository itemRepository) {
        this.heroRepository = heroRepository;
        this.userService = userService;
        this.weaponRepository = weaponRepository;
        this.heroService = heroService;
        this.armorRepository = armorRepository;
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    @GetMapping("")
    public String items(@AuthenticationPrincipal UserDetails customUser, Model model) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        Weapon weapon = weaponRepository.getOne(hero.getItemEquiped().get("weapon").getId());
        Map<String,Item> itemList = hero.getItemEquiped();
        List <Armor> armorList = itemService.getSortedArmorFromItems(itemList);
        model.addAttribute("hero", hero);
        model.addAttribute("weapon", weapon);
        model.addAttribute("armorList", armorList);
        model.addAttribute("itemList", itemList);
        return "app/items";
    }

    @GetMapping("/list/{type}")
    public String itemsList(@PathVariable String type, Model model, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        List<Item> itemList = hero.getItem().stream().filter(i->i.getType().equals(type)).collect(Collectors.toList());
        model.addAttribute("itemList",itemList);
        model.addAttribute("type",type);
        return "app/itemsList";
    }

    @GetMapping("/add/{points}")
    public String itemsPoints(@PathVariable String points, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        if(points.equals("health") && hero.getPotionHealth() > 0) {
            hero.setHealthPointsCurrent(hero.getHealthPointsCurrent() + 100);
            if(hero.getHealthPointsCurrent() > hero.getHealthPointsMax()) {
                hero.setHealthPointsCurrent(hero.getHealthPointsMax());
            }
            hero.setPotionHealth(hero.getPotionHealth() - 1);
        }
        if(points.equals("stamina") && hero.getPotionStamina() > 0) {
            hero.setStaminaCurrent(hero.getStaminaCurrent() + 10);
            if(hero.getStaminaCurrent() > hero.getStaminaMax()) {
                hero.setStaminaCurrent(hero.getStaminaMax());
            }
            hero.setPotionStamina(hero.getPotionStamina() - 1);
        }
        if(points.equals("health10") && hero.getPotionHealth() > 9) {
            hero.setHealthPointsCurrent(hero.getHealthPointsCurrent() + 1000);
            if(hero.getHealthPointsCurrent() > hero.getHealthPointsMax()) {
                hero.setHealthPointsCurrent(hero.getHealthPointsMax());
            }
            hero.setPotionHealth(hero.getPotionHealth() - 10);
        }
        if(points.equals("stamina10") && hero.getPotionStamina() > 9) {
            hero.setStaminaCurrent(hero.getStaminaCurrent() + 100);
            if(hero.getStaminaCurrent() > hero.getStaminaMax()) {
                hero.setStaminaCurrent(hero.getStaminaMax());
            }
            hero.setPotionStamina(hero.getPotionStamina() - 10);
        }
        heroRepository.save(hero);
        return "redirect:/items";
    }

    @GetMapping("/change/{type}/{id}")
    public String itemsChange(@PathVariable String type, @PathVariable Long id, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        Map<String, Item> itemMap = hero.getItemEquiped();
        itemMap.put(type,itemRepository.getOne(id));
        heroService.updateHero(hero);
        heroRepository.save(hero);
        return "redirect:/items";
    }

    @GetMapping("/details/{type}/{id}")
    public String itemsDetails(@PathVariable Long id,@PathVariable String type, Model model, @AuthenticationPrincipal UserDetails customUser) {
        if(type.equals("weapon")) {
            model.addAttribute("item", weaponRepository.getOne(id));
        }else {
            model.addAttribute("item", armorRepository.getOne(id));
        }
        model.addAttribute("type", type);
        return "app/itemsDetails";
    }
}
