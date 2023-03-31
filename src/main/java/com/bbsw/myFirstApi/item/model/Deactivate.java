package com.bbsw.myFirstApi.item.model;

import com.bbsw.myFirstApi.item.enums.ReasonEnum;
import com.bbsw.myFirstApi.user.model.UserData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name ="deactivate", schema= "erp")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Deactivate {

    @Id
    @Column(name ="iddeactivate")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "deactivate_id_seq")
    @SequenceGenerator(name = "deactivate_id_seq",sequenceName = "deactivate_id_seq", allocationSize = 1, schema = "erp")
    Long idDeactivate;

    @ManyToOne
    @JoinColumn(name="itemdeactivate")
    @JsonBackReference("itemDeactivated")
    private ItemData itemdata;

    @ManyToOne
    @JoinColumn(name="userdeactivate")
    @JsonBackReference("userDeactivated")
    private UserData userdata;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ReasonEnum reason;

    String observation;
    @Column(name="discontinuedate")
    LocalDate discontinueDate;


}
