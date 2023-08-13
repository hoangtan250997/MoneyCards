package com.hoangtan.moneycards.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hoangtan.moneycards.entity.JarType;
import com.hoangtan.moneycards.service.mapper.JarTypeAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoneyCardDTO {

    private Long id;
    @Convert(converter = JarTypeAttributeConverter.class)
    private JarType jarType;

    private Double balance;

    private Double percentage;

    private Long userId;
}
