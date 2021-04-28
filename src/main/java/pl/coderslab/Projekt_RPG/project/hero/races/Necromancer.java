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
            Skill skill = getBuffSkill(hero);
            hero.setAttack(hero.getAttack() + 15 +skill.getSkillRank() * 5);
            hero.setDefence(hero.getDefence() + 15 + skill.getSkillRank() * 5);
        }
        dealDamage(hero,monster);
        hero.setSecPointsCurrent(hero.getSecPointsCurrent()+20);
    }

    @Override
    public void attackSkill(Hero hero, Monster monster, Skill skill, boolean buff) {
        if(getBuffSkill(hero).getId().equals(skill.getId()) && hero.getSecPointsCurrent()>=100) {
            monsterSession.setSpecialAttack(true);
            hero.setSecPointsCurrent(hero.getSecPointsCurrent()-100);
            hero.setAttack(0);
        }else if(getDamageSkill(hero).getId().equals(skill.getId()) && hero.getSecPointsCurrent()>=30) {
            hero.setAttack(hero.getAttack() + skill.getDamage());
            if(buff) {
                Skill skillBuff = getBuffSkill(hero);
                hero.setAttack(hero.getAttack() + 15 +skillBuff.getSkillRank() * 5);
                hero.setDefence(hero.getDefence() + 15 + skillBuff.getSkillRank() * 5);
            }
            Integer points = hero.getSecPointsCurrent();
            if(points>100){
                hero.setHealthPointsCurrent(hero.getHealthPointsCurrent() + points * 2);
            }else if(points<100){
                hero.setHealthPointsCurrent(hero.getHealthPointsCurrent() - points * 2);
            }
            hero.setSecPointsCurrent(hero.getSecPointsCurrent()-30);
        }
        dealDamage(hero,monster);
    }

    @Override
    public void endFight(Hero hero) {
        hero.setSecPointsCurrent(100);
    }
}
