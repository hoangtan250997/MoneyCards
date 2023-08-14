package com.hoangtan.moneycards.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignDTO {

    private Long id;

    private Double amount;
    private LocalDateTime assignedTime;

    private Long moneyCardId;

    private Long incomeSourceId;

}
