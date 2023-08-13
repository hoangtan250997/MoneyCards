package com.hoangtan.moneycards.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hoangtan.moneycards.service.mapper.JarTypeAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoneyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = JarTypeAttributeConverter.class)
    private JarType jarType;

    private Double balance;

    private Double percentage;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
