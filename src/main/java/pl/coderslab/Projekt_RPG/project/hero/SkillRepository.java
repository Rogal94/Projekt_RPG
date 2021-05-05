package pl.coderslab.Projekt_RPG.project.hero;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.Projekt_RPG.project.hero.races.Race;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findAllByRace(Race race);
    List<Skill> findAllByRaceAndType(Race race, String type);
    Skill findByNameAndSkillRank(String name, Integer skillRank);
    List<Skill> findAllByRaceAndSkillRank(Race race, Integer skillRank);
}
