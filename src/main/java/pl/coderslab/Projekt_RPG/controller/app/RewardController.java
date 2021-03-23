package pl.coderslab.Projekt_RPG.controller.app;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Projekt_RPG.project.*;
import pl.coderslab.Projekt_RPG.user.UserService;

import java.util.List;

@Controller
@RequestMapping("/reward")
public class RewardController {

    private final HeroRepository heroRepository;
    private final UserService userService;
    private final HeroService heroService;
    private final QuestRepository questRepository;

    public RewardController(HeroRepository heroRepository, UserService userService, HeroService heroService, QuestRepository questRepository) {
        this.heroRepository = heroRepository;
        this.userService = userService;
        this.heroService = heroService;
        this.questRepository = questRepository;
    }

    @GetMapping("/{from}/{id}")
    public String reward(@PathVariable String from,@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        int exp = 0;
        int gold = 0;
        switch (from) {
            case "monster":
                if(hero.getReward().equals(id) && id > 0) {
                    exp = id.intValue() * 10;
                    gold = id.intValue() * 20;
                    String random = heroService.randomItem();
                    if(random.equals("weapon")) {
                        List<Weapon> weapons = hero.getWeapon();
                        if(id < 6) {
                            weapons.add(heroService.getRandomWeapon(1));
                        }else if(id < 12) {
                            weapons.add(heroService.getRandomWeapon(2));
                        }else {
                            weapons.add(heroService.getRandomWeapon(3));
                        }
                        model.addAttribute("item",weapons.get(weapons.size()-1));
                        hero.setWeapon(weapons);
                    }else if(random.equals("armor")) {
                        List<Armor> armors = hero.getArmor();
                        if(id < 6) {
                            armors.add(heroService.getRandomArmor(1));
                        }else if(id < 12) {
                            armors.add(heroService.getRandomArmor(2));
                        }else {
                            armors.add(heroService.getRandomArmor(3));
                        }
                        model.addAttribute("item",armors.get(armors.size()-1));
                        hero.setArmor(armors);
                    }
                    hero.setReward(0L);
                }
                break;
            case "quest":
                if(hero.getMonsterKilled() >= 5) {
                    exp = id.intValue() * 200;
                    gold = id.intValue() * 1000;
                    String random = heroService.randomItem();
                    if(random.equals("weapon")) {
                        List<Weapon> weapons = hero.getWeapon();
                        if(id < 3) {
                            weapons.add(heroService.getRandomWeapon(2));
                        }else if(id < 6) {
                            weapons.add(heroService.getRandomWeapon(3));
                        }else {
                            weapons.add(heroService.getRandomWeapon(4));
                        }
                        model.addAttribute("item",weapons.get(weapons.size()-1));
                        hero.setWeapon(weapons);
                    }else if(random.equals("armor")) {
                        List<Armor> armors = hero.getArmor();
                        if(id < 3) {
                            armors.add(heroService.getRandomArmor(2));
                        }else if(id < 6) {
                            armors.add(heroService.getRandomArmor(3));
                        }else {
                            armors.add(heroService.getRandomArmor(4));
                        }
                        model.addAttribute("item",armors.get(armors.size()-1));
                        hero.setArmor(armors);
                    }
                    hero.setMonsterKilled(0L);
                    hero.setQuest(questRepository.getOne(id + 1));
                }
                break;
        }
        hero.setExperienceCurrent(hero.getExperienceCurrent() + exp);
        hero.setGoldAmount(hero.getGoldAmount() + gold);
        while(hero.getExperienceCurrent() > hero.getExperienceMax()) {
            heroService.levelUp(hero);
            heroService.updateHero(hero);
        }
        heroRepository.save(hero);
        model.addAttribute("exp", exp);
        model.addAttribute("gold", gold);
        return "app/reward";
    }
}
