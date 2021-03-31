package pl.coderslab.Projekt_RPG.project;

import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_RPG.user.User;

import java.util.List;

@Service
public interface HeroService{
    void createHero(Hero hero);
    void updateHero(Hero hero);
    boolean checkHero(Long heroId , User user);
    void addEquipArmorToList(List<Armor> list , Hero hero);
    void removeEquippedArmor(List<Armor> armorList, Hero hero);
    void attack(Hero hero, Monster monster, Integer skillDmg);
    void levelUp(Hero hero);
    Armor getRandomArmor (int power);
    Weapon getRandomWeapon (int power);
    String randomItem();
    String checkCoordinates(List<Double> list);
}
