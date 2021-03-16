package pl.coderslab.Projekt_RPG.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Monster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer attack;
    private Integer defence;
    private Integer healthPoints;
    private Integer levelPreferred;
}

