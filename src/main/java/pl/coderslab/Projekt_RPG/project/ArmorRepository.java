package pl.coderslab.Projekt_RPG.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArmorRepository extends JpaRepository<Armor, Long> {
    List<Armor> findAllByType(String type);
}
