package br.com.annotation;

import br.com.domain.OrderStatus;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredAttributeStatus {
    OrderStatus[] statuses() default {};
    String message() default "O atributo '%s' deve ser preenchido";
}
