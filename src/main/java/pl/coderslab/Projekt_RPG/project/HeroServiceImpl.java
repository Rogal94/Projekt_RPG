package pl.coderslab.Projekt_RPG.project;

import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_RPG.user.User;

import java.util.List;

@Service
public class HeroServiceImpl implements HeroService{
    private final QuestRepository questRepository;
    private final WeaponRepository weaponRepository;
    private final ArmorRepository armorRepository;
    private final HeroRepository heroRepository;

    public HeroServiceImpl(QuestRepository questRepository, WeaponRepository weaponRepository, ArmorRepository armorRepository, HeroRepository heroRepository) {
        this.questRepository = questRepository;
        this.weaponRepository = weaponRepository;
        this.armorRepository = armorRepository;
        this.heroRepository = heroRepository;
    }

    @Override
    public void createHero(Hero hero) {
        hero.setLevel(1);
        hero.setStatisticPoints(0);
        hero.setSkillPoints(0);
        hero.setMainStat(10);
        hero.setVitality(10);
        hero.setGoldAmount(100);
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
        hero.setPotionHealth(1);
        hero.setPotionMana(1);
        hero.setPotionStamina(1);
        hero.setExperienceCurrent(0);
        hero.setExperienceMax(hero.getLevel()*100);

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
        hero.setDefense(hero.getVitality()*10 + sumDefense(hero));
    }

    public void createMage(Hero hero) {
        hero.setMainStatName("intellect");
        hero.setHealthPointsMax(hero.getVitality()*100);
        hero.setManaPointsMax(hero.getMainStat()*100);
        hero.setAttack(hero.getMainStat()*10);
        hero.setDefense(hero.getVitality()*5 + sumDefense(hero));
    }

    public void updateWarrior(Hero hero) {
        hero.setHealthPointsMax(hero.getVitality()*100 + hero.getMainStat()*50);
        hero.setManaPointsMax(hero.getMainStat()*50);
        hero.setAttack(hero.getMainStat()*5 + weaponRepository.getOne(hero.getEquipWeapon()).getAttack());
        hero.setDefense(hero.getVitality()*10 + sumDefense(hero));
        hero.setExperienceMax(hero.getLevel()*100);
    }

    public void updateMage(Hero hero) {
        hero.setHealthPointsMax(hero.getVitality()*100);
        hero.setManaPointsMax(hero.getMainStat()*100);
        hero.setAttack(hero.getMainStat()*10 + weaponRepository.getOne(hero.getEquipWeapon()).getAttack());
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
}
