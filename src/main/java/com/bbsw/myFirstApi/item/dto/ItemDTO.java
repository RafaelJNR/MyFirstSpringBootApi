package com.bbsw.myFirstApi.item.dto;

import com.bbsw.myFirstApi.item.enums.StateEnum;
import com.bbsw.myFirstApi.pricereduction.model.PriceReductionData;
import com.bbsw.myFirstApi.supplier.model.SupplierData;
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

    String userName;

    private List<PriceReductionData> priceReductions;

    List<SupplierData> suppliersData;

}
