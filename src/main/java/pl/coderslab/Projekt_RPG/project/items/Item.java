package pl.coderslab.Projekt_RPG.project.items;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.Projekt_RPG.project.hero.races.Race;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="category",discriminatorType=DiscriminatorType.STRING)
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Integer price;
    private String type;
    private Integer tier;
    private Integer grade;
    @Column(insertable = false, updatable = false)
    private String category;
    @ManyToMany
    private List<Race> race;
}
