package com.hoangtan.moneycards.service.mapper;

import com.hoangtan.moneycards.entity.Assign;
import com.hoangtan.moneycards.entity.Spending;
import com.hoangtan.moneycards.service.model.AssignDTO;
import com.hoangtan.moneycards.service.model.SpendingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SpendingMapper extends BaseMapper<Spending, SpendingDTO> {

    @Override
    @Mapping(target = "moneyCardId", source = "moneyCard.id")
    @Mapping(target = "userId", source = "user.id")
    SpendingDTO toDTO(Spending spending);

}
