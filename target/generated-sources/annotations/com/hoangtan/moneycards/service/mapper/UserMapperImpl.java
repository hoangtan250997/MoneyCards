package com.hoangtan.moneycards.service.mapper;

import com.hoangtan.moneycards.entity.User;
import com.hoangtan.moneycards.service.model.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-15T23:35:01+0700",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@ApplicationScoped
public class UserMapperImpl implements UserMapper {

    @Override
    public List<UserDTO> toDTOList(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDTO( user ) );
        }

        return list;
    }

    @Override
    public User toEntity(UserDTO DTO) {
        if ( DTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( DTO.getId() );
        user.name( DTO.getName() );
        user.email( DTO.getEmail() );
        user.password( DTO.getPassword() );
        user.status( DTO.getStatus() );
        user.role( DTO.getRole() );

        return user.build();
    }

    @Override
    public List<User> toEntityList(List<UserDTO> DTOList) {
        if ( DTOList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( DTOList.size() );
        for ( UserDTO userDTO : DTOList ) {
            list.add( toEntity( userDTO ) );
        }

        return list;
    }

    @Override
    public UserDTO toDTO(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( entity.getId() );
        userDTO.name( entity.getName() );
        userDTO.email( entity.getEmail() );
        userDTO.status( entity.getStatus() );
        userDTO.role( entity.getRole() );

        return userDTO.build();
    }
}
