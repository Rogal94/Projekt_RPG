package pl.coderslab.Projekt_RPG.validation;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = UsernameUniqueValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface UsernameUnique {
    String message() default "{UsernameUnique.error.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
