package pl.coderslab.Projekt_RPG.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Projekt_RPG.project.*;

import java.io.File;
import java.io.FileNotFoundException;
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

    public DatabaseController(ObjectMapper objectMapper, WeaponRepository weaponRepository, ArmorRepository armorRepository, MonsterRepository monsterRepository, QuestRepository questRepository, SkillRepository skillRepository) {
        this.objectMapper = objectMapper;
        this.weaponRepository = weaponRepository;
        this.armorRepository = armorRepository;
        this.monsterRepository = monsterRepository;
        this.questRepository = questRepository;
        this.skillRepository = skillRepository;
    }

    @Value("${database.location}")
    private String filePath;

    @GetMapping("")
    public String showAdd() {
        return "database/add";
    }

    @PostMapping("/all")
    public String addAll() {
        addWeapons();
        addArmors();
        addSkills();
        addMonsters();
        addQuests();
        return "redirect:/";
    }


    @PostMapping("/weapons")
    public String addWeapons() {
        try(Scanner scanner = new Scanner(new File(filePath+"\\weapons.txt"))) {
            while (scanner.hasNext()) {
                try {
                    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    Weapon weapon = objectMapper.readValue(scanner.nextLine(), Weapon.class);
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
                    Armor armor = objectMapper.readValue(scanner.nextLine(), Armor.class);
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
                    Skill skill = objectMapper.readValue(scanner.nextLine(),Skill.class);
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
