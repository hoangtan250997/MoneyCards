package com.hoangtan.moneycards.service.mapper;

import com.hoangtan.moneycards.entity.IncomeSource;
import com.hoangtan.moneycards.entity.MoneyCard;
import com.hoangtan.moneycards.service.model.IncomeSourceDTO;
import com.hoangtan.moneycards.service.model.MoneyCardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MoneyCardMapper extends BaseMapper<MoneyCard, MoneyCardDTO> {

    @Override
    @Mapping(target = "userId", source = "user.id")
    MoneyCardDTO toDTO(MoneyCard moneyCard);

}
