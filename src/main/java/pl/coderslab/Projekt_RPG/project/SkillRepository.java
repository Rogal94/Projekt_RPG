package pl.coderslab.Projekt_RPG.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findAllBySkillForAndSkillRank(String skillFor, Integer rank);
    List<Skill> findAllByName(String name);
}
