package com.dikmineradora.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "quotation")
@Data
@NoArgsConstructor
public class QuotationEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @Column(name = "current_price")
    private BigDecimal currentPrice;

    @Column(name = "pct_change")
    private String pctChange;

    private String pair;




}
