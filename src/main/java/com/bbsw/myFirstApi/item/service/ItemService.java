package com.bbsw.myFirstApi.item.service;

import com.bbsw.myFirstApi.item.dto.ItemDTO;
import com.bbsw.myFirstApi.item.model.ItemData;
import com.bbsw.myFirstApi.item.repository.ItemDataRepository;
import com.bbsw.myFirstApi.supplier.dto.SupplierDto;
import com.bbsw.myFirstApi.supplier.model.SupplierData;
import com.bbsw.myFirstApi.supplier.repository.SupplierDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ItemService {

    @Autowired
    private ItemDataRepository itemDataRepository;
    private SupplierDataRepository supplierDataRepository;

    public List<ItemDTO> findAllItems(){

        List<ItemData> itemsData = itemDataRepository.findAll();
        return itemsData.stream().map(itemData -> {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setPriceReductions(itemData.getPriceReductions());
            itemDTO.setSuppliersData(itemData.getSuppliersData());
            itemDTO.setCode(itemData.getCode());
            itemDTO.setPrice(itemData.getPrice());
            itemDTO.setDescription(itemData.getDescription());
            itemDTO.setUserName(itemData.getUserName());
            itemDTO.setCreationDate(itemData.getCreationDate());
            itemDTO.setState(itemData.getState());
            return itemDTO;
        }).toList();
    }

    public ItemDTO findItem(String code){

        ItemDTO itemDto= new ItemDTO();
        ItemData itemData = itemDataRepository.findByCode(code);

        itemDto.setCode(itemData.getCode());
        itemDto.setUserName(itemData.getUserName());
        itemDto.setPrice(itemData.getPrice());
        itemDto.setDescription(itemData.getDescription());
        itemDto.setCreationDate(itemData.getCreationDate());
        itemDto.setPriceReductions(itemData.getPriceReductions());
        itemDto.setSuppliersData(itemData.getSuppliersData());

        return itemDto;
    }

    public ItemData createItem(ItemDTO itemDto){

        if (itemDataRepository.findByCode(itemDto.getCode()) == null){
            ItemData itemData = new ItemData();
            itemData.setCode(itemDto.getCode());
            itemData.setCreationDate(itemDto.getCreationDate());
            itemData.setState(itemDto.getState());
            itemData.setPrice(itemDto.getPrice());
            itemData.setDescription(itemDto.getDescription());
            itemData.setUserName(itemDto.getUserName());
            itemData.setSuppliersData(itemDto.getSuppliersData());
            itemData.setPriceReductions(itemDto.getPriceReductions());

            return itemDataRepository.save(itemData);
        }else{
            return null;
        }

    }

    public ItemDTO updateItem(ItemDTO itemDto, SupplierDto supplierDto){

        if(supplierDto == null || itemDto == null){
            throw new RuntimeException("error");
        }

        ItemData itemData = itemDataRepository.findByCode(itemDto.getCode());
        if (itemData == null){

            itemData = new ItemData();
            itemData.setCode(itemDto.getCode());

        }
        itemData.setCreationDate(itemDto.getCreationDate());
        itemData.setState(itemDto.getState());
        itemData.setPrice(itemDto.getPrice());
        itemData.setDescription(itemDto.getDescription());
        itemData.setUserName(itemDto.getUserName());
        itemData.setSuppliersData(itemDto.getSuppliersData());//TODO esto no pueden ser POJOS
        itemData.setPriceReductions(itemDto.getPriceReductions());
        itemDataRepository.save(itemData);

        //elimiar supplier
        Optional<SupplierData> supplierDataOptional =  itemData.getSuppliersData().stream()
                .findFirst()
                .filter(supplierData -> supplierData.getName().equalsIgnoreCase(supplierDto.getName()));
        if(supplierDataOptional.isEmpty()){
            throw new RuntimeException("error");
        }
        SupplierData supplierData = supplierDataOptional.get();
        itemData.getSuppliersData().remove(supplierData);
        supplierData.getItemsData().remove(itemData);

        itemDataRepository.save(itemData);
        supplierDataRepository.save(supplierData);


        ////
        return new ItemDTO();

    }

    public void deleteItemByCode(ItemDTO itemDto){

        String code = itemDto.getCode();
        ItemData itemData = itemDataRepository.findByCode(code);

        itemDataRepository.delete(itemData);}
}
