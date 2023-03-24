package com.bbsw.myFirstApi.item.repository;

import com.bbsw.myFirstApi.item.model.ItemData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemDataRepository extends JpaRepository<ItemData, Long>{

    ItemData findBySuppliersDataNameIn(List<String> supplierName);

    ItemData findByCode(String code);

    Optional<ItemData> deleteByCode(String code);
}
