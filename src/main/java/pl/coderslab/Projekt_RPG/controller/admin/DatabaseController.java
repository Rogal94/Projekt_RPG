package pl.coderslab.Projekt_RPG.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.Projekt_RPG.project.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

@Controller
@RequestMapping("/add")
public class DatabaseController {
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ObjectMapper objectMapper;

    private final WeaponRepository weaponRepository;
    private final ArmorRepository armorRepository;
    private final MonsterRepository monsterRepository;
    private final QuestRepository questRepository;

    public DatabaseController(WeaponRepository weaponRepository, ArmorRepository armorRepository, MonsterRepository monsterRepository, QuestRepository questRepository) {
        this.weaponRepository = weaponRepository;
        this.armorRepository = armorRepository;
        this.monsterRepository = monsterRepository;
        this.questRepository = questRepository;
    }

//    @GetMapping("/weapons")
//    @ResponseBody
//    public String addWeapons() {
//        try(Scanner scanner = new Scanner(new File("/database/weapons.txt"))) {
//            while (scanner.hasNext()) {
//                try {
//                    Weapon weapon = objectMapper.readValue(scanner.nextLine(), Weapon.class);
//                    weaponRepository.save(weapon);
//                }catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
//            }
//        }catch(FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return "Weapons add success";
//    }

//    @GetMapping("/armors")
//    @ResponseBody
//    public String addArmors() {
//        try(Scanner scanner = new Scanner(new File("/database/armors.txt"))) {
//            while (scanner.hasNext()) {
//                try {
//                    Armor armor = objectMapper.readValue(scanner.nextLine(), Armor.class);
//                    armorRepository.save(armor);
//                }catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
//            }
//        }catch(FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return "Armors add success";
//    }

//    @GetMapping("/monsters")
//    @ResponseBody
//    public String addMonsters() {
//        try(Scanner scanner = new Scanner(new File("/database/monsters.txt"))) {
//            while (scanner.hasNext()) {
//                try {
//                    Monster monster = objectMapper.readValue(scanner.nextLine(), Monster.class);
//                    monsterRepository.save(monster);
//                }catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
//            }
//        }catch(FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return "Monsters add success";
//    }

    @GetMapping("/quests")
    @ResponseBody
    public String addQuests() {
        try{
            File fileRes = new ClassPathResource("database/quests.txt").getFile();
            File fileWeb = resourceLoader.getResource("/database/quests.txt").getFile();
            try(Scanner scanner = new Scanner(fileRes)) {
                while (scanner.hasNext()) {
                    try {
                        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                        Quest quest = objectMapper.readValue(scanner.nextLine(),Quest.class);
                        System.out.println(quest.getName());
                        questRepository.save(quest);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }catch(FileNotFoundException e) {
                e.printStackTrace();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        return "Quests add success";
    }
}
