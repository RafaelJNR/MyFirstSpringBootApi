package com.bbsw.myFirstApi.supplier.service;


import com.bbsw.myFirstApi.supplier.model.SupplierData;
import com.bbsw.myFirstApi.supplier.repository.SupplierDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierDataRepository supplierDataRepository;

    public List<String> findAllSuppliers(){

        List<SupplierData> suppliersData = supplierDataRepository.findAll();

        return suppliersData.stream().map(supplierData -> {
            String name;
            name=(supplierData.getName());
            return name;
        }).toList();

    }

}
