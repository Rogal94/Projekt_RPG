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

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final HeroRepository heroRepository;
    private final HeroService heroService;
    private final UserService userService;
    private final WeaponRepository weaponRepository;
    private final ArmorRepository armorRepository;

    public ShopController(HeroRepository heroRepository, HeroService heroService, UserService userService, WeaponRepository weaponRepository, ArmorRepository armorRepository) {
        this.heroRepository = heroRepository;
        this.heroService = heroService;
        this.userService = userService;
        this.weaponRepository = weaponRepository;
        this.armorRepository = armorRepository;
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
            List<Weapon> weaponList = hero.getWeapon();
            List<Armor> armorList = hero.getArmor();
            weaponList.remove(weaponList.stream()
                    .filter(w->w.getId().equals(hero.getEquipWeapon()))
                    .findFirst().orElse(null));
            armorList.removeAll(armorList.stream()
                    .distinct()
                    .filter(w->heroService.isArmorEquipped(w,hero))
                    .collect(Collectors.toList()));
            model.addAttribute("weaponList",weaponList);
            model.addAttribute("armorList",armorList);

        }else if(transaction.equals("buy")){
            List<Weapon> weaponList = weaponRepository.findAll().stream()
                    .filter(w->w.getAttack() > 0 && w.getAttack() < 40)
                    .filter(w->w.getId() %2 == 0 && w.getId() < 23L)
                    .collect(Collectors.toList());
            List<Armor> armorList = armorRepository.findAll().stream()
                    .filter(w->w.getDefense() > 0 && w.getDefense() < 40)
                    .filter(w->w.getId() %2 == 1)
                    .collect(Collectors.toList());
            model.addAttribute("weaponList",weaponList);
            model.addAttribute("armorList",armorList);
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
                if(hero.getGoldAmount() > 100) {
                    hero.setPotionHealth(hero.getPotionHealth() + 1);
                    hero.setGoldAmount(hero.getGoldAmount() - 100);
                }
                break;
            case "mana":
                if(hero.getGoldAmount() > 100) {
                    hero.setPotionMana(hero.getPotionMana() + 1);
                    hero.setGoldAmount(hero.getGoldAmount() - 100);
                }
                break;
            case "stamina":
                if(hero.getGoldAmount() > 2000) {
                    hero.setPotionStamina(hero.getPotionStamina() + 1);
                    hero.setGoldAmount(hero.getGoldAmount() - 2000);
                }
                break;
        }
        heroRepository.save(hero);
        return "redirect:/shop";
    }

    @GetMapping("/{transaction}/weapon/{id}")
    public String shopBuyWeapon(@AuthenticationPrincipal UserDetails customUser, @PathVariable String transaction, @PathVariable Long id) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        Weapon weapon = weaponRepository.getOne(id);
        List <Weapon> weaponList = hero.getWeapon();
        switch (transaction) {
            case "buy":
                if(hero.getGoldAmount() > weapon.getPrice()) {
                    weaponList.add(weaponRepository.getOne(id));
                    hero.setGoldAmount(hero.getGoldAmount() - weapon.getPrice());
                }
                break;
            case "sell":
                weaponList.remove(weaponRepository.getOne(id));
                hero.setGoldAmount(hero.getGoldAmount() + weapon.getPrice()/5);
                break;
        }
        hero.setWeapon(weaponList);
        heroRepository.save(hero);
        return "redirect:/shop";
    }

    @GetMapping("/{transaction}/armor/{id}")
    public String shopBuyArmor(@AuthenticationPrincipal UserDetails customUser, @PathVariable String transaction, @PathVariable Long id) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        Armor armor = armorRepository.getOne(id);
        List <Armor> armorList = hero.getArmor();
        switch (transaction) {
            case "buy":
                if(hero.getGoldAmount() > armor.getPrice()) {
                    armorList.add(armorRepository.getOne(id));
                    hero.setGoldAmount(hero.getGoldAmount() - armor.getPrice());
                }
                break;
            case "sell":
                armorList.remove(armorRepository.getOne(id));
                hero.setGoldAmount(hero.getGoldAmount() + armor.getPrice()/5);
                break;
        }
        hero.setArmor(armorList);
        heroRepository.save(hero);
        return "redirect:/shop";
    }
}
