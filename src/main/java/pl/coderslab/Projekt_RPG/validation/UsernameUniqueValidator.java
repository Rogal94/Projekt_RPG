package pl.coderslab.Projekt_RPG.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_RPG.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
@Slf4j
public class UsernameUniqueValidator implements ConstraintValidator<UsernameUnique, String> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(UsernameUnique constraintAnnotation) {
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if ( value == null ) {
            return true;
        }
       try {
        return userService.findByUserName(value)==null;
        }catch(NullPointerException e){
            return true;
        }
    }
}
