package com.bbsw.myFirstApi.supplier.repository;

import com.bbsw.myFirstApi.item.model.ItemData;
import com.bbsw.myFirstApi.supplier.model.SupplierData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierDataRepository extends JpaRepository<SupplierData, Long> {


    @Query("""
        select supplier
        from SupplierData supplier
        left join supplier.itemsData item with item = :item
        where supplier.name in (:names) and item is null
        """)
    List<SupplierData> findSupplierNamesNotItem(List<String> names, ItemData item);

    List<SupplierData> findByItemsData(ItemData itemData);

}
