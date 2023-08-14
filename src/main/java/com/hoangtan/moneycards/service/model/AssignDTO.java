package com.hoangtan.moneycards.service.model;

import com.hoangtan.moneycards.entity.IncomeSource;
import com.hoangtan.moneycards.entity.MoneyCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignDTO {

    private Long id;

    private Double amount;
    private LocalDate assignedTime;

    private Long moneyCardId;

    private Long incomeSourceId;

}
