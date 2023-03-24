package com.bbsw.myFirstApi.supplier.model;

import com.bbsw.myFirstApi.item.model.ItemData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name ="supplierdata", schema ="erp")
@Entity
@Getter
@Setter

public class SupplierData {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "supplier_id_seq")
    @SequenceGenerator(name = "supplier_id_seq", sequenceName = "supplier_id_seq", allocationSize = 1, schema = "erp")
    @Column(name = "idsupplier")
    long idSupplier;
    @Column(unique = true, nullable = false)
    String name;
    String country;

    @ManyToMany(mappedBy = "suppliersData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    List<ItemData> itemsData;

}
