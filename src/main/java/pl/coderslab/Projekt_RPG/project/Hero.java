package pl.coderslab.Projekt_RPG.project;

import pl.coderslab.Projekt_RPG.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String race;
    private String mainStatName;
    private Integer mainStat;
    private Integer vitality;
    private Integer healthPoints;
    private Integer manaPoints;
    private Integer stamina;
    private Integer attack;
    private Integer defence;
    private Integer goldAmount;
    private String monsterKilled;

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