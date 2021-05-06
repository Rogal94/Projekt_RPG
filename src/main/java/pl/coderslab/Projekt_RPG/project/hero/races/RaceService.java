package pl.coderslab.Projekt_RPG.project.hero.races;

import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_RPG.project.hero.Hero;
import pl.coderslab.Projekt_RPG.project.hero.Skill;
import pl.coderslab.Projekt_RPG.project.hero.SkillRepository;
import pl.coderslab.Projekt_RPG.project.items.ItemService;
import pl.coderslab.Projekt_RPG.project.items.items.*;
import pl.coderslab.Projekt_RPG.project.monster.Monster;
import pl.coderslab.Projekt_RPG.project.monster.MonsterSession;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class RaceService {

    private final ItemService itemService;
    protected final MonsterSession monsterSession;
    private final SkillRepository skillRepository;

    public RaceService(MonsterSession monsterSession, ItemService itemService, SkillRepository skillRepository) {
        this.itemService = itemService;
        this.monsterSession = monsterSession;
        this.skillRepository = skillRepository;
    }
    public abstract void create(Hero hero);
    public abstract void update(Hero hero);
    public abstract void attack(Hero hero, Monster monster, boolean buff);
    public abstract void attackSkill(Hero hero, Monster monster, Skill skill, boolean buff);
    public abstract void endFight(Hero hero);

    protected Integer sumDefence (Hero hero) {
        List<Armor> armorList = itemService.getArmorFromItems(new ArrayList<>(hero.getItemEquipped().values()));
        return armorList.stream().map(Armor::getDefence).reduce(0, Integer::sum);
    }

    protected Integer sumAttack (Hero hero) {
        List<Weapon> weaponList = itemService.getWeaponFromItems(new ArrayList<>(hero.getItemEquipped().values()));
        List<Accessory> accessoryList = itemService.getAccessoryFromItems(new ArrayList<>(hero.getItemEquipped().values()));
        return weaponList.stream().map(Weapon::getAttack).reduce(0,Integer::sum) +
                accessoryList.stream().map(Accessory::getAttack).reduce(0,Integer::sum);
    }

    protected Integer damage(Integer attackOfAttacker, Integer defenceOfDefender) {
        return (attackOfAttacker * 2 * 100)/(100+defenceOfDefender);
    }

    protected void dealDamage(Hero hero, Monster monster) {
        Integer heroAttack = damage(hero.getAttack(),monster.getDefense());
        Integer monsterAttack = damage(monster.getAttack(),hero.getDefence());

        hero.setHealthPointsCurrent(hero.getHealthPointsCurrent() - monsterAttack);
        monsterSession.setHealthPointsCurrent(monsterSession.getHealthPointsCurrent() - heroAttack);
    }

    protected Skill getBuffSkill(Hero hero) {
        List<Skill> skills = skillRepository.findAllByRaceAndType(hero.getRace(),"buff");
        return skills.get(0);
    }

    protected Skill getDamageSkill(Hero hero) {
        List<Skill> skills = skillRepository.findAllByRaceAndType(hero.getRace(),"damage");
        return skills.get(0);
    }
}
