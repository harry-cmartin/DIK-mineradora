package com.dikmineradora.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
@AllArgsConstructor
public class CurrentPriceDto {

    @JsonProperty("USDBRL")
    public USDBRL USDBRL;

}
