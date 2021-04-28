package pl.coderslab.Projekt_RPG.user;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.Projekt_RPG.project.hero.Hero;
import pl.coderslab.Projekt_RPG.validation.UsernameUnique;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 60)
    @NotBlank(message = "CAN NOT BE EMPTY!")
    @UsernameUnique(message = "USERNAME IS ALREADY EXIST!")
    private String username;
    @NotBlank(message = "CAN NOT BE EMPTY!")
    private String password;
    private int enabled;
    private Long loggedHero;
    private Double musicVolume;
    private Integer combatStyle;
    private Integer mapSize;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Hero> hero;
}
