package ru.luchkinds.sporttimersyncserver.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, String> {
    @Override
    public String convertToDatabaseColumn(LocalDate localDate) {
        return localDate == null ? null : localDate.toString();
    }

    @Override
    public LocalDate convertToEntityAttribute(String s) {
        return s ==  null ? null : LocalDate.parse(s);
    }
}
