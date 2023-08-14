package com.hoangtan.moneycards.service.mapper;

import com.hoangtan.moneycards.entity.MoneyCard;
import com.hoangtan.moneycards.entity.User;
import com.hoangtan.moneycards.service.model.MoneyCardDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-15T01:10:34+0700",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@ApplicationScoped
public class MoneyCardMapperImpl implements MoneyCardMapper {

    @Override
    public List<MoneyCardDTO> toDTOList(List<MoneyCard> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MoneyCardDTO> list = new ArrayList<MoneyCardDTO>( entityList.size() );
        for ( MoneyCard moneyCard : entityList ) {
            list.add( toDTO( moneyCard ) );
        }

        return list;
    }

    @Override
    public MoneyCard toEntity(MoneyCardDTO DTO) {
        if ( DTO == null ) {
            return null;
        }

        MoneyCard.MoneyCardBuilder moneyCard = MoneyCard.builder();

        moneyCard.id( DTO.getId() );
        moneyCard.jarType( DTO.getJarType() );
        moneyCard.balance( DTO.getBalance() );
        moneyCard.percentage( DTO.getPercentage() );

        return moneyCard.build();
    }

    @Override
    public List<MoneyCard> toEntityList(List<MoneyCardDTO> DTOList) {
        if ( DTOList == null ) {
            return null;
        }

        List<MoneyCard> list = new ArrayList<MoneyCard>( DTOList.size() );
        for ( MoneyCardDTO moneyCardDTO : DTOList ) {
            list.add( toEntity( moneyCardDTO ) );
        }

        return list;
    }

    @Override
    public MoneyCardDTO toDTO(MoneyCard moneyCard) {
        if ( moneyCard == null ) {
            return null;
        }

        MoneyCardDTO moneyCardDTO = new MoneyCardDTO();

        moneyCardDTO.setUserId( moneyCardUserId( moneyCard ) );
        moneyCardDTO.setId( moneyCard.getId() );
        moneyCardDTO.setJarType( moneyCard.getJarType() );
        moneyCardDTO.setBalance( moneyCard.getBalance() );
        moneyCardDTO.setPercentage( moneyCard.getPercentage() );

        return moneyCardDTO;
    }

    private Long moneyCardUserId(MoneyCard moneyCard) {
        if ( moneyCard == null ) {
            return null;
        }
        User user = moneyCard.getUser();
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
