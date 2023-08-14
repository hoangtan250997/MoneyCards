package com.hoangtan.moneycards.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    @CreationTimestamp
    private LocalDate assignedTime;

    @ManyToOne
    @JoinColumn(name="moneyJar_id")
    private MoneyCard moneyCard;

    @ManyToOne
    @JoinColumn(name = "incomeSource_id")
    private IncomeSource incomeSource;

}
