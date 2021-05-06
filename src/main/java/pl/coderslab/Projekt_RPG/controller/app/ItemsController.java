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
    private final ItemRepository itemRepository;

    public ItemsController(ItemRepository itemRepository, ItemService itemService, HeroRepository heroRepository, UserService userService, HeroService heroService) {
        this.heroRepository = heroRepository;
        this.userService = userService;
        this.heroService = heroService;
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

    @GetMapping("")
    public String items(@AuthenticationPrincipal UserDetails customUser, Model model) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        LinkedHashMap<String,Item> itemList = itemService.getSortedItems(new HashMap<>(hero.getItemEquipped()));
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

    @GetMapping("/change/{id}")
    public String itemsChange(@PathVariable Long id, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        Optional<Item> itemToEquip = hero.getItem().stream()
                .filter(i->i.getId().equals(id))
                .findFirst();
        if(itemToEquip.isPresent()) {
            Item itemNew = itemToEquip.get();
            if(itemNew.getRace().contains(hero.getRace())) {
                Map<String, Item> itemMap = hero.getItemEquipped();
                List <Item> itemList = hero.getItem();
                Item itemOld = itemMap.get(itemNew.getType());
                itemMap.put(itemNew.getType(),itemNew);
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

    @GetMapping("/upgrade/{equipped}/{id}")
    public String itemsUpgrade(@PathVariable Long id, @PathVariable String equipped, Model model, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        Optional<Item> itemToEquip;
        if(equipped.equals("equipped")) {
            itemToEquip = hero.getItemEquipped().values().stream()
                    .filter(i->i.getId().equals(id))
                    .findFirst();
            model.addAttribute("equipped", true);
        }else {
            itemToEquip = hero.getItem().stream()
                    .filter(i->i.getId().equals(id))
                    .findFirst();
            model.addAttribute("equipped", false);
        }
        if(itemToEquip.isPresent()) {
            Item item = itemToEquip.get();
            Integer chance = itemService.getChanceToUpgrade(item.getGrade());
            model.addAttribute("chance", chance);
            model.addAttribute("item", item);
            model.addAttribute("hero", hero);
        }
        return "app/itemsUpgrade";
    }

    @GetMapping("/upgrade/try/{equipped}/{id}")
    public String itemsUpgradeTry(@PathVariable Long id, @PathVariable String equipped, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        Optional<Item> itemToUpgrade;
        Item itemNew = itemRepository.getOne(id);
        if(equipped.equals("equipped")) {
            itemToUpgrade = hero.getItemEquipped().values().stream()
                    .filter(i->i.getId().equals(id))
                    .findFirst();
        }else {
            itemToUpgrade = hero.getItem().stream()
                    .filter(i->i.getId().equals(id))
                    .findFirst();
        }
        if(itemToUpgrade.isPresent()) {
            Item item = itemToUpgrade.get();
            if(hero.getGoldAmount() >= item.getPrice()/2) {
                boolean upgrade = itemService.upgradeItem(itemService.getChanceToUpgrade(item.getGrade()));
                if(upgrade) {
                    itemNew = itemRepository.findByNameAndGrade(item.getName(),item.getGrade() + 1);
                }else{
                    itemNew = itemRepository.findByNameAndGrade(item.getName(),item.getGrade() - 1);
                }
                if(equipped.equals("equipped")) {
                    hero.getItemEquipped().put(item.getType(),itemNew);
                }else {
                    hero.getItem().set(hero.getItem().indexOf(item), itemNew);
                }
                hero.setGoldAmount(hero.getGoldAmount() - item.getPrice()/2);
            }
        }
        heroService.updateHero(hero);
        heroRepository.save(hero);
        return "redirect:/items/upgrade/" + equipped + "/" + itemNew.getId();
    }
}
