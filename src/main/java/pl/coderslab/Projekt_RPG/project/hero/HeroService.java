package pl.coderslab.Projekt_RPG.project.hero;

import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_RPG.project.items.items.Armor;
import pl.coderslab.Projekt_RPG.project.monster.Monster;
import pl.coderslab.Projekt_RPG.project.items.items.Weapon;
import pl.coderslab.Projekt_RPG.user.User;

import java.util.List;

@Service
public interface HeroService{
    void createHero(Hero hero);
    void updateHero(Hero hero);
    boolean checkHero(Long heroId , User user);
//    void addEquipArmorToList(List<Armor> list , Hero hero);
//    void removeEquippedArmor(List<Armor> armorList, Hero hero);
    void attack(Hero hero, Monster monster);
    void attackSkill(Hero hero, Monster monster, Skill skill);
    void levelUp(Hero hero);
    String checkCoordinates(List<Double> list);
    void endFight(Hero hero);
}
