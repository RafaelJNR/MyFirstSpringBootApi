package com.bbsw.myFirstApi.supplier.repository;

import com.bbsw.myFirstApi.supplier.model.SupplierData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierDataRepository extends JpaRepository<SupplierData, Long> {

}
