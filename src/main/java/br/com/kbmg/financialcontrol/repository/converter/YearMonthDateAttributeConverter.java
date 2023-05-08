package br.com.kbmg.financialcontrol.repository.converter;

import jakarta.persistence.AttributeConverter;

import java.time.YearMonth;

public class YearMonthDateAttributeConverter
        implements AttributeConverter<YearMonth, String> {

    @Override
    public String convertToDatabaseColumn(
            YearMonth attribute) {
        if (attribute != null) {
            return attribute.toString();
        }
        return null;
    }

    @Override
    public YearMonth convertToEntityAttribute(
            String dbData) {
        if (dbData != null) {
            return YearMonth.parse(dbData);
        }
        return null;
    }
}
