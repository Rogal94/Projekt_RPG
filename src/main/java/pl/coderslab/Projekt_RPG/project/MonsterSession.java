package pl.coderslab.Projekt_RPG.project;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
public class MonsterSession {
    private Long id;
    private Integer healthPointsCurrent;
}
