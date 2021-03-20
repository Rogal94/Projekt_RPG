package pl.coderslab.Projekt_RPG.project;

import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_RPG.user.User;

import java.util.List;

@Service
public interface HeroService{
    void createHero(Hero hero);
    void updateHero(Hero hero);
    boolean checkHero(Long heroId , User user);
    void addEquipArmorToList(List<Armor> list , Hero hero);
}
