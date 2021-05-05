package pl.coderslab.Projekt_RPG.project.items;

import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_RPG.project.hero.races.Race;
import pl.coderslab.Projekt_RPG.project.items.items.Armor;
import pl.coderslab.Projekt_RPG.project.items.items.Weapon;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public interface ItemService {
    List<Weapon> getWeaponFromItems(List<Item> items);
    List<Armor> getArmorFromItems(List<Item> items);
    LinkedHashMap<String, Item> getSortedItems(Map<String,Item> items);
    Item getRandomItem (int tier);
    Item getSuitableRandomItem (int tier, Race race);
}
