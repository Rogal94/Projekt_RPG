package pl.coderslab.Projekt_RPG.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
    List<Hero> findAllByUserId(Long userId);
    List<Hero> findTop10ByOrderByLevelDesc();
    List<Hero> findTop10ByOrderByAttackDesc();
    List<Hero> findTop10ByOrderByDefenseDesc();
}
