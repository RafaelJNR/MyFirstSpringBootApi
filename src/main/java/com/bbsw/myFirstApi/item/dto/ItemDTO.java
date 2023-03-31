package com.bbsw.myFirstApi.item.dto;

import com.bbsw.myFirstApi.item.enums.StateEnum;
import com.bbsw.myFirstApi.pricereduction.dto.PriceReductionDto;
import com.bbsw.myFirstApi.supplier.dto.SupplierDTO;
import com.bbsw.myFirstApi.user.dto.UserDTO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ItemDTO {

    String code;
    String description;

    BigDecimal price;

    StateEnum state;
    LocalDate creationDate;

    String username;
    List<UserDTO> users;

    private List<PriceReductionDto> priceReductions;

    List<SupplierDTO> suppliersData;

}
