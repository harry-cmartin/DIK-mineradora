package com.dikmineradora.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "records")
@Data
@NoArgsConstructor
public class RecordsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Date date;

    @Column(name = "proposal_id")
    private Long proposalid;

    private String costumer;

    @Column(name = "price_tonne")
    private BigDecimal priceTonne;


    @Column(name = "last_current_cottation")
    private BigDecimal lastDollarCottation;

}
