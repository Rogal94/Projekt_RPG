package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Projekt_RPG.project.*;
import pl.coderslab.Projekt_RPG.user.UserRepository;
import pl.coderslab.Projekt_RPG.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/items")
public class ItemsController {
    private final HeroRepository heroRepository;
    private final UserService userService;
    private final WeaponRepository weaponRepository;
    private final HeroService heroService;

    public ItemsController(HeroRepository heroRepository, UserService userService, WeaponRepository weaponRepository, HeroService heroService) {
        this.heroRepository = heroRepository;
        this.userService = userService;
        this.weaponRepository = weaponRepository;
        this.heroService = heroService;
    }

    @GetMapping("")
    public String items(@AuthenticationPrincipal UserDetails customUser, Model model) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        Weapon weapon = weaponRepository.getOne(hero.getEquipWeapon());
        List <Armor> armorList = new ArrayList<>();
        heroService.addEquipArmorToList(armorList, hero);
        model.addAttribute("hero", hero);
        model.addAttribute("weapon", weapon);
        model.addAttribute("armorList", armorList);
        return "app/items";
    }

    @GetMapping("/list/{type}")
    public String itemsList(@PathVariable String type, Model model, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        if(type.equals("weapon")) {
            List<Weapon> itemList = hero.getWeapon();
            model.addAttribute("itemList",itemList);
        }else {
            List<Armor> itemList = hero.getArmor().stream().filter(a->a.getType().equals(type)).collect(Collectors.toList());
            model.addAttribute("itemList",itemList);
        }
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
        if(points.equals("mana") && hero.getPotionMana() > 0) {
            hero.setManaPointsCurrent(hero.getManaPointsCurrent() + 100);
            if(hero.getManaPointsCurrent() > hero.getManaPointsMax()) {
                hero.setManaPointsCurrent(hero.getManaPointsMax());
            }
            hero.setPotionMana(hero.getPotionMana() - 1);
        }
        if(points.equals("stamina") && hero.getPotionStamina() > 0) {
            hero.setStaminaCurrent(hero.getStaminaCurrent() + 100);
            if(hero.getStaminaCurrent() > hero.getStaminaMax()) {
                hero.setStaminaCurrent(hero.getStaminaMax());
            }
            hero.setPotionStamina(hero.getPotionStamina() - 1);
        }
        heroRepository.save(hero);
        return "redirect:/items";
    }

    @GetMapping("/change/{type}/{id}")
    public String itemsChange(@PathVariable String type, @PathVariable Long id, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        switch(type) {
            case "weapon":
                hero.setEquipWeapon(id);
                break;
            case "helmet":
                hero.setEquipHelmet(id);
                break;
            case "chest":
                hero.setEquipChest(id);
                break;
            case "legs":
                hero.setEquipLegs(id);
                break;
            case "gloves":
                hero.setEquipGloves(id);
                break;
            case "boots":
                hero.setEquipBoots(id);
                break;
        }
        heroService.updateHero(hero);
        heroRepository.save(hero);
        return "redirect:/items";
    }
}
