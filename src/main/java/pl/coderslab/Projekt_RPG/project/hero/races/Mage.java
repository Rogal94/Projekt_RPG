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
public class Mage extends RaceService {

    public Mage(MonsterSession monsterSession, ItemService itemService, SkillRepository skillRepository) {
        super(monsterSession, itemService, skillRepository);
    }

    @Override
    public void create(Hero hero) {
        hero.setHealthPointsMax(hero.getVitality()*100);
        hero.setSecPointsMax(hero.getMainStat()*100);
        hero.setAttack(hero.getMainStat()*5 + sumAttack(hero));
        hero.setDefence(hero.getVitality()*5 + sumDefence(hero));
        hero.setSecPointsCurrent(hero.getSecPointsMax());
    }

    @Override
    public void update(Hero hero) {
        hero.setHealthPointsMax(hero.getVitality()*100);
        hero.setSecPointsMax(hero.getMainStat()*100);
        hero.setAttack(hero.getMainStat()*5 + sumAttack(hero));
        hero.setDefence(hero.getVitality()*5 + sumDefence(hero));
        hero.setExperienceMax(hero.getLevel()*100);
    }

    @Override
    public void attack(Hero hero, Monster monster,boolean buff) {
        if(buff) {
            if(hero.getSkill().get("buff").getName().equals(getBuffSkill(hero).getName())) {
                hero.setSecPointsCurrent(hero.getSecPointsCurrent() + hero.getSkill().get("buff").getEffect());
            }
        }
        hero.setSecPointsCurrent(hero.getSecPointsCurrent()+50);
        dealDamage(hero,monster);
    }

    @Override
    public void attackSkill(Hero hero, Monster monster, Skill skill, boolean buff) {
        if(getBuffSkill(hero).getName().equals(skill.getName()) && hero.getSecPointsCurrent() >= getBuffSkill(hero).getCost()) {
            monsterSession.setSpecialAttack(true);
            hero.setSecPointsCurrent(hero.getSecPointsCurrent() - getBuffSkill(hero).getCost());
            hero.setAttack(0);
            dealDamage(hero,monster);
        }else if(getDamageSkill(hero).getName().equals(skill.getName()) && hero.getSecPointsCurrent() >= getDamageSkill(hero).getCost()) {
            hero.setAttack(hero.getAttack() + skill.getDamage());
            if(buff) {
                if(hero.getSkill().get("buff").getName().equals(getBuffSkill(hero).getName())) {
                    hero.setSecPointsCurrent(hero.getSecPointsCurrent() + hero.getSkill().get("buff").getEffect());
                }
            }
            hero.setSecPointsCurrent(hero.getSecPointsCurrent() - getDamageSkill(hero).getCost());
            dealDamage(hero,monster);
        }
    }
    @Override
    public void endFight(Hero hero) {
    }
}
