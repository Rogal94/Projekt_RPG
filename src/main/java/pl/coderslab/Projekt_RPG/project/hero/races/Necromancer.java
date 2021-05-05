package pl.coderslab.Projekt_RPG.project.hero.races;

import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_RPG.project.hero.Hero;
import pl.coderslab.Projekt_RPG.project.hero.Skill;
import pl.coderslab.Projekt_RPG.project.hero.SkillRepository;
import pl.coderslab.Projekt_RPG.project.items.ItemService;
import pl.coderslab.Projekt_RPG.project.items.items.WeaponRepository;
import pl.coderslab.Projekt_RPG.project.monster.Monster;
import pl.coderslab.Projekt_RPG.project.monster.MonsterSession;


@Service
public class Necromancer extends RaceService {

    public Necromancer(MonsterSession monsterSession, WeaponRepository weaponRepository, ItemService itemService, SkillRepository skillRepository) {
        super(monsterSession, weaponRepository, itemService, skillRepository);
    }

    @Override
    public void create(Hero hero) {
        hero.setHealthPointsMax(hero.getVitality()*100);
        hero.setSecPointsMax(200);
        hero.setAttack(hero.getMainStat()*5 + sumAttack(hero));
        hero.setDefence(hero.getVitality()*5 + sumDefence(hero));
        hero.setSecPointsCurrent(100);
    }

    @Override
    public void update(Hero hero) {
        hero.setHealthPointsMax(hero.getVitality()*100);
        hero.setAttack(hero.getMainStat()*5 + sumAttack(hero));
        hero.setDefence(hero.getVitality()*5 + sumDefence(hero));
        hero.setExperienceMax(hero.getLevel()*100);
    }

    @Override
    public void attack(Hero hero, Monster monster,boolean buff) {
        if(buff) {
            if(hero.getSkill().get("buff").getName().equals(getBuffSkill(hero).getName())) {
                hero.setAttack(hero.getAttack() + hero.getSkill().get("buff").getEffect());
                hero.setDefence(hero.getDefence() + hero.getSkill().get("buff").getEffect());
            }
        }
        dealDamage(hero,monster);
        hero.setSecPointsCurrent(hero.getSecPointsCurrent()+20);
    }

    @Override
    public void attackSkill(Hero hero, Monster monster, Skill skill, boolean buff) {
        if(getBuffSkill(hero).getName().equals(skill.getName()) && hero.getSecPointsCurrent() >= getBuffSkill(hero).getCost()) {
            monsterSession.setSpecialAttack(true);
            hero.setSecPointsCurrent(hero.getSecPointsCurrent() - getBuffSkill(hero).getCost());
            hero.setAttack(0);
        }else if(getDamageSkill(hero).getName().equals(skill.getName()) && hero.getSecPointsCurrent() >= getDamageSkill(hero).getCost()) {
            hero.setAttack(hero.getAttack() + skill.getDamage());
            if(buff) {
                if(hero.getSkill().get("buff").getName().equals(getBuffSkill(hero).getName())) {
                    hero.setAttack(hero.getAttack() + hero.getSkill().get("buff").getEffect());
                    hero.setDefence(hero.getDefence() + hero.getSkill().get("buff").getEffect());
                }
            }
            Integer points = hero.getSecPointsCurrent();
            if(points>100){
                hero.setHealthPointsCurrent(hero.getHealthPointsCurrent() + points * 2);
            }else if(points<100){
                hero.setHealthPointsCurrent(hero.getHealthPointsCurrent() - points * 2);
            }
            hero.setSecPointsCurrent(hero.getSecPointsCurrent() - getDamageSkill(hero).getCost());
        }
        dealDamage(hero,monster);
    }

    @Override
    public void endFight(Hero hero) {
        hero.setSecPointsCurrent(hero.getSecPointsMax()/2);
    }
}
