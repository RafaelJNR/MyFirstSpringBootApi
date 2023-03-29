package com.bbsw.myFirstApi.item.service;

import com.bbsw.myFirstApi.item.dto.ItemDto;
import com.bbsw.myFirstApi.item.model.ItemData;
import com.bbsw.myFirstApi.item.repository.ItemDataRepository;
import com.bbsw.myFirstApi.pricereduction.dto.PriceReductionDto;
import com.bbsw.myFirstApi.pricereduction.model.PriceReductionData;
import com.bbsw.myFirstApi.supplier.dto.SupplierDTO;
import com.bbsw.myFirstApi.supplier.model.SupplierData;
import com.bbsw.myFirstApi.supplier.repository.SupplierDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;


@Service
public class ItemService {

    @Autowired
    private ItemDataRepository itemDataRepository;

    @Autowired
    private SupplierDataRepository supplierDataRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<ItemDto> findAllItems(){

        List<ItemData> itemsData = itemDataRepository.findAll();

        return itemsData.stream().map(itemData -> {
            ItemDto itemDTO = new ItemDto();
            itemDTO.setCode(itemData.getCode());
            itemDTO.setPrice(itemData.getPrice());
            itemDTO.setDescription(itemData.getDescription());
            itemDTO.setUsername(itemData.getUserName());
            itemDTO.setCreationDate(itemData.getCreationDate());
            itemDTO.setState(itemData.getState());
            return itemDTO;
        }).toList();
    }

    public ItemDto findItem(String code){

        ItemDto itemDto= new ItemDto();
        ItemData itemData = itemDataRepository.findByCode(code);

        itemDto.setCode(itemData.getCode());
        itemDto.setUsername(itemData.getUserName());
        itemDto.setPrice(itemData.getPrice());
        itemDto.setDescription(itemData.getDescription());
        itemDto.setCreationDate(itemData.getCreationDate());

        itemDto.setSuppliersData(itemData.getSuppliersData().stream().map(supplierData ->{
            SupplierDTO supplierDTO = new SupplierDTO();
            supplierDTO.setCountry(supplierData.getCountry());
            supplierDTO.setName(supplierData.getName());
            /*supplierDTO = modelMapper.map(this, SupplierDTO.class);*/
            return supplierDTO;
                }
        ).toList());

        itemDto.setPriceReductions(itemData.getPriceReductions().stream().map(priceReductionData ->{
                    PriceReductionDto priceReductionDto = new PriceReductionDto();
                    priceReductionDto.setReducedPrice(priceReductionData.getReducedPrice());
                    priceReductionDto.setStartDate(priceReductionData.getStartDate());
                    priceReductionDto.setEndDate(priceReductionData.getEndDate());
                    return priceReductionDto;
                }
            //modelMapper.map(this, PriceReductionDto.class)
        ).toList());
        return itemDto;
    }

    public ItemDto createItem(ItemDto itemDto){

        if (itemDataRepository.findByCode(itemDto.getCode()) == null){
            ItemData itemData = new ItemData();
            itemData.setCode(itemDto.getCode());
            itemData.setCreationDate(LocalDate.now());
            itemData.setState(itemDto.getState());
            itemData.setPrice(itemDto.getPrice());
            itemData.setDescription(itemDto.getDescription());
            itemData.setUserName(itemDto.getUsername());
           /* itemData.setSuppliersData(itemDto.getSuppliersData());
            itemData.setPriceReductions(itemDto.getPriceReductions());*/

            itemDataRepository.save(itemData);
            return itemDto;
        }else{
            return itemDto;
        }

    }

    public ItemDto createUpdateItem(ItemDto itemDto, List<SupplierDTO> suppliersDTO){

        if(itemDto == null){
            throw new RuntimeException("error");        //OJO, TE DIJO RAYCO QUE CREARAS TU PROPIA EXCEPCION
        }

        ItemData itemData = itemDataRepository.findByCode(itemDto.getCode());

        List<SupplierData> suppliers = supplierDataRepository.findByNameIn(suppliersDTO.stream().map(SupplierDTO::getName).toList());

        if (itemData == null){

            itemData = new ItemData();
            itemData.setCode(itemDto.getCode());
            itemData.setCreationDate(LocalDate.now());
        }

        itemData.setCreationDate(itemDto.getCreationDate());
        itemData.setState(itemDto.getState());
        itemData.setPrice(itemDto.getPrice());
        itemData.setDescription(itemDto.getDescription());
        itemData.setUserName(itemDto.getUsername());
        itemData.addSuppliers(suppliers);
        itemData.setState(itemDto.getState());

        if(itemDto.getPriceReductions() != null && !itemDto.getPriceReductions().isEmpty()){
            itemData.addPriceReductions(itemDto.getPriceReductions().stream().map(priceReductionDto ->
                    new PriceReductionData(null, priceReductionDto.getReducedPrice(),
                            priceReductionDto.getStartDate(), priceReductionDto.getEndDate(), null)
            ).toList());
        }

        if (itemDto.getSuppliersData()==null && itemDto.getSuppliersData().isEmpty()){
            itemData.addSuppliers(itemDto.getSuppliersData().stream().map(supplierDTO ->
                    new SupplierData(null, supplierDTO.getName(), supplierDTO.getCountry(), null)
                    ).toList());
        }

        itemDataRepository.save(itemData);
        supplierDataRepository.saveAll(suppliers);
        return new ItemDto();
    }

    public void deleteItemByCode(ItemDto itemDto){

        String code = itemDto.getCode();
        ItemData itemData = itemDataRepository.findByCode(code);
        itemData.getSuppliersData().forEach(supplierData ->
                supplierData.getItemsData().remove(itemData));
        itemDataRepository.delete(itemData);
    }
}