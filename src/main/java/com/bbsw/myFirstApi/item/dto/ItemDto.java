package com.bbsw.myFirstApi.item.dto;

import com.bbsw.myFirstApi.item.enums.StateEnum;
import com.bbsw.myFirstApi.pricereduction.dto.PriceReductionDto;
import com.bbsw.myFirstApi.supplier.dto.SupplierDTO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ItemDto {

    String code;
    String description;

    BigDecimal price;

    StateEnum state;
    LocalDate creationDate;

    String username;

    private List<PriceReductionDto> priceReductions;

    List<SupplierDTO> suppliersData;

}
