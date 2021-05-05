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
import pl.coderslab.Projekt_RPG.project.hero.QuestRepository;
import pl.coderslab.Projekt_RPG.project.items.Item;
import pl.coderslab.Projekt_RPG.project.items.ItemService;
import pl.coderslab.Projekt_RPG.project.items.items.Armor;
import pl.coderslab.Projekt_RPG.project.items.items.Weapon;
import pl.coderslab.Projekt_RPG.project.monster.MonsterRepository;
import pl.coderslab.Projekt_RPG.project.monster.MonsterSession;
import pl.coderslab.Projekt_RPG.user.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reward")
public class RewardController {

    private final HeroRepository heroRepository;
    private final UserService userService;
    private final HeroService heroService;
    private final QuestRepository questRepository;
    private final ItemService itemService;
    private final MonsterSession monsterSession;
    private final MonsterRepository monsterRepository;

    public RewardController(MonsterRepository monsterRepository, MonsterSession monsterSession, ItemService itemService, HeroRepository heroRepository, UserService userService, HeroService heroService, QuestRepository questRepository) {
        this.heroRepository = heroRepository;
        this.userService = userService;
        this.heroService = heroService;
        this.questRepository = questRepository;
        this.itemService = itemService;
        this.monsterSession = monsterSession;
        this.monsterRepository = monsterRepository;
    }

    @GetMapping("/{from}/{id}")
    public String reward(@PathVariable String from,@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        int exp = 0;
        int gold = 0;
        List<Item> items = hero.getItem();
        switch (from) {
            case "monster":
                if(monsterSession.getRewardFromMonster().equals(id) && id > 0) {
                    exp = id.intValue() * 10;
                    gold = id.intValue() * 20;
                    Item item = itemService.getRandomItem(monsterRepository.getOne(id).getTier());
                    model.addAttribute("item",item);
                    items.add(item);
                    monsterSession.setRewardFromMonster(0L);
                    model.addAttribute("monsterId", id);
                }
                break;
            case "quest":
                if(hero.getMonsterKilled() >= 5) {
                    exp = id.intValue() * 200;
                    gold = id.intValue() * 1000;
                    Item item = itemService.getSuitableRandomItem(monsterRepository.getOne(id).getTier(),hero.getRace());
                    model.addAttribute("item",item);
                    items.add(item);
                    hero.setMonsterKilled(0L);
                    if(questRepository.findAll().size() != id) {
                        hero.setQuest(questRepository.getOne(id + 1));
                    }
                }
                break;
        }
//        hero.setItem(items);
        hero.setExperienceCurrent(hero.getExperienceCurrent() + exp);
        hero.setGoldAmount(hero.getGoldAmount() + gold);
        heroService.endFight(hero);
        while(hero.getExperienceCurrent() >= hero.getExperienceMax()) {
            heroService.levelUp(hero);
            heroService.updateHero(hero);
        }
        heroRepository.save(hero);
        model.addAttribute("exp", exp);
        model.addAttribute("gold", gold);
        model.addAttribute("hero", hero);
        model.addAttribute("lastMap", monsterSession.getLastMap());
        return "app/reward";
    }
}
