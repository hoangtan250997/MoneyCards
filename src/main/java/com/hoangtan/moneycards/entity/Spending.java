package com.hoangtan.moneycards.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    //    @CreationTimestamp
    //    Cho trường hợp là nếu người dùng quên nhập thì ngày khác có thể nhập"
    private LocalDate spendingTime;
    private String purpose;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "moneyJar_id")
    private MoneyJar moneyJar;
}
