package pl.coderslab.Projekt_RPG.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_RPG.project.hero.Hero;
import pl.coderslab.Projekt_RPG.project.hero.HeroRepository;

@Service
public class StatisticsTimer {

    private final HeroRepository heroRepository;

    public StatisticsTimer(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Scheduled(fixedRate = 600000)
    public void addStats () {
        for(Hero hero : heroRepository.findAll()) {
            if(hero.getHealthPointsCurrent() + 100 < hero.getHealthPointsMax()) {
                hero.setHealthPointsCurrent(hero.getHealthPointsCurrent()+100);
            }else {
                hero.setHealthPointsCurrent(hero.getHealthPointsMax());
            }

            if(hero.getStaminaCurrent() + 10 < hero.getStaminaMax()) {
                hero.setStaminaCurrent(hero.getStaminaCurrent()+10);
            }else {
                hero.setStaminaCurrent(hero.getStaminaMax());
            }
            heroRepository.save(hero);
        }
    }
}