package com.hoangtan.moneycards.service.mapper;

import com.hoangtan.moneycards.entity.Assign;
import com.hoangtan.moneycards.service.model.AssignDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AssignMapper extends BaseMapper<Assign, AssignDTO> {

    @Override
    @Mapping(target = "moneyCardId", source = "moneyCard.id")
    @Mapping(target = "incomeSourceId", source = "incomeSource.id")
    AssignDTO toDTO(Assign assign);

}
