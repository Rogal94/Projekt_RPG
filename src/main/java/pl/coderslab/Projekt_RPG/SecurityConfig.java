package pl.coderslab.Projekt_RPG;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.Projekt_RPG.user.SpringDataUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/panel/**","/character/**","/items/**","/monsters/**","/quests/**","/reward/**","/shop/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/add/**").hasRole("ADMIN")
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/character/list")
                .failureUrl("/login/error")
                .and().logout().logoutSuccessUrl("/")
                .permitAll();
    }
}
