package pl.coderslab.Projekt_RPG.project.hero.races;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    Race findByName(String name);
}
