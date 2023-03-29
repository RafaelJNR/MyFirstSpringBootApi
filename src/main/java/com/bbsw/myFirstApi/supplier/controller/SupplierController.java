package com.bbsw.myFirstApi.supplier.controller;

import com.bbsw.myFirstApi.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/supplier/getall")
    public ResponseEntity<?> getAllSuppliers(){

        try{
            return ResponseEntity.ok(supplierService.findAllSuppliers());
        }catch(Exception e){
            System.out.println("Error de Servidor");
        }
        return ResponseEntity.notFound().build();
    }

}
