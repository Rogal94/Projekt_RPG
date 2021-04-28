package pl.coderslab.Projekt_RPG.project.hero;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.Projekt_RPG.project.hero.races.Race;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer skillRank;
    private String type;
    private String description;
    private Integer damage;
    @ManyToOne
    private Race race;
}