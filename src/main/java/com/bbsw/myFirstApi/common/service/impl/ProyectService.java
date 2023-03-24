package com.bbsw.myFirstApi.common.service.impl;


import com.bbsw.myFirstApi.common.service.IProyectService;
import com.bbsw.myFirstApi.item.repository.ItemDataRepository;
import com.bbsw.myFirstApi.pricereduction.repository.PriceReductionRepository;
import com.bbsw.myFirstApi.supplier.repository.SupplierDataRepository;
import com.bbsw.myFirstApi.user.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProyectService implements IProyectService {

    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    ItemDataRepository itemDataRepository ;

    @Autowired
    SupplierDataRepository supplierDataRepository;

    @Autowired
    PriceReductionRepository priceReductionRepository;

    @Override
    public String concatParam(String type, String data) {
        return "hello world " + type +
                data;
    }




}
