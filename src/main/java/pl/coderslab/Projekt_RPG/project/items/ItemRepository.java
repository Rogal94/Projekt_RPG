package pl.coderslab.Projekt_RPG.project.items;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.Projekt_RPG.project.hero.races.Race;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByCategory(String category);
    List<Item> findAllByTier(Integer tier);
    List<Item> findAllByTierAndRace(Integer tier, Race race);
    List<Item> findAllByRace(Race race);
    Item findByNameAndGrade(String name, Integer grade);
}

