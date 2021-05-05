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
public class Assassin extends RaceService {

    public Assassin(MonsterSession monsterSession, WeaponRepository weaponRepository, ItemService itemService, SkillRepository skillRepository) {
        super(monsterSession, weaponRepository, itemService, skillRepository);
    }

    @Override
    public void create(Hero hero) {
        hero.setHealthPointsMax(hero.getVitality()*100);
        hero.setSecPointsMax(100);
        hero.setAttack(hero.getMainStat()*5 + sumAttack(hero));
        hero.setDefence(hero.getVitality()*5 + sumDefence(hero));
        hero.setSecPointsCurrent(hero.getSecPointsMax());
    }

    @Override
    public void update(Hero hero) {
        hero.setHealthPointsMax(hero.getVitality()*100);
        hero.setAttack(hero.getMainStat()*5 + sumAttack(hero));
        hero.setDefence(hero.getVitality()*5 + sumDefence(hero));
        hero.setExperienceMax(hero.getLevel()*100);
    }

    @Override
    public void attack(Hero hero, Monster monster, boolean buff) {
        if(buff) {
            if(hero.getSkill().get("buff").getName().equals(getBuffSkill(hero).getName())) {
                double effect = hero.getSkill().get("buff").getEffect().doubleValue();
                double attack = (effect/100) * hero.getAttack();
                hero.setAttack((int)attack);
                monsterSession.setSpecialAttack(false);
            }
        }
        dealDamage(hero,monster);
        hero.setSecPointsCurrent(hero.getSecPointsCurrent() + 10);
    }

    @Override
    public void attackSkill(Hero hero, Monster monster, Skill skill, boolean buff) {
        if(getBuffSkill(hero).getName().equals(skill.getName()) && hero.getSecPointsCurrent() >= getBuffSkill(hero).getCost()) {
            monsterSession.setSpecialAttack(true);
            hero.setSecPointsCurrent(hero.getSecPointsCurrent() - getBuffSkill(hero).getCost() + 10);
            hero.setAttack(0);
        }else if(getDamageSkill(hero).getName().equals(skill.getName()) && hero.getSecPointsCurrent() >= getDamageSkill(hero).getCost()) {
            hero.setAttack(hero.getAttack() + skill.getDamage());
            if(buff) {
                if(hero.getSkill().get("buff").getName().equals(getBuffSkill(hero).getName())) {
                    double effect = hero.getSkill().get("buff").getEffect().doubleValue();
                    double attack = (effect/100) * hero.getAttack();
                    hero.setAttack((int) attack);
                    monsterSession.setSpecialAttack(false);
                }
            }
            hero.setSecPointsCurrent(hero.getSecPointsCurrent() - getDamageSkill(hero).getCost() + 10);
        }
        dealDamage(hero,monster);
    }
    @Override
    public void endFight(Hero hero) {
        hero.setSecPointsCurrent(hero.getSecPointsMax());
    }
}
