package com.bbsw.myFirstApi.user.model;

import com.bbsw.myFirstApi.user.enums.RolEnum;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "userdata", schema ="erp")
@Entity
@Getter
@Setter
public class UserData {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "userdata_id_seq")
    @SequenceGenerator(name = "userdata_id_seq",sequenceName = "userdata_id_seq", allocationSize = 1, schema = "erp")
    @Column(name = "iduser")
    Long idUser;
    @Column(name = "username", unique = true, nullable = false)
    String username;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    RolEnum rol;

}