package pl.coderslab.Projekt_RPG.project;

import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_RPG.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class HeroServiceImpl implements HeroService{
    private final QuestRepository questRepository;
    private final WeaponRepository weaponRepository;
    private final ArmorRepository armorRepository;
    private final HeroRepository heroRepository;
    private final MonsterSession monsterSession;

    public HeroServiceImpl(QuestRepository questRepository, WeaponRepository weaponRepository, ArmorRepository armorRepository, HeroRepository heroRepository, MonsterSession monsterSession) {
        this.questRepository = questRepository;
        this.weaponRepository = weaponRepository;
        this.armorRepository = armorRepository;
        this.heroRepository = heroRepository;
        this.monsterSession = monsterSession;
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
        hero.setReward(0L);
        hero.setStaminaMax(200);
        hero.setStaminaCurrent(hero.getStaminaMax());
        hero.setQuest(questRepository.getOne(1L));
        hero.setEquipWeapon(1L);
        hero.setEquipHelmet(3L);
        hero.setEquipChest(7L);
        hero.setEquipLegs(14L);
        hero.setEquipGloves(1L);
        hero.setEquipBoots(2L);
        hero.setPotionHealth(5);
        hero.setPotionMana(5);
        hero.setPotionStamina(2);
        hero.setExperienceCurrent(0);
        hero.setExperienceMax(hero.getLevel()*100);

        List <Weapon> weaponList = new ArrayList<>();
        weaponList.add(weaponRepository.getOne(1L));
        hero.setWeapon(weaponList);

        List <Armor> armorList = new ArrayList<>();
        armorList.add(armorRepository.getOne(3L));
        armorList.add(armorRepository.getOne(7L));
        armorList.add(armorRepository.getOne(14L));
        hero.setArmor(armorList);

        if(hero.getRace().equals("warrior")){
            createWarrior(hero);
        }else if(hero.getRace().equals("mage")) {
            createMage(hero);
        }
        hero.setHealthPointsCurrent(hero.getHealthPointsMax());
        hero.setManaPointsCurrent(hero.getManaPointsMax());
    }

    @Override
    public void updateHero(Hero hero) {
        if(hero.getRace().equals("warrior")){
            updateWarrior(hero);
        }else if(hero.getRace().equals("mage")) {
            updateMage(hero);
        }
    }

    @Override
    public boolean checkHero(Long heroId, User user) {
        return heroRepository.getOne(heroId).getUser() == user;
    }
    @Override
    public void addEquipArmorToList(List<Armor> list, Hero hero) {
        list.add(armorRepository.getOne(hero.getEquipHelmet()));
        list.add(armorRepository.getOne(hero.getEquipChest()));
        list.add(armorRepository.getOne(hero.getEquipLegs()));
        list.add(armorRepository.getOne(hero.getEquipGloves()));
        list.add(armorRepository.getOne(hero.getEquipBoots()));
    }

    public void createWarrior(Hero hero) {
        hero.setMainStatName("strength");
        hero.setHealthPointsMax(hero.getVitality()*100 + hero.getMainStat()*50);
        hero.setManaPointsMax(hero.getMainStat()*50);
        hero.setAttack(hero.getMainStat()*5);
        hero.setDefense(hero.getVitality()*5 + sumDefense(hero));
    }

    public void createMage(Hero hero) {
        hero.setMainStatName("intellect");
        hero.setHealthPointsMax(hero.getVitality()*100);
        hero.setManaPointsMax(hero.getMainStat()*100);
        hero.setAttack(hero.getMainStat()*5);
        hero.setDefense(hero.getVitality()*5 + sumDefense(hero));
    }

    public void updateWarrior(Hero hero) {
        hero.setHealthPointsMax(hero.getVitality()*100 + hero.getMainStat()*50);
        hero.setManaPointsMax(hero.getMainStat()*50);
        hero.setAttack(hero.getMainStat()*5 + weaponRepository.getOne(hero.getEquipWeapon()).getAttack());
        hero.setDefense(hero.getVitality()*5 + sumDefense(hero));
        hero.setExperienceMax(hero.getLevel()*100);
    }

    public void updateMage(Hero hero) {
        hero.setHealthPointsMax(hero.getVitality()*100);
        hero.setManaPointsMax(hero.getMainStat()*100);
        hero.setAttack(hero.getMainStat()*5 + weaponRepository.getOne(hero.getEquipWeapon()).getAttack());
        hero.setDefense(hero.getVitality()*5 + sumDefense(hero));
        hero.setExperienceMax(hero.getLevel()*100);
    }

    public Integer sumDefense (Hero hero) {
        return armorRepository.getOne(hero.getEquipHelmet()).getDefense() +
        armorRepository.getOne(hero.getEquipHelmet()).getDefense() +
        armorRepository.getOne(hero.getEquipChest()).getDefense() +
        armorRepository.getOne(hero.getEquipLegs()).getDefense() +
        armorRepository.getOne(hero.getEquipGloves()).getDefense() +
        armorRepository.getOne(hero.getEquipBoots()).getDefense();
    }

    @Override
    public void removeEquippedArmor(List<Armor> armorList, Hero hero) {
        List<Long> idList = Arrays.asList(hero.getEquipHelmet(), hero.getEquipChest(), hero.getEquipLegs(), hero.getEquipGloves(), hero.getEquipBoots());
        for (Long id : idList) {
            armorList.remove(armorRepository.getOne(id));
        }
    }

    @Override
    public void attack(Hero hero, Monster monster, Integer skillDmg) {
        Integer monsterDamage = monster.getAttack()*2 - hero.getDefense();
        if(monsterDamage<10) {
            monsterDamage = 10;
        }
        Integer heroDamage = hero.getAttack()* 2 + skillDmg - monster.getDefense();
        if(heroDamage<10) {
            heroDamage = 10;
        }
        hero.setHealthPointsCurrent(hero.getHealthPointsCurrent() - monsterDamage);
        monsterSession.setHealthPointsCurrent(monsterSession.getHealthPointsCurrent() - heroDamage);

        Weapon weapon = weaponRepository.getOne(hero.getEquipWeapon());
        switch (weapon.getName()) {
            case "Water Staff":
                hero.setManaPointsCurrent(hero.getManaPointsCurrent() + 10);
                break;
            case "Earth Staff":
                hero.setManaPointsCurrent(hero.getManaPointsCurrent() + 20);
                break;
            case "Air Staff":
                hero.setManaPointsCurrent(hero.getManaPointsCurrent() + 50);
                break;
            case "Fire Staff":
                hero.setManaPointsCurrent(hero.getManaPointsCurrent() + 100);
                break;
        }
        if(hero.getManaPointsCurrent() > hero.getManaPointsMax()) {
            hero.setManaPointsCurrent(hero.getManaPointsMax());
        }
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
    public Armor getRandomArmor(int power) {
        Armor armor;
        Random rand = new Random();
        List<Armor> armorList = new ArrayList<>();
        switch(power) {
            case 1:
                armorList = armorRepository.findAll().stream()
                        .filter(a->a.getDefense()>0)
                        .filter(a->a.getDefense()<15)
                        .collect(Collectors.toList());

                break;
            case 2:
                armorList = armorRepository.findAll().stream()
                        .filter(a->a.getDefense()>5)
                        .filter(a->a.getDefense()<35)
                        .collect(Collectors.toList());
                break;
            case 3:
                armorList = armorRepository.findAll().stream()
                        .filter(a->a.getDefense()>15)
                        .filter(a->a.getDefense()<60)
                        .collect(Collectors.toList());
                break;
            case 4:
                armorList = armorRepository.findAll().stream()
                        .filter(a->a.getDefense()>35)
                        .filter(a->a.getDefense()<60)
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
