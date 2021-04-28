package pl.coderslab.Projekt_RPG.project.hero;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_RPG.project.hero.races.*;
import pl.coderslab.Projekt_RPG.project.items.Item;
import pl.coderslab.Projekt_RPG.project.items.ItemRepository;
import pl.coderslab.Projekt_RPG.project.items.items.Armor;
import pl.coderslab.Projekt_RPG.project.items.items.ArmorRepository;
import pl.coderslab.Projekt_RPG.project.items.items.Weapon;
import pl.coderslab.Projekt_RPG.project.items.items.WeaponRepository;
import pl.coderslab.Projekt_RPG.project.monster.Monster;
import pl.coderslab.Projekt_RPG.project.monster.MonsterSession;
import pl.coderslab.Projekt_RPG.user.User;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HeroServiceImpl implements HeroService{
    private final QuestRepository questRepository;
    private final ItemRepository itemRepository;
    private final HeroRepository heroRepository;
    private final MonsterSession monsterSession;
    private final RaceRepository raceRepository;
    private RaceService raceService;

    public HeroServiceImpl(QuestRepository questRepository, HeroRepository heroRepository, MonsterSession monsterSession,RaceRepository raceRepository,ItemRepository itemRepository) {
        this.questRepository = questRepository;
        this.heroRepository = heroRepository;
        this.monsterSession = monsterSession;
        this.raceRepository = raceRepository;
        this.itemRepository = itemRepository;
    }
    @Autowired
    BeanFactory beans;

    private void getRaceBean(Hero hero) {
        List<Race> raceList = raceRepository.findAll();
        for (Race race : raceList) {
            if(hero.getRace().getName().equals(race.getName())){
                raceService = beans.getBean(race.getName(), RaceService.class);
            }
        }
    }

    @Override
    public void createHero(Hero hero) {
        hero.setLevel(1);
        hero.setStatisticPoints(0);
        hero.setSkillPoints(1);
        hero.setMainStat(16);
        hero.setVitality(14);
        hero.setGoldAmount(500);
        hero.setMonsterKilled(0L);
        hero.setStaminaMax(200);
        hero.setStaminaCurrent(hero.getStaminaMax());
        hero.setQuest(questRepository.getOne(1L));
        Map<String,Item> itemMap = new TreeMap<>();
        itemMap.put("weapon",itemRepository.findByName("Small Axe"));
        List<Item> itemList = new ArrayList<>(itemMap.values());
        itemMap.put("helmet",itemRepository.findByName("helmet"));
        itemMap.put("armor",itemRepository.findByName("armor"));
        itemMap.put("pants",itemRepository.findByName("pants"));
        itemMap.put("gloves",itemRepository.findByName("gloves"));
        itemMap.put("boots",itemRepository.findByName("boots"));
        hero.setItemEquiped(itemMap);
        hero.setItem(itemList);
        hero.setPotionHealth(5);
        hero.setPotionStamina(2);
        hero.setExperienceCurrent(0);
        hero.setExperienceMax(hero.getLevel()*100);

        getRaceBean(hero);
        raceService.create(hero);
        hero.setHealthPointsCurrent(hero.getHealthPointsMax());
    }

    @Override
    public void updateHero(Hero hero) {
        getRaceBean(hero);
        raceService.update(hero);
    }

    @Override
    public boolean checkHero(Long heroId, User user) {
        return heroRepository.getOne(heroId).getUser() == user;
    }
//    @Override
//    public void addEquipArmorToList(List<Armor> list, Hero hero) {
//        list.add(armorRepository.getOne(hero.getEquipHelmet()));
//        list.add(armorRepository.getOne(hero.getEquipChest()));
//        list.add(armorRepository.getOne(hero.getEquipLegs()));
//        list.add(armorRepository.getOne(hero.getEquipGloves()));
//        list.add(armorRepository.getOne(hero.getEquipBoots()));
//    }

//    @Override
//    public void removeEquippedArmor(List<Armor> armorList, Hero hero) {
//        List<Long> idList = Arrays.asList(hero.getEquipHelmet(), hero.getEquipChest(), hero.getEquipLegs(), hero.getEquipGloves(), hero.getEquipBoots());
//        for (Long id : idList) {
//            armorList.remove(armorRepository.getOne(id));
//        }
//    }

    public void checkMax (Hero hero) {
        if(hero.getSecPointsCurrent()>hero.getSecPointsMax()) {
            hero.setSecPointsCurrent(hero.getSecPointsMax());
        }
        if(hero.getHealthPointsCurrent()>hero.getHealthPointsMax()) {
            hero.setHealthPointsCurrent(hero.getHealthPointsMax());
        }
    }

    @Override
    public void attack(Hero hero, Monster monster) {
        getRaceBean(hero);
        raceService.attack(hero, monster, monsterSession.isSpecialAttack());
        checkMax(hero);
    }

    @Override
    public void attackSkill(Hero hero, Monster monster, Skill skill) {
        getRaceBean(hero);
        raceService.attackSkill(hero,monster,skill, monsterSession.isSpecialAttack());
        checkMax(hero);
    }

    @Override
    public void endFight(Hero hero) {
        getRaceBean(hero);
        raceService.endFight(hero);
    }

    @Override
    public void levelUp(Hero hero) {
        hero.setExperienceCurrent(hero.getExperienceCurrent()-hero.getExperienceMax());
        hero.setLevel(hero.getLevel() + 1);
        hero.setStatisticPoints(hero.getStatisticPoints() + 2);
        if(hero.getLevel() % 5 == 0) {
            hero.setSkillPoints(hero.getSkillPoints() + 1);
        }
    }

    @Override
    public String checkCoordinates(List<Double> list) {
        double xStart = list.get(0);
        double yStart = list.get(1);
        double xEnd = list.get(2);
        double yEnd = list.get(3);

        if(Math.abs(xStart - xEnd) < 40 && Math.abs(yStart - yEnd) < 40) {
            return "skill1";
        }else if(Math.abs(xStart - xEnd) < 40 && Math.abs(yStart - yEnd) > 150) {
            return "attack";
        }else{
            return "fail";
        }
    }
}
