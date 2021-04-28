package pl.coderslab.Projekt_RPG.project.items;

import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_RPG.project.items.items.Armor;
import pl.coderslab.Projekt_RPG.project.items.items.Weapon;

import java.util.List;
import java.util.Map;

@Service
public interface ItemService {
    List<Weapon> getWeaponFromItems(List<Item> items);
    List<Armor> getArmorFromItems(List<Item> items);
    List<Armor> getSortedArmorFromItems(Map<String,Item> items);
    Armor getRandomArmor (int power);
    Weapon getRandomWeapon (int power);
    String randomItem();
}
