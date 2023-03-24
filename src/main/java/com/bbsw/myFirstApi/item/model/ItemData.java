package com.bbsw.myFirstApi.item.model;

import com.bbsw.myFirstApi.item.enums.StateEnum;
import com.bbsw.myFirstApi.pricereduction.model.PriceReductionData;
import com.bbsw.myFirstApi.supplier.model.SupplierData;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "itemdata", schema = "erp")
@Entity
@Getter
@Setter
public class ItemData {

    @Id
    @Column(name ="iditem")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "itemdata_id_seq")
    @SequenceGenerator(name = "itemdata_id_seq",sequenceName = "itemdata_id_seq", allocationSize = 1, schema = "erp")
    long idItem;

    @Column(nullable = false, unique = true)
    String code;

    BigDecimal price;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    StateEnum state;
    @Column(nullable = false)
    String description;
    @Column(name = "creationdate")
    LocalDate creationDate;
    @Column(name = "username")
    String userName;

    @OneToMany(mappedBy="itemdata", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("itemPriceReduction")
    @Column(name = "pricereduction")
    private List<PriceReductionData> priceReductions;

    @ManyToMany
    @JoinTable(
            name = "itemdata_supplierdata",
            joinColumns = @JoinColumn(name = "itemdata_id"),
            inverseJoinColumns = @JoinColumn(name = "supplierdata_id"),
            uniqueConstraints = {@UniqueConstraint(
            columnNames = {"itemdata_id", "supplierdata_id"})})
    List<SupplierData> suppliersData;

    public void addPriceReduction(PriceReductionData priceReductionData){
        if(priceReductions == null){
            priceReductions = new ArrayList<>();
        }
        priceReductionData.setItemdata(this);
        priceReductions.add(priceReductionData);
    }

    public void addPriceReductions(List<PriceReductionData> priceReductionDataList){
        priceReductionDataList.forEach(this::addPriceReduction);
    }


}