package pl.coderslab.Projekt_RPG.validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.Projekt_RPG.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameUniqueValidator implements ConstraintValidator<UsernameUnique, String> {

    public UsernameUniqueValidator() {
    }

    private UserService userService;
    @Autowired
    public UsernameUniqueValidator(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void initialize(UsernameUnique constraintAnnotation) {
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if ( value == null ) {
            return true;
        }
//        if(userService == null) {
//            return true;
//        }
        return userService.findByUserName(value) == null;
    }
}
