package pl.coderslab.Projekt_RPG;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class RPGApplication {
    public static void main(String[] args) {
        SpringApplication.run(RPGApplication.class, args);
    }
}
