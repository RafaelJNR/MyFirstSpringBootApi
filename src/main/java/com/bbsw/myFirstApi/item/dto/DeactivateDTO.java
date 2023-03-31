package com.bbsw.myFirstApi.item.dto;

import com.bbsw.myFirstApi.item.enums.ReasonEnum;
import com.bbsw.myFirstApi.user.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DeactivateDTO {

    UserDTO userDto;
    ItemDTO itemDto;
    ReasonEnum reason;
    String Observation;
    LocalDate discontinueDate;




}
