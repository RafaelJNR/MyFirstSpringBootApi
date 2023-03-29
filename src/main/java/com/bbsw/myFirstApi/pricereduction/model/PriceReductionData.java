package com.bbsw.myFirstApi.pricereduction.model;


import com.bbsw.myFirstApi.item.model.ItemData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

@Table(name="reducedpricedata", schema = "erp")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceReductionData {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "reducedprice_id_seq")
    @SequenceGenerator(name = "reducedprice_id_seq", sequenceName = "reducedprice_id_seq", allocationSize = 1, schema = "erp")
    @Column(name = "idreducedprice")
    Long idReducedPrice;
    @Column(name ="reducedprice")
    BigInteger reducedPrice;
    @Column(name = "startdate")
    LocalDate startDate;
    @Column(name = "enddate")
    LocalDate endDate;

    @ManyToOne
    @JoinColumn(name="itemdata")
    @JsonBackReference("itemPriceReduction")
    private ItemData itemdata;

}
