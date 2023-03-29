package com.bbsw.myFirstApi.supplier.repository;

import com.bbsw.myFirstApi.item.model.ItemData;
import com.bbsw.myFirstApi.supplier.model.SupplierData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierDataRepository extends JpaRepository<SupplierData, Long> {

    List<SupplierData> findByNameIn(List<String> names);

    List<SupplierData> findByItemsData(ItemData itemData);

}
