package com.hoangtan.moneycards.service.mapper;

import com.hoangtan.moneycards.entity.IncomeSource;
import com.hoangtan.moneycards.service.model.IncomeSourceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IncomeSourceMapper extends BaseMapper<IncomeSource, IncomeSourceDTO> {

    @Override
    @Mapping(target = "userId", source = "user.id")
    IncomeSourceDTO toDTO(IncomeSource incomeSource);

}
