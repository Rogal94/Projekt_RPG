package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.Projekt_RPG.project.*;
import pl.coderslab.Projekt_RPG.user.UserService;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/monsters")
public class MonsterController {
    private final MonsterSession monsterSession;
    private final MonsterRepository monsterRepository;
    private final HeroRepository heroRepository;
    private final UserService userService;
    private final HeroService heroService;
    private final SkillRepository skillRepository;

    public MonsterController(MonsterRepository monsterRepository, HeroRepository heroRepository, UserService userService, MonsterSession monsterSession, HeroService heroService, SkillRepository skillRepository) {
        this.monsterRepository = monsterRepository;
        this.heroRepository = heroRepository;
        this.userService = userService;
        this.monsterSession = monsterSession;
        this.heroService = heroService;
        this.skillRepository = skillRepository;
    }

    @GetMapping("")
    public String monsters(Model model) {
        model.addAttribute("monsterList", monsterRepository.findAll());
        monsterSession.setId(null);
        return "app/monsters";
    }

    @GetMapping("/fight/{id}/start")
    public String monstersFightStart(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        Monster monster = monsterRepository.getOne(id);
        if(monsterSession.getId() == null && hero.getStaminaCurrent() >= 10) {
            monsterSession.setId(monster.getId());
            monsterSession.setHealthPointsCurrent(monster.getHealthPointsMax());
            hero.setStaminaCurrent(hero.getStaminaCurrent()-10);
            heroRepository.save(hero);
        }else if(!monsterSession.getId().equals(id)) {
            return "redirect:/monsters";
        }

        if(monsterSession.getHealthPointsCurrent() <= 0) {
            monsterSession.setId(null);
            hero.setReward(id);
            if(hero.getQuest().getMonsterName().equals(monster.getName())){
                hero.setMonsterKilled(hero.getMonsterKilled()+1);
            }
            heroRepository.save(hero);
            return "redirect:/reward/monster/" + id;
        }else if(hero.getHealthPointsCurrent() <= 0) {
            monsterSession.setId(null);
            return "redirect:/character/died";
        }

        model.addAttribute("hero", hero);
        model.addAttribute("monster", monster);
        model.addAttribute("monsterSession", monsterSession);
        return "app/monstersFight";
    }

    @GetMapping("/fight/{id}/{coordinates}")
    public String monstersFightAttack(@PathVariable Long id, @AuthenticationPrincipal UserDetails customUser, @PathVariable List<Double> coordinates) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        String result = heroService.checkCoordinates(coordinates);
        switch (result) {
            case "skill1":
                Optional<Skill> skill = hero.getSkill().stream().filter(s->s.getName().equals("FireBall") || s.getName().equals("Whirlwind")).findFirst();
                if(skill.isPresent()) {
                    Long skillId = skill.get().getId();
                    return "redirect:skill?skillId=" + skillId;
                }
                return "redirect:start";
            case "attack":
                return "redirect:attack";
            default:
                return "redirect:start";
        }
    }

    @GetMapping("/fight/{id}/attack")
    public String monstersFightAttack(@PathVariable Long id, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        Monster monster = monsterRepository.getOne(id);

        heroService.attack(hero, monster, 0);
        heroRepository.save(hero);

        return "redirect:start";
    }

    @GetMapping("/fight/{id}/skill")
    public String monstersFightSkill(@PathVariable Long id, @RequestParam Long skillId, @AuthenticationPrincipal UserDetails customUser) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        Monster monster = monsterRepository.getOne(id);
        if(hero.getSkill().contains(skillRepository.getOne(skillId))) {
            Integer skillDmg = skillRepository.getOne(skillId).getSkillAttack();
            if(hero.getManaPointsCurrent() >= 100) {
                heroService.attack(hero, monster, skillDmg);
                hero.setManaPointsCurrent(hero.getManaPointsCurrent() - 100);
            }
            heroRepository.save(hero);
        }
        return "redirect:start";
    }
}
