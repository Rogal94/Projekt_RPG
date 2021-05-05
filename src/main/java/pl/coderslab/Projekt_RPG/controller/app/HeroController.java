package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Projekt_RPG.project.hero.*;
import pl.coderslab.Projekt_RPG.project.hero.races.Race;
import pl.coderslab.Projekt_RPG.project.hero.races.RaceRepository;
import pl.coderslab.Projekt_RPG.user.User;
import pl.coderslab.Projekt_RPG.user.UserRepository;
import pl.coderslab.Projekt_RPG.user.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/character")
public class HeroController {
    private final HeroRepository heroRepository;
    private final HeroService heroService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final RaceRepository raceRepository;

    public HeroController(HeroRepository heroRepository, HeroService heroService, UserService userService, UserRepository userRepository, SkillRepository skillRepository,RaceRepository raceRepository) {
        this.heroRepository = heroRepository;
        this.heroService = heroService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.raceRepository = raceRepository;
    }

    @GetMapping("")
    public String charPanel(@AuthenticationPrincipal UserDetails customUser, Model model) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        model.addAttribute("hero", hero);
        return "app/char";
    }

    @GetMapping("/char")
    public String character(@AuthenticationPrincipal UserDetails customUser, Model model) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        List<Skill> buffSkill = skillRepository.findAllByRaceAndType(hero.getRace(),"buff");
        List<Skill> damageSkill = skillRepository.findAllByRaceAndType(hero.getRace(),"damage");
        model.addAttribute("buffSkill", buffSkill);
        model.addAttribute("damageSkill", damageSkill);
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
        List<Race> raceList = raceRepository.findAll();
        model.addAttribute("hero", hero);
        model.addAttribute("raceList", raceList);
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
        return "redirect:/character/char";
    }

    @GetMapping("/skill/add")
    public String charSkillAddList(@AuthenticationPrincipal UserDetails customUser, Model model) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        List <Skill> skillList = skillRepository.findAllByRaceAndSkillRank(hero.getRace(),1);
        skillList.removeIf(skill -> hero.getSkill().containsKey(skill.getType()));
        List<Skill> buffSkill = skillRepository.findAllByRaceAndType(hero.getRace(),"buff");
        List<Skill> damageSkill = skillRepository.findAllByRaceAndType(hero.getRace(),"damage");
        model.addAttribute("buffSkill", buffSkill);
        model.addAttribute("damageSkill", damageSkill);
        model.addAttribute("hero", hero);
        model.addAttribute("skillList", skillList);
        return "app/charSkillList";
    }

    @GetMapping("/skill/add/{skillId}")
    public String charSkillAdd(@AuthenticationPrincipal UserDetails customUser, @PathVariable Long skillId) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        if(hero.getSkillPoints()>0) {
            Map<String,Skill> skillMap = hero.getSkill();
            Skill skillToAdd = skillRepository.getOne(skillId);
            Skill skill = skillMap.get(skillToAdd.getType());
            if(skill.getSkillRank()<5 && skill.getName().equals(skillToAdd.getName())) {
                skillToAdd = skillRepository.findByNameAndSkillRank(skill.getName(),skill.getSkillRank()+1);
                skillMap.put(skillToAdd.getType(),skillToAdd);
//                hero.setSkill(skillMap);
                hero.setSkillPoints(hero.getSkillPoints() -1);
            }
        }
        heroRepository.save(hero);
        return "redirect:/character/char";
    }

    @GetMapping("/skill/add/{skillId}/new")
    public String charSkillAddNew(@AuthenticationPrincipal UserDetails customUser, @PathVariable Long skillId) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        if(hero.getSkillPoints()>0) {
            Skill skillToAdd = skillRepository.getOne(skillId);
            if(!hero.getSkill().containsKey(skillToAdd.getType()) && skillRepository.findAllByRace(hero.getRace()).contains(skillToAdd)) {
                Map<String,Skill> skillMap = hero.getSkill();
                skillMap.put(skillToAdd.getType(), skillToAdd);
//                hero.setSkill(skillMap);
                hero.setSkillPoints(hero.getSkillPoints() - 1);
            }
        }
        heroRepository.save(hero);
        return "redirect:/character/char";
    }

    @GetMapping("/died")
    public String charDied(@AuthenticationPrincipal UserDetails customUser, Model model) {
        Hero hero = heroRepository.getOne(userService.findByUserName(customUser.getUsername()).getLoggedHero());
        if(hero.getHealthPointsCurrent() <= 0) {
            hero.setHealthPointsCurrent(hero.getHealthPointsMax()/10);
            hero.setExperienceCurrent(0);
            hero.setStaminaCurrent(0);
            heroService.endFight(hero);
            heroRepository.save(hero);
        }
        model.addAttribute("died", true);
        model.addAttribute("hero", hero);
        return "app/character";
    }
}
