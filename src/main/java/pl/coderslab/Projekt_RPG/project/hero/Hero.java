package pl.coderslab.Projekt_RPG.project.hero;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.Projekt_RPG.project.hero.races.Race;
import pl.coderslab.Projekt_RPG.project.items.Item;
import pl.coderslab.Projekt_RPG.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer level;
    private Integer statisticPoints;
    private Integer skillPoints;
    private Integer mainStat;
    private Integer vitality;
    private Integer healthPointsMax;
    private Integer healthPointsCurrent;
    private Integer secPointsMax;
    private Integer secPointsCurrent;
    private Integer staminaMax;
    private Integer staminaCurrent;
    private Integer experienceMax;
    private Integer experienceCurrent;
    private Integer attack;
    private Integer defence;
    private Integer goldAmount;
    private Long monsterKilled;
    private Integer potionHealth;
    private Integer potionStamina;

    @ManyToMany
    private Map<String,Item> itemEquipped;
    @ManyToMany
    private Map<String,Skill> skill;
    @ManyToMany
    private List<Item> item;
    @ManyToOne
    private Quest quest;
    @ManyToOne
    private User user;
    @ManyToOne
    private Race race;
}