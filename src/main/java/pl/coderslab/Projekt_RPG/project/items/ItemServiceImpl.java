package pl.coderslab.Projekt_RPG.project.items;

import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_RPG.project.hero.races.Race;
import pl.coderslab.Projekt_RPG.project.items.items.Armor;
import pl.coderslab.Projekt_RPG.project.items.items.ArmorRepository;
import pl.coderslab.Projekt_RPG.project.items.items.Weapon;
import pl.coderslab.Projekt_RPG.project.items.items.WeaponRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ItemServiceImpl implements ItemService{
    private final ArmorRepository armorRepository;
    private final WeaponRepository weaponRepository;
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository, ArmorRepository armorRepository, WeaponRepository weaponRepository) {
        this.armorRepository = armorRepository;
        this.weaponRepository = weaponRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Weapon> getWeaponFromItems(List<Item> itemList) {
        List<Weapon> items = new ArrayList<>();
        itemList.stream().filter(i->i.getCategory().equals("Weapon")).forEach(i->{
            Weapon weapon = weaponRepository.getOne(i.getId());
            items.add(weapon);
        });
        return items;
    }

    @Override
    public List<Armor> getArmorFromItems(List<Item> itemList) {
        List<Armor> items = new ArrayList<>();
        itemList.stream().filter(i->i.getCategory().equals("Armor")).forEach(i->{
            Armor armor = armorRepository.getOne(i.getId());
            items.add(armor);
        });
        return items;
    }

    @Override
    public LinkedHashMap<String, Item> getSortedItems(Map<String,Item> itemMap) {
        LinkedHashMap<String, Item> items = new LinkedHashMap<>();
        items.put("weapon", itemMap.get("weapon"));
        items.put("helmet", itemMap.get("helmet"));
        items.put("armor", itemMap.get("armor"));
        items.put("pants", itemMap.get("pants"));
        items.put("gloves", itemMap.get("gloves"));
        items.put("boots", itemMap.get("boots"));
        return items;
    }

    @Override
    public Item getRandomItem(int tier) {
        Random rand = new Random();
        List<Item> itemList = itemRepository.findAllByTier(tier);
        int randomIndex = rand.nextInt(itemList.size());
        return itemList.get(randomIndex);
    }

    @Override
    public Item getSuitableRandomItem(int tier , Race race) {
        Random rand = new Random();
        List<Item> itemList = itemRepository.findAllByTierAndRace(tier,race);
        int randomIndex = rand.nextInt(itemList.size());
        return itemList.get(randomIndex);
    }

    @Override
    public Integer getChanceToUpgrade(Integer grade) {
        switch (grade) {
            case 1:
                return 100;
            case 2:
                return 50;
            case 3:
                return 20;
            case 4:
                return 10;
        }
        return null;
    }

    @Override
    public boolean upgradeItem(Integer chance) {
        Random r = new Random();
        int result = r.nextInt(100) + 1;
        return result <= chance;
    }
}
