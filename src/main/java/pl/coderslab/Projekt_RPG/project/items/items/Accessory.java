package pl.coderslab.Projekt_RPG.project.items.items;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.Projekt_RPG.project.items.Item;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Accessory extends Item {
    private Integer attack;
}
