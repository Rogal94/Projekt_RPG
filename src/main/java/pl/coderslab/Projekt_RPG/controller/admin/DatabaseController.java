package pl.coderslab.Projekt_RPG.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Projekt_RPG.project.hero.Quest;
import pl.coderslab.Projekt_RPG.project.hero.QuestRepository;
import pl.coderslab.Projekt_RPG.project.hero.Skill;
import pl.coderslab.Projekt_RPG.project.hero.SkillRepository;
import pl.coderslab.Projekt_RPG.project.hero.races.Race;
import pl.coderslab.Projekt_RPG.project.hero.races.RaceRepository;
import pl.coderslab.Projekt_RPG.project.items.Item;
import pl.coderslab.Projekt_RPG.project.items.ItemRepository;
import pl.coderslab.Projekt_RPG.project.items.items.Armor;
import pl.coderslab.Projekt_RPG.project.items.items.ArmorRepository;
import pl.coderslab.Projekt_RPG.project.items.items.Weapon;
import pl.coderslab.Projekt_RPG.project.items.items.WeaponRepository;
import pl.coderslab.Projekt_RPG.project.monster.Monster;
import pl.coderslab.Projekt_RPG.project.monster.MonsterRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//File fileRes = new ClassPathResource("database/quests.txt").getFile();   file z resource
//File fileWeb = resourceLoader.getResource("/database/quests.txt").getFile();   file z webapp
//@PropertySource("classpath:application.properties")

@Controller
@RequestMapping("/add")
public class DatabaseController {

    private final ObjectMapper objectMapper;
    private final WeaponRepository weaponRepository;
    private final ArmorRepository armorRepository;
    private final MonsterRepository monsterRepository;
    private final QuestRepository questRepository;
    private final SkillRepository skillRepository;
    private final RaceRepository raceRepository;

    public DatabaseController(ObjectMapper objectMapper, WeaponRepository weaponRepository, ArmorRepository armorRepository, MonsterRepository monsterRepository, QuestRepository questRepository, SkillRepository skillRepository, RaceRepository raceRepository) {
        this.objectMapper = objectMapper;
        this.weaponRepository = weaponRepository;
        this.armorRepository = armorRepository;
        this.monsterRepository = monsterRepository;
        this.questRepository = questRepository;
        this.skillRepository = skillRepository;
        this.raceRepository = raceRepository;
    }

    @Value("${database.location}")
    private String filePath;

    @GetMapping("")
    public String showAdd() {
        return "database/add";
    }

    @PostMapping("/all")
    public String addAll() {
        addRaces();
        addMonsters();
        addQuests();
        addWeapons();
        addArmors();
        addSkills();
        return "redirect:/";
    }


    @PostMapping("/races")
    public String addRaces() {
        try(Scanner scanner = new Scanner(new File(filePath+"\\races.txt"))) {
            while (scanner.hasNext()) {
                try {
                    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    Race race = objectMapper.readValue(scanner.nextLine(), Race.class);
                    raceRepository.save(race);
                }catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @PostMapping("/weapons")
    public String addWeapons() {
        try(Scanner scanner = new Scanner(new File(filePath+"\\weapons.txt"))) {
            while (scanner.hasNext()) {
                try {
                    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    String line = scanner.nextLine();
                    Weapon weapon = objectMapper.readValue(line, Weapon.class);
                    Map<String,String> map = objectMapper.readValue(line,Map.class);
                    List<Race> raceList = new ArrayList<>();
                    String[] raceTable = map.get("itemFor").split(",");
                    for (String raceName : raceTable) {
                        Race race = raceRepository.findByName(raceName);
                        raceList.add(race);
                    }
                    weapon.setRace(raceList);
                    weaponRepository.save(weapon);
                }catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @PostMapping("/armors")
    public String addArmors() {
        try(Scanner scanner = new Scanner(new File(filePath+"\\armors.txt"))) {
            while (scanner.hasNext()) {
                try {
                    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    String line = scanner.nextLine();
                    Armor armor = objectMapper.readValue(line,Armor.class);
                    Map<String,String> map = objectMapper.readValue(line,Map.class);
                    List<Race> raceList = new ArrayList<>();
                    String[] raceTable = map.get("itemFor").split(",");
                    for (String raceName : raceTable) {
                        Race race = raceRepository.findByName(raceName);
                        raceList.add(race);
                    }
                    armor.setRace(raceList);
                    armorRepository.save(armor);
                }catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @PostMapping("/monsters")
    public String addMonsters() {
        try(Scanner scanner = new Scanner(new File(filePath+"\\monsters.txt"))) {
            while (scanner.hasNext()) {
                try {
                    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    Monster monster = objectMapper.readValue(scanner.nextLine(),Monster.class);
                    monsterRepository.save(monster);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @PostMapping("/quests")
    public String addQuests() {
        try(Scanner scanner = new Scanner(new File(filePath+"\\quests.txt"))) {
            while (scanner.hasNext()) {
                try {
                    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    Quest quest = objectMapper.readValue(scanner.nextLine(),Quest.class);
                    questRepository.save(quest);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @PostMapping("/skills")
    public String addSkills() {
        try(Scanner scanner = new Scanner(new File(filePath+"\\skills.txt"))) {
            while (scanner.hasNext()) {
                try {
                    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    String line = scanner.nextLine();
                    Skill skill = objectMapper.readValue(line,Skill.class);
                    Map<String,String> map = objectMapper.readValue(line,Map.class);
                    Race race = raceRepository.findByName(map.get("skillFor"));
                    skill.setRace(race);
                    skillRepository.save(skill);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
