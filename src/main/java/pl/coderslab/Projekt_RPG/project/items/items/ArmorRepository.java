package pl.coderslab.Projekt_RPG.project.items.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmorRepository extends JpaRepository<Armor, Long> {
    Armor findByName(String name);
}
