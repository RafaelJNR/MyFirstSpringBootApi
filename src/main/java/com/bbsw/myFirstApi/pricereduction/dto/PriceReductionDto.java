package com.bbsw.myFirstApi.pricereduction.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
public class PriceReductionDto {

    BigInteger reducedPrice;
    LocalDate startDate;
    LocalDate endDate;
}
