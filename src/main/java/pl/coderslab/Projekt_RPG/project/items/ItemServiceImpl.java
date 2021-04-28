package pl.coderslab.Projekt_RPG.project.items;

import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_RPG.project.items.items.Armor;
import pl.coderslab.Projekt_RPG.project.items.items.ArmorRepository;
import pl.coderslab.Projekt_RPG.project.items.items.Weapon;
import pl.coderslab.Projekt_RPG.project.items.items.WeaponRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService{
    private final ArmorRepository armorRepository;
    private final WeaponRepository weaponRepository;

    public ItemServiceImpl(ArmorRepository armorRepository, WeaponRepository weaponRepository) {
        this.armorRepository = armorRepository;
        this.weaponRepository = weaponRepository;
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
    public List<Armor> getSortedArmorFromItems(Map<String,Item> itemMap) {
        List<Item> items = new ArrayList<>();
        items.add(itemMap.get("helmet"));
        items.add(itemMap.get("armor"));
        items.add(itemMap.get("pants"));
        items.add(itemMap.get("gloves"));
        items.add(itemMap.get("boots"));
        return getArmorFromItems(items);
    }

    @Override
    public Armor getRandomArmor(int power) {
        Armor armor;
        Random rand = new Random();
        List<Armor> armorList = new ArrayList<>();
        switch(power) {
            case 1:
                armorList = armorRepository.findAll().stream()
                        .filter(a->a.getDefence()>0)
                        .filter(a->a.getDefence()<15)
                        .collect(Collectors.toList());

                break;
            case 2:
                armorList = armorRepository.findAll().stream()
                        .filter(a->a.getDefence()>5)
                        .filter(a->a.getDefence()<35)
                        .collect(Collectors.toList());
                break;
            case 3:
                armorList = armorRepository.findAll().stream()
                        .filter(a->a.getDefence()>15)
                        .filter(a->a.getDefence()<60)
                        .collect(Collectors.toList());
                break;
            case 4:
                armorList = armorRepository.findAll().stream()
                        .filter(a->a.getDefence()>35)
                        .filter(a->a.getDefence()<60)
                        .collect(Collectors.toList());
                break;
        }
        int randomIndex = rand.nextInt(armorList.size());
        armor = armorList.get(randomIndex);
        return armor;
    }

    @Override
    public Weapon getRandomWeapon(int power) {
        Weapon weapon;
        Random rand = new Random();
        List<Weapon> weaponList = new ArrayList<>();
        switch(power) {
            case 1:
                weaponList = weaponRepository.findAll().stream()
                        .filter(w->w.getAttack()>0)
                        .filter(w->w.getAttack()<15)
                        .filter(w->w.getId()<23)
                        .collect(Collectors.toList());
                break;
            case 2:
                weaponList = weaponRepository.findAll().stream()
                        .filter(w->w.getAttack()>5)
                        .filter(w->w.getAttack()<35)
                        .filter(w->w.getId()<24)
                        .collect(Collectors.toList());
                break;
            case 3:
                weaponList = weaponRepository.findAll().stream()
                        .filter(w->w.getAttack()>15)
                        .filter(w->w.getAttack()<60)
                        .collect(Collectors.toList());
                weaponList.add(weaponRepository.getOne(24L));
                break;
            case 4:
                weaponList = weaponRepository.findAll().stream()
                        .filter(w->w.getAttack()>35)
                        .filter(w->w.getAttack()<60)
                        .collect(Collectors.toList());
                weaponList.add(weaponRepository.getOne(25L));
                break;
        }
        int randomIndex = rand.nextInt(weaponList.size());
        weapon = weaponList.get(randomIndex);
        return weapon;
    }

    @Override
    public String randomItem() {
        Random rand = new Random();
        int random = rand.nextInt(5);
        if(random <= 1) {
            return "weapon";
        }
        return "armor";
    }
}
