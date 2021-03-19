package pl.coderslab.Projekt_RPG.project;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.Projekt_RPG.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String race;
    private Integer level;
    private Integer statisticPoints;
    private Integer skillPoints;
    private String mainStatName;
    private Integer mainStat;
    private Integer vitality;
    private Integer healthPointsMax;
    private Integer healthPointsCurrent;
    private Integer manaPointsMax;
    private Integer manaPointsCurrent;
    private Integer staminaMax;
    private Integer staminaCurrent;
    private Integer experienceMax;
    private Integer experienceCurrent;
    private Integer attack;
    private Integer defense;
    private Integer goldAmount;
    private Long monsterKilled;
    private Long reward;
    private Long equipWeapon;
    private Long equipHelmet;
    private Long equipChest;
    private Long equipLegs;
    private Long equipGloves;
    private Long equipBoots;
    private Integer potionHealth;
    private Integer potionMana;
    private Integer potionStamina;

    @ManyToMany
    private List<Skill> skill;
    @ManyToMany
    private List<Weapon> weapon;
    @ManyToMany
    private List<Armor> armor;
    @ManyToOne
    private Quest quest;
    @ManyToOne
    private User user;
}