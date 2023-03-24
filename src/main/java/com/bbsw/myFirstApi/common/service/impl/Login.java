package com.bbsw.myFirstApi.common.service.impl;

import com.bbsw.myFirstApi.encoder.Encoder;

public class Login {

    public String GetLogin(String username, String password){

    //NOTA PARA MI YO DEL FUTURO. DEBES EXTRAER DE LA BBDD LOS DATOS DE USUARIO Y PASSWORD Y COMPARARLOS CON LOS USERNAME Y PASSWORD QUE YA LOS CIFRASTE EN LA 14 Y 15

        String bbddUserName = "", bbddPassword= "", bbddRol= "";
        Encoder encoder = new Encoder();
        username = encoder.getCipher(username);
        password = encoder.getCipher(password);

        if(username.equals(bbddUserName) && password.equals(bbddPassword)){
            return bbddRol;
        }else{
            return bbddRol;
        }

    }

}
