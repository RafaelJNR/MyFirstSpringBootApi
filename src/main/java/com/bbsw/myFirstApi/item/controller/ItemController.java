package com.bbsw.myFirstApi.item.controller;

import com.bbsw.myFirstApi.item.dto.ItemDTO;
import com.bbsw.myFirstApi.item.service.ItemService;
import com.bbsw.myFirstApi.supplier.dto.SupplierDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/itemdata/getall")
    public ResponseEntity<?> getAllItemsData(){

        List<ItemDTO> itemsData = itemService.findAllItems();
        try{
            return ResponseEntity.ok(itemService.findAllItems());
        }catch(Exception e){
            System.out.println("Error de Servidor");
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/itemdata")
    public ResponseEntity<ItemDTO> getItemData(@RequestParam String code){
        try{
            return ResponseEntity.ok(itemService.findItem(code));
        }catch(Exception e){
            System.out.println("Error de Servidor");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/itemdata")
    public ResponseEntity<?> deleteItem(@RequestParam String code){

        try{

            ItemDTO itemDto = itemService.findItem(code);
            itemService.deleteItemByCode(itemDto);

            return ResponseEntity.noContent().build();

        }catch(Exception e){
            System.out.println("Server error.");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/itemdata")
    public ResponseEntity<?> createItem(@RequestBody ItemDTO itemDto){

        if (itemService.createItem(itemDto)==null){
            return ResponseEntity.status(HttpStatus.CREATED).body(itemDto);

        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(itemDto);
        }
    }

    @PutMapping("/itemdata")
    public ResponseEntity<?> updateItem(@RequestBody ItemDTO itemDto, SupplierDto supplierDto){

            if(itemService.updateItem(itemDto, supplierDto)==null){
                return ResponseEntity.status(HttpStatus.FOUND).body(itemDto);
            }else{
                return ResponseEntity.status(HttpStatus.CREATED).body(itemDto);
            }

}
}
