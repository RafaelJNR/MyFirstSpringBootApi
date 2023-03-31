package com.bbsw.myFirstApi.item.service;

import com.bbsw.myFirstApi.item.dto.DeactivateDTO;
import com.bbsw.myFirstApi.item.dto.ItemDTO;
import com.bbsw.myFirstApi.item.enums.StateEnum;
import com.bbsw.myFirstApi.item.model.Deactivate;
import com.bbsw.myFirstApi.item.model.ItemData;
import com.bbsw.myFirstApi.item.repository.DeactiveDataRepository;
import com.bbsw.myFirstApi.item.repository.ItemDataRepository;
import com.bbsw.myFirstApi.pricereduction.model.PriceReductionData;
import com.bbsw.myFirstApi.supplier.dto.SupplierDTO;
import com.bbsw.myFirstApi.supplier.model.SupplierData;
import com.bbsw.myFirstApi.supplier.repository.SupplierDataRepository;
import com.bbsw.myFirstApi.user.model.UserData;
import com.bbsw.myFirstApi.user.repository.UserDataRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class ItemService {

    @Autowired
    private ItemDataRepository itemDataRepository;
    @Autowired
    private DeactiveDataRepository deactiveDataRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private SupplierDataRepository supplierDataRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<ItemDTO> findAllItems(){
        List<ItemData> itemsData = itemDataRepository.findAll();

        return itemsData.stream().map(itemData -> modelMapper.map(itemData, ItemDTO.class)).toList();

    }

    public ItemDTO findItem(String code){

        ItemData itemData = itemDataRepository.findByCode(code);
        return modelMapper.map(itemData, ItemDTO.class);
    }

    public ItemDTO createUpdateItem(ItemDTO itemDto, List<SupplierDTO> suppliersDTO){

        if(itemDto == null) {
            throw new RuntimeException("error");        //OJO, TE DIJO RAYCO QUE CREARAS TU PROPIA EXCEPCION
        }
        ItemData itemData = itemDataRepository.findByCode(itemDto.getCode());

        if (itemData == null){
            itemData = new ItemData();
            itemData.setCode(itemDto.getCode());
            itemData.setCreationDate(LocalDate.now());
        }

        if(itemData.getState()==StateEnum.ACTIVE){

        itemData.setState(itemDto.getState());
        itemData.setPrice(itemDto.getPrice());
        itemData.setDescription(itemDto.getDescription());
        itemData.setUserName(itemDto.getUsername());
        itemData.setState(itemDto.getState());

        if(suppliersDTO != null && !suppliersDTO.isEmpty()) {
            List<SupplierData> suppliers = supplierDataRepository.findSupplierNamesNotItem(suppliersDTO.stream().map(SupplierDTO::getName).toList(), itemData);
            itemData.addSuppliers(suppliers);
        }

        if(itemDto.getPriceReductions() != null && !itemDto.getPriceReductions().isEmpty()){
            itemData.addPriceReductions(modelMapper.map(itemDto.getPriceReductions(), new TypeToken<List<PriceReductionData>>() {}.getType() ));
           /* itemData.addPriceReductions(itemDto.getPriceReductions().stream().map(priceReductionDto ->
                    new PriceReductionData(null, priceReductionDto.getReducedPrice(),
                            priceReductionDto.getStartDate(), priceReductionDto.getEndDate(), null)
            ).toList());*/
        }

        return modelMapper.map(itemData, ItemDTO.class);
        }
        return modelMapper.map(itemData, ItemDTO.class);
    }

    public void deleteItemByCode(ItemDTO itemDto){

        String code = itemDto.getCode();
        ItemData itemData = itemDataRepository.findByCode(code);
        itemData.getSuppliersData().forEach(supplierData ->
                supplierData.getItemsData().remove(itemData));
        itemDataRepository.delete(itemData);
    }

    public void discontueItem(DeactivateDTO deactivate){

        String code =deactivate.getItemDto().getCode();
        ItemData itemData = itemDataRepository.findByCode(code);
        UserData userdata = userDataRepository.findByUsername(deactivate.getUserDto().getUsername());
        itemData.setState(StateEnum.DISCONTINUED);
        itemDataRepository.save(itemData);

        Deactivate deactivateData = new Deactivate();

        deactivateData.setItemdata(itemData);
        deactivateData.setUserdata(userdata);
        deactivateData.setReason(deactivate.getReason());
        deactivateData.setObservation(deactivate.getObservation());
        deactivateData.setDiscontinueDate(LocalDate.now());

        deactiveDataRepository.save(deactivateData);

    }
}