package com.hoangtan.moneycards.service.mapper;

import com.hoangtan.moneycards.entity.MoneyCard;
import com.hoangtan.moneycards.entity.Spending;
import com.hoangtan.moneycards.entity.User;
import com.hoangtan.moneycards.service.model.SpendingDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-15T01:42:47+0700",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@ApplicationScoped
public class SpendingMapperImpl implements SpendingMapper {

    @Override
    public List<SpendingDTO> toDTOList(List<Spending> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SpendingDTO> list = new ArrayList<SpendingDTO>( entityList.size() );
        for ( Spending spending : entityList ) {
            list.add( toDTO( spending ) );
        }

        return list;
    }

    @Override
    public Spending toEntity(SpendingDTO DTO) {
        if ( DTO == null ) {
            return null;
        }

        Spending.SpendingBuilder spending = Spending.builder();

        spending.id( DTO.getId() );
        spending.amount( DTO.getAmount() );
        spending.spendingTime( DTO.getSpendingTime() );
        spending.purpose( DTO.getPurpose() );

        return spending.build();
    }

    @Override
    public List<Spending> toEntityList(List<SpendingDTO> DTOList) {
        if ( DTOList == null ) {
            return null;
        }

        List<Spending> list = new ArrayList<Spending>( DTOList.size() );
        for ( SpendingDTO spendingDTO : DTOList ) {
            list.add( toEntity( spendingDTO ) );
        }

        return list;
    }

    @Override
    public SpendingDTO toDTO(Spending spending) {
        if ( spending == null ) {
            return null;
        }

        SpendingDTO spendingDTO = new SpendingDTO();

        spendingDTO.setMoneyCardId( spendingMoneyCardId( spending ) );
        spendingDTO.setUserId( spendingUserId( spending ) );
        spendingDTO.setId( spending.getId() );
        spendingDTO.setAmount( spending.getAmount() );
        spendingDTO.setSpendingTime( spending.getSpendingTime() );
        spendingDTO.setPurpose( spending.getPurpose() );

        return spendingDTO;
    }

    private Long spendingMoneyCardId(Spending spending) {
        if ( spending == null ) {
            return null;
        }
        MoneyCard moneyCard = spending.getMoneyCard();
        if ( moneyCard == null ) {
            return null;
        }
        Long id = moneyCard.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long spendingUserId(Spending spending) {
        if ( spending == null ) {
            return null;
        }
        User user = spending.getUser();
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
