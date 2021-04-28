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

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final HeroRepository heroRepository;
    private final ItemRepository itemRepository;
    private final UserService userService;
    private final WeaponRepository weaponRepository;
    private final ArmorRepository armorRepository;
    private final ItemService itemService;

    public ShopController(ItemService itemService, HeroRepository heroRepository, ItemRepository itemRepository, UserService userService, WeaponRepository weaponRepository, ArmorRepository armorRepository) {
        this.heroRepository = heroRepository;
        this.itemRepository = itemRepository;
        this.userService = userService;
        this.weaponRepository = weaponRepository;
        this.armorRepository = armorRepository;
        this.itemService = itemService;
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
            List<Weapon> weaponList = itemService.getWeaponFromItems(hero.getItem());
            List<Armor> armorList = itemService.getArmorFromItems(hero.getItem());
            weaponList.remove(weaponList.stream()
                    .filter(w->w.getId().equals(hero.getItemEquiped().get("weapon").getId()))
                    .findFirst().orElse(null));
            List<Armor> armorListToRemove = itemService.getArmorFromItems(new ArrayList<>(hero.getItemEquiped().values()));
            armorListToRemove.forEach(armorList::remove);
            model.addAttribute("weaponList",weaponList);
            model.addAttribute("armorList",armorList);

        }else if(transaction.equals("buy")){
            List<Weapon> weaponList = weaponRepository.findAll().stream()
                    .filter(w->w.getAttack() > 0 && w.getAttack() < 40)
                    .filter(w->w.getId() %2 == 0 && w.getId() < 23L)
                    .collect(Collectors.toList());
            List<Armor> armorList = armorRepository.findAll().stream()
                    .filter(w->w.getDefence() > 0 && w.getDefence() < 40)
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
                if(hero.getGoldAmount() >= 100) {
                    hero.setPotionHealth(hero.getPotionHealth() + 1);
                    hero.setGoldAmount(hero.getGoldAmount() - 100);
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
                    itemList.add(weaponRepository.getOne(id));
                    hero.setGoldAmount(hero.getGoldAmount() - item.getPrice());
                }
                break;
            case "sell":
                itemList.remove(weaponRepository.getOne(id));
                hero.setGoldAmount(hero.getGoldAmount() + item.getPrice()/5);
                break;
        }
        hero.setItem(itemList);
        heroRepository.save(hero);
        return "redirect:/shop/list/" + transaction;
    }

//    @GetMapping("/{transaction}/armor/{id}")
//    public String shopBuyArmor(@AuthenticationPrincipal UserDetails customUser, @PathVariable String transaction, @PathVariable Long id) {
//        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
//        Armor armor = armorRepository.getOne(id);
//        List <Armor> armorList = hero.getArmor();
//        switch (transaction) {
//            case "buy":
//                if(hero.getGoldAmount() >= armor.getPrice()) {
//                    armorList.add(armorRepository.getOne(id));
//                    hero.setGoldAmount(hero.getGoldAmount() - armor.getPrice());
//                }
//                break;
//            case "sell":
//                armorList.remove(armorRepository.getOne(id));
//                hero.setGoldAmount(hero.getGoldAmount() + armor.getPrice()/5);
//                break;
//        }
//        hero.setArmor(armorList);
//        heroRepository.save(hero);
//        return "redirect:/shop/list/" + transaction;
//    }
}
