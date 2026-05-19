package com.dikmineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;


@Builder
@Data
@AllArgsConstructor
@Jacksonized
public class RecordsDto {

    private Long proposalId;

    private String costumer;

    private BigDecimal priceTonne;

    private BigDecimal lastDollarCotation;

}
