package com.hoangtan.moneycards.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncomeSourceDTO {

    private Long id;

    private String name;

    private double balance;
    private Long userId;



}
