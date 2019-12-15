package br.com.annotation;

import br.com.domain.OrderStatus;
import br.com.domain.OrderVO;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

@Slf4j
public class OrderValidator implements ConstraintValidator<OrderValid, OrderVO> {
    @Override
    public boolean isValid(OrderVO order, ConstraintValidatorContext constraintValidatorContext) {
        final Stream<Field> fields = getClassFeilds(order);

        if (isObjectValid(order)){
            return false;
        }

        constraintValidatorContext.disableDefaultConstraintViolation();
        AtomicBoolean isValid = new AtomicBoolean(true);
        fields.forEach(field -> {
            log.debug("field  name {}", field.getName());
            if(isContainsStatus(field, order.getStatusCurrent()) && isAttributeNull(order, field.getName())){
                isValid.set(false);
                constraintValidatorContext.buildConstraintViolationWithTemplate(formatMessage(field))
                    .addPropertyNode(field.getName())
                    .addConstraintViolation();
            }
        });

        return isValid.get();
    }

    private String formatMessage(final Field field){
        final String message = field.getAnnotation(RequiredAttributeStatus.class).message();
        return String.format(message, field.getName());
    }

    private Stream<Method> getClassMethods(final OrderVO order) {
        return Stream.of(order.getClass().getMethods());
    }

    private Stream<Field> getClassFeilds(final OrderVO order) {
        return Stream.of(order.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(RequiredAttributeStatus.class));
    }

    private boolean isObjectValid(final OrderVO order){
        return Objects.isNull(order) && order.isStatusCurrent();
    }

    private boolean isContainsStatus(final Field field, final String statusCurrent ){
        final OrderStatus orderStatus = OrderStatus.getEnum(statusCurrent);
        final Stream<OrderStatus> statuses = Stream.of(field.getAnnotation(RequiredAttributeStatus.class).statuses());
        return statuses.filter(status -> status.equals(orderStatus)).count() == 1;
    }
    
    private boolean isMethod(final Method method, final String fieldName){
        final String fieldNameTreated = "get" + fieldName.toLowerCase();
        return method.getName().toLowerCase().equals(fieldNameTreated);
    }

    private boolean isObjectNull(final Method method, final OrderVO order){
        try {
            final Object object = method.invoke(order);
            return Objects.isNull(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return false;
        }
    }
    private boolean isAttributeNull(final OrderVO order, final String fieldName){
        return getClassMethods(order)
                .filter(method -> isMethod(method, fieldName))
                .findFirst()
                .map(method -> {
                    return isObjectNull(method,order);
                }).orElseGet(() -> {
                    return false;
                });
    }
}
