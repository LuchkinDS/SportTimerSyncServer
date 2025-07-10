package ru.luchkinds.sporttimersyncserver.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, String> {
    private List<String> acceptedValues;

    @Override
    public boolean isValid(String o, ConstraintValidatorContext constraintValidatorContext) {
        return acceptedValues.contains(o);
    }

    @Override
    public void initialize(ValueOfEnum constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.value().getEnumConstants())
                .map(Enum::name)
                .toList();
    }
}
