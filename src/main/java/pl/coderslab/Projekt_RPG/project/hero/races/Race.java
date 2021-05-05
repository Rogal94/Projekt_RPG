package pl.coderslab.Projekt_RPG.project.hero.races;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    public String mainStatName;
    public String mainStatDesc;
    public String secPointsName;
    public String secPointsDesc;
}
