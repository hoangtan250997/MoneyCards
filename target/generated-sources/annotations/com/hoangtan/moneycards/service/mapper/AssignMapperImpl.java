package com.hoangtan.moneycards.service.mapper;

import com.hoangtan.moneycards.entity.Assign;
import com.hoangtan.moneycards.entity.IncomeSource;
import com.hoangtan.moneycards.entity.MoneyCard;
import com.hoangtan.moneycards.service.model.AssignDTO;
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
public class AssignMapperImpl implements AssignMapper {

    @Override
    public List<AssignDTO> toDTOList(List<Assign> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AssignDTO> list = new ArrayList<AssignDTO>( entityList.size() );
        for ( Assign assign : entityList ) {
            list.add( toDTO( assign ) );
        }

        return list;
    }

    @Override
    public Assign toEntity(AssignDTO DTO) {
        if ( DTO == null ) {
            return null;
        }

        Assign.AssignBuilder assign = Assign.builder();

        assign.id( DTO.getId() );
        assign.amount( DTO.getAmount() );
        assign.assignedTime( DTO.getAssignedTime() );

        return assign.build();
    }

    @Override
    public List<Assign> toEntityList(List<AssignDTO> DTOList) {
        if ( DTOList == null ) {
            return null;
        }

        List<Assign> list = new ArrayList<Assign>( DTOList.size() );
        for ( AssignDTO assignDTO : DTOList ) {
            list.add( toEntity( assignDTO ) );
        }

        return list;
    }

    @Override
    public AssignDTO toDTO(Assign assign) {
        if ( assign == null ) {
            return null;
        }

        AssignDTO assignDTO = new AssignDTO();

        assignDTO.setMoneyCardId( assignMoneyCardId( assign ) );
        assignDTO.setIncomeSourceId( assignIncomeSourceId( assign ) );
        assignDTO.setId( assign.getId() );
        assignDTO.setAmount( assign.getAmount() );
        assignDTO.setAssignedTime( assign.getAssignedTime() );

        return assignDTO;
    }

    private Long assignMoneyCardId(Assign assign) {
        if ( assign == null ) {
            return null;
        }
        MoneyCard moneyCard = assign.getMoneyCard();
        if ( moneyCard == null ) {
            return null;
        }
        Long id = moneyCard.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long assignIncomeSourceId(Assign assign) {
        if ( assign == null ) {
            return null;
        }
        IncomeSource incomeSource = assign.getIncomeSource();
        if ( incomeSource == null ) {
            return null;
        }
        Long id = incomeSource.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
