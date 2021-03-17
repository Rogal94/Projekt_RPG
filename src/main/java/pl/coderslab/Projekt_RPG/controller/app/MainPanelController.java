package pl.coderslab.Projekt_RPG.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panel")
public class MainPanelController {
    @GetMapping("")
    public String mainPanel() {
        return "app/mainPanel";
    }
}
