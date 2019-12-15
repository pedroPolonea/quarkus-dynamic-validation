package br.com.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OrderValidator.class)
public @interface OrderValid {
    String message() default "%s";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
