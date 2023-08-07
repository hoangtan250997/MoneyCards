package com.hoangtan.moneycards.service.mapper;

import com.hoangtan.moneycards.entity.User;
import com.hoangtan.moneycards.service.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends BaseMapper<User, UserDTO> {

    @Override
    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User entity);
}
