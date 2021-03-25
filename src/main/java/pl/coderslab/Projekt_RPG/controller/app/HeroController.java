package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Projekt_RPG.project.*;
import pl.coderslab.Projekt_RPG.user.User;
import pl.coderslab.Projekt_RPG.user.UserRepository;
import pl.coderslab.Projekt_RPG.user.UserService;

import java.util.List;

@Controller
@RequestMapping("/character")
public class HeroController {
    private final HeroRepository heroRepository;
    private final HeroService heroService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    public HeroController(HeroRepository heroRepository, HeroService heroService, UserService userService, UserRepository userRepository, SkillRepository skillRepository) {
        this.heroRepository = heroRepository;
        this.heroService = heroService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
    }

    @GetMapping("")
    public String character(@AuthenticationPrincipal UserDetails customUser, Model model) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        model.addAttribute("hero", hero);
        return "app/character";
    }

    @GetMapping("/list")
    public String charList(Model model, @AuthenticationPrincipal UserDetails customUser) {
        User user = userService.findByUserName(customUser.getUsername());
        user.setLoggedHero(0L);
        userRepository.save(user);
        List<Hero> heroList = heroRepository.findAllByUserId(user.getId());
        model.addAttribute(heroList);
        return "app/charList";
    }

    @GetMapping("/create")
    public String charCreate(Model model) {
        Hero hero = new Hero();
        model.addAttribute("hero", hero);
        return "app/charCreate";
    }

    @PostMapping("/create")
    public String charCreatePost(@AuthenticationPrincipal UserDetails customUser, Hero hero) {
        hero.setUser(userService.findByUserName(customUser.getUsername()));
        heroService.createHero(hero);
        heroRepository.save(hero);
        return "redirect:/character/list";
    }

    @GetMapping("/stat/add/{stat}")
    public String charStatAdd(@AuthenticationPrincipal UserDetails customUser, @PathVariable String stat) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        if(hero.getStatisticPoints()>0) {
            if(stat.equals("main")) {
                hero.setMainStat(hero.getMainStat() + 1);
                hero.setStatisticPoints(hero.getStatisticPoints() -1);
            }
            else if(stat.equals("vit")) {
                hero.setVitality(hero.getVitality() + 1);
                hero.setStatisticPoints(hero.getStatisticPoints() -1);
            }
            heroService.updateHero(hero);
            heroRepository.save(hero);
        }
        return "redirect:/character";
    }

    @GetMapping("/stat/details")
    public String charStatDetails() {
        return "app/charStatDetails";
    }

    @GetMapping("/skill/add")
    public String charSkillAddList(@AuthenticationPrincipal UserDetails customUser, Model model) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        List <Skill> skillList = skillRepository.findAllBySkillForAndSkillRank(hero.getRace(), 1);
        for(Skill elem : hero.getSkill()) {
            skillList.removeAll(skillRepository.findAllByName(elem.getName()));
        }
        model.addAttribute("hero", hero);
        model.addAttribute("skillList", skillList);
        return "app/charSkillList";
    }

    @GetMapping("/skill/add/{skillId}")
    public String charSkillAdd(@AuthenticationPrincipal UserDetails customUser, @PathVariable Long skillId) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        Skill skill = skillRepository.getOne(skillId);
        if(hero.getSkillPoints()>0) {
            if(skill.getSkillRank() == 1) {
                hero.getSkill().add(skillRepository.getOne(skillId));
            }else if(skill.getSkillRank()<=5) {
                hero.getSkill().add(skillRepository.getOne(skillId));
                hero.getSkill().remove(skillRepository.getOne(skillId - 1));
            }
            hero.setSkillPoints(hero.getSkillPoints() -1);
        }
        heroRepository.save(hero);
        return "redirect:/character";
    }

    @GetMapping("/skill/details/{skillId}")
    public String charSkillDetails(@PathVariable Long skillId, Model model) {
        Skill skill = skillRepository.getOne(skillId);
        List <Skill> skillList = skillRepository.findAllByName(skill.getName());
        model.addAttribute("skill", skill);
        model.addAttribute("skillList", skillList);
        return "app/charSkillDetails";
    }

    @GetMapping("/died")
    public String charDied(@AuthenticationPrincipal UserDetails customUser, Model model) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        if(hero.getHealthPointsCurrent() <= 0) {
            hero.setHealthPointsCurrent(hero.getHealthPointsMax()/10);
            hero.setExperienceCurrent(0);
            hero.setStaminaCurrent(0);
            heroRepository.save(hero);
        }
        model.addAttribute("died", true);
        model.addAttribute("hero", hero);
        return "app/character";
    }
}
