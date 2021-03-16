package pl.coderslab.Projekt_RPG.project;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Hero> hero;

}
