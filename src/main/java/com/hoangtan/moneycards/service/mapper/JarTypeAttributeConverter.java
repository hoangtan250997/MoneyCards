package com.hoangtan.moneycards.service.mapper;

import com.hoangtan.moneycards.entity.JarType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class JarTypeAttributeConverter implements AttributeConverter<JarType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(JarType jarType) {
        return (jarType != null)?  jarType.getValue() : null;
    }

    @Override
    public JarType convertToEntityAttribute(Integer dbData) {
        return Arrays.stream(JarType.values())
                .filter(each -> each.getValue() == dbData)
                .findFirst()
                .orElse(null);
    }
}
