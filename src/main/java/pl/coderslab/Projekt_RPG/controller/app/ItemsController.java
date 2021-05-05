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
import pl.coderslab.Projekt_RPG.project.items.ItemService;
import pl.coderslab.Projekt_RPG.user.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/items")
public class ItemsController {
    private final HeroRepository heroRepository;
    private final UserService userService;
    private final HeroService heroService;
    private final ItemService itemService;

    public ItemsController(ItemService itemService, HeroRepository heroRepository, UserService userService, HeroService heroService) {
        this.heroRepository = heroRepository;
        this.userService = userService;
        this.heroService = heroService;
        this.itemService = itemService;
    }

    @GetMapping("")
    public String items(@AuthenticationPrincipal UserDetails customUser, Model model) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        LinkedHashMap<String,Item> itemList = itemService.getSortedItems(new HashMap<>(hero.getItemEquiped()));
        model.addAttribute("hero", hero);
        model.addAttribute("itemList", itemList);
        return "app/items";
    }

    @GetMapping("/list/{type}")
    public String itemsList(@PathVariable String type, Model model, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        List<Item> itemList;
        if(type.equals("item")) {
            itemList = hero.getItem();
        }else{
            itemList = hero.getItem().stream().filter(i->i.getType().equals(type)).collect(Collectors.toList());
        }
        model.addAttribute("itemList",itemList);
        model.addAttribute("type",type);
        return "app/itemsList";
    }

    @GetMapping("/list/change/{type}")
    public String suitableItemsList(@PathVariable String type, Model model, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        List<Item> itemList;
        if(type.equals("item")) {
            itemList = hero.getItem().stream()
                    .filter(i->i.getRace().contains(hero.getRace()))
                    .collect(Collectors.toList());
        }else{
            itemList = hero.getItem().stream()
                    .filter(i->i.getType().equals(type))
                    .filter(i->i.getRace().contains(hero.getRace()))
                    .collect(Collectors.toList());
        }
        model.addAttribute("hero",hero);
        model.addAttribute("itemList",itemList);
        model.addAttribute("type",type);
        return "app/itemsChangeList";
    }

    @GetMapping("/change/{type}/{id}")
    public String itemsChange(@PathVariable String type, @PathVariable Long id, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        Optional<Item> itemToEquip = hero.getItem().stream()
                .filter(i->i.getId().equals(id))
                .findFirst();
        if(itemToEquip.isPresent()) {
            Item itemNew = itemToEquip.get();
            if(itemNew.getRace().contains(hero.getRace())) {
                Map<String, Item> itemMap = hero.getItemEquiped();
                List <Item> itemList = hero.getItem();
                Item itemOld = itemMap.get(type);
                itemMap.put(type,itemNew);
                itemList.remove(itemNew);
                if(itemOld.getPrice() != null) {
                    itemList.add(itemOld);
                }
                heroService.updateHero(hero);
                heroRepository.save(hero);
            }
        }
        return "redirect:/items";
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

//    @GetMapping("/details/{type}/{id}")
//    public String itemsDetails(@PathVariable Long id,@PathVariable String type, Model model, @AuthenticationPrincipal UserDetails customUser) {
//        if(type.equals("weapon")) {
//            model.addAttribute("item", weaponRepository.getOne(id));
//        }else {
//            model.addAttribute("item", armorRepository.getOne(id));
//        }
//        model.addAttribute("type", type);
//        return "app/itemsDetails";
//    }
}
