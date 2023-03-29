package com.bbsw.myFirstApi.supplier.model;

import com.bbsw.myFirstApi.item.model.ItemData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name ="supplierdata", schema ="erp")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierData {


    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "supplier_id_seq")
    @SequenceGenerator(name = "supplier_id_seq", sequenceName = "supplier_id_seq", allocationSize = 1, schema = "erp")
    @Column(name = "idsupplier")
    Long idSupplier;
    @Column(unique = true, nullable = false)
    String name;
    String country;

    @ManyToMany(mappedBy = "suppliersData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    List<ItemData> itemsData;

    public void addItem(ItemData itemData) {
        if(itemsData == null){
            itemsData = new ArrayList<>();
        }
        itemsData.add(itemData);
    }
}
