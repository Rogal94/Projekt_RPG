package pl.coderslab.Projekt_RPG.project.items.items;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.Projekt_RPG.project.hero.races.Race;
import pl.coderslab.Projekt_RPG.project.items.Item;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Armor extends Item {
    private Integer defence;
    @ManyToOne
    private Race race;
}
