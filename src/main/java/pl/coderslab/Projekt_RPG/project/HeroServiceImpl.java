package pl.coderslab.Projekt_RPG.project;

import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_RPG.user.User;

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
        hero.setEquipHelmet(1L);
        hero.setEquipChest(5L);
        hero.setEquipLegs(12L);
        hero.setEquipGloves(0L);
        hero.setEquipBoots(0L);
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
        Integer armorSum = 0;
        if(armorRepository.findById(hero.getEquipHelmet()).isPresent()) {
            armorSum += armorRepository.findById(hero.getEquipHelmet()).get().getDefense();
        }
        if(armorRepository.findById(hero.getEquipChest()).isPresent()) {
            armorSum += armorRepository.findById(hero.getEquipChest()).get().getDefense();
        }
        if(armorRepository.findById(hero.getEquipLegs()).isPresent()) {
            armorSum += armorRepository.findById(hero.getEquipLegs()).get().getDefense();
        }
        if(armorRepository.findById(hero.getEquipGloves()).isPresent()) {
            armorSum += armorRepository.findById(hero.getEquipGloves()).get().getDefense();
        }
        if(armorRepository.findById(hero.getEquipBoots()).isPresent()) {
            armorSum += armorRepository.findById(hero.getEquipBoots()).get().getDefense();
        }
        return armorSum;
    }
}
