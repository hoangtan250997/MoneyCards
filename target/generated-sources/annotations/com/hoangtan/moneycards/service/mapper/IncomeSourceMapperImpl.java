package com.hoangtan.moneycards.service.mapper;

import com.hoangtan.moneycards.entity.IncomeSource;
import com.hoangtan.moneycards.entity.User;
import com.hoangtan.moneycards.service.model.IncomeSourceDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-23T16:46:48+0700",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@ApplicationScoped
public class IncomeSourceMapperImpl implements IncomeSourceMapper {

    @Override
    public List<IncomeSourceDTO> toDTOList(List<IncomeSource> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<IncomeSourceDTO> list = new ArrayList<IncomeSourceDTO>( entityList.size() );
        for ( IncomeSource incomeSource : entityList ) {
            list.add( toDTO( incomeSource ) );
        }

        return list;
    }

    @Override
    public IncomeSource toEntity(IncomeSourceDTO DTO) {
        if ( DTO == null ) {
            return null;
        }

        IncomeSource.IncomeSourceBuilder incomeSource = IncomeSource.builder();

        incomeSource.id( DTO.getId() );
        incomeSource.name( DTO.getName() );
        incomeSource.balance( DTO.getBalance() );

        return incomeSource.build();
    }

    @Override
    public List<IncomeSource> toEntityList(List<IncomeSourceDTO> DTOList) {
        if ( DTOList == null ) {
            return null;
        }

        List<IncomeSource> list = new ArrayList<IncomeSource>( DTOList.size() );
        for ( IncomeSourceDTO incomeSourceDTO : DTOList ) {
            list.add( toEntity( incomeSourceDTO ) );
        }

        return list;
    }

    @Override
    public IncomeSourceDTO toDTO(IncomeSource incomeSource) {
        if ( incomeSource == null ) {
            return null;
        }

        IncomeSourceDTO.IncomeSourceDTOBuilder incomeSourceDTO = IncomeSourceDTO.builder();

        incomeSourceDTO.userId( incomeSourceUserId( incomeSource ) );
        incomeSourceDTO.id( incomeSource.getId() );
        incomeSourceDTO.name( incomeSource.getName() );
        incomeSourceDTO.balance( incomeSource.getBalance() );

        return incomeSourceDTO.build();
    }

    private Long incomeSourceUserId(IncomeSource incomeSource) {
        if ( incomeSource == null ) {
            return null;
        }
        User user = incomeSource.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
