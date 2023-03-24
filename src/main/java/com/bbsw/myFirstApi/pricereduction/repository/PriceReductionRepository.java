package com.bbsw.myFirstApi.pricereduction.repository;


import com.bbsw.myFirstApi.pricereduction.model.PriceReductionData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceReductionRepository extends JpaRepository<PriceReductionData, Long> {

}
